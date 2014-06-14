' This file is generated so that you can run the seeded database produced
' by the app-domain build.  Once app-web is built, just execute ./go in the app-web module root directory.

Function AreAnyDbArgsGiven(args)
    Dim dbArgName, dbArgNames(3)
    dbArgNames(0) = "-u"
    dbArgNames(1) = "-d"
    dbArgNames(2) = "-l"
    dbArgNames(3) = "-p"

    For Each dbArgName In dbArgNames
        If Len(args.Item(dbArgName)) > 0 Then
            AreAnyDbArgsGiven = True
            Exit Function
        End If
    Next

    AreAnyDbArgsGiven = False
End Function

Function UserHomeDir()
    Dim sh : Set sh = CreateObject("WScript.Shell")
    UserHomeDir = sh.ExpandEnvironmentStrings("%USERPROFILE%")
End Function

Sub Mkdirs(path,fso)
    If fso Is Nothing Then
        Mkdirs path, CreateObject("Scripting.FileSystemObject")
        Exit Sub
    End If
    
    If Not fso.FolderExists(path) Then
        Mkdirs fso.GetParentFolderName(path), fso
        fso.CreateFolder(path)
    End If
End Sub

Function Unzip(zipFile, destDir)
    Dim fso, app

    Set fso = CreateObject("Scripting.FileSystemObject")
    If Not fso.FolderExists(destDir) Then
        Mkdirs destDir, fso
    End If

    Dim priorDir, sh
    Set sh = CreateObject("WScript.Shell")
    priorDir = sh.CurrentDirectory
    sh.CurrentDirectory = destDir

    Dim cmd : cmd = "cmd.exe /c jar xf " & zipFile
    Dim proc : Set proc = sh.Exec(cmd)
    Monitor proc

    sh.CurrentDirectory = priorDir

    Unzip = (proc.ExitCode = 0)
End Function

Sub RunWebapp(args)

    ' replace dots with slashes
    Dim companyPrefixAsDir : companyPrefixAsDir = Replace("@project.groupId@", ".", "\")
    Dim dbPort, dbStartCmd, dbStopCmd, dbHost, dbUrl, dbDriver, dbUser, dbPwd, dbArgsGiven
    Dim fs : Set fs = CreateObject("Scripting.FileSystemObject")

    ' defaults
    dbPort = "@eligibility.rdb.derby.test.database.port@"
    dbStartCmd = "startNetworkServer -h localhost -p " & dbPort
    dbStopCmd = "stopNetworkServer -p " & dbPort
    dbHost = "@eligibility.rdb.sqlserver.test.database.hostname@"
    dbUrl = "jdbc:derby://" & dbHost & ":" & dbPort & "/@eligibility.rdb.derby.seed.artifact.artifactId@-@project.version@"
    dbDriver = "org.apache.derby.jdbc.ClientDriver"
    dbUser = "APP"
    dbPwd = "nothing"
    dbArgsGiven = AreAnyDbArgsGiven(args)

    If Not dbArgsGiven Then 'use defaults -- unzip seeded database & launch server

        Dim dbSrcFile, dbParentDir, dbDir

        'Unzip

	    dbSrcFile = UserHomeDir() & "\.m2\repository\" & companyPrefixAsDir & "\@eligibility.rdb.derby.seed.artifact.artifactId@\@project.version@\@eligibility.rdb.derby.seed.artifact.artifactId@-@project.version@-@eligibility.rdb.derby.seed.artifact.classifier@.@eligibility.rdb.derby.seed.assembly.format@"
        dbParentDir = "@project.build.directory@\run-webapp\db"
	    dbDir = dbParentDir & "\@eligibility.rdb.derby.seed.artifact.artifactId@-@project.version@"
	
	    Mkdirs dbParentDir, Nothing
	
    	If fs.FileExists(dbSrcFile) Then
            WScript.Echo dbSrcFile
            WScript.Echo dbParentDir
    		If Not Unzip(dbSrcFile, dbParentDir) Then
                WScript.Echo "Error extracting " & dbSrcFile
                WScript.Quit(2)
            End If
    	Else
    		WScript.StdErr.WriteLine "not readable: " & dbSrcFile
    		WScript.Quit(2)
    	End If

        'Start Derby

    	Dim derbyOpts : derbyOpts = "-Dderby.system.home=" & dbParentDir
        Dim derbyStartBatchFile : derbyStartBatchFile = dbParentDir & "\derbyStart.bat"
        Dim out : Set out = fs.CreateTextFile(derbyStartBatchFile, True)
        out.WriteLine "set DERBY_OPTS=" & derbyOpts
        out.WriteLine dbStartCmd
        out.Close

        Dim dbStartShell : Set dbStartShell = CreateObject("WScript.Shell")
        Dim dbStartShellCmd : dbStartShellCmd = "cmd.exe /c " & derbyStartBatchFile
        WScript.Echo "Executing in new window: " & dbStartShellCmd
        dbStartShell.Run dbStartShellCmd, 0, False

    Else ' db info is given
		
        If args.Exists("-u") Then dbUrl = args("-u")
        If args.Exists("-d") Then dbDriver = args("-d")
        If args.Exists("-l") Then dbUser = args("-l")
        If args.Exists("-p") Then dbPwd = args("-p")

	    If Len(dbUrl) = 0 Then
		    WScript.Echo "Database URL is required!" >&2
		    Usage
		    WScript.Quit(1)
	    End If
	
	    If Len(dbDriver) = 0 Then
		    WScript.Echo "Database driver is required!" >&2
		    Usage
		    WScript.Quit(1)
	    End If
    End If

    Dim runArgs : runArgs = "-Deligibility.rdb.url=" & dbUrl & " -Deligibility.rdb.driver=" & dbDriver & " -Deligibility.rdb.username=" & dbUser & " -Deligibility.rdb.password=" & dbPwd & " -Deligibility.rdb.driver.maxActive=4 -Deligibility.rdb.driver.maxWait=2000"

    Dim sh : Set sh = CreateObject("WScript.Shell")
    
    Dim debugOpts : debugOpts = ""
    Dim mvnDashX : mvnDashX = ""
    If args.Exists("-X") Then
        debugOpts = "set MAVEN_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=" & args("-X") & " & "
        mvnDashX = "-X"
        WScript.Echo "!! NOTE:  Debugger will be listening on port " & args("-X")
    End If

    Dim container : container = "cmd.exe /c " & debugOpts & " mvn " & mvnDashX & " tomcat7:run " & runArgs & " -P " & args("-P")
    WScript.Echo "Executing: " & container
    
    sh.Run container, 1, True

    If Not dbArgsGiven Then
        dbStopCmd = "cmd.exe /c " & dbStopCmd
        Dim dbStopShell : Set dbStopShell = CreateObject("WScript.Shell")
        WScript.Echo "Executing: " & dbStopCmd
        Dim proc : Set proc = dbStopShell.Exec(dbStopCmd)
        Monitor proc
    End If

End Sub

RunWebapp CreateArgumentDictionary(WScript.Arguments)
