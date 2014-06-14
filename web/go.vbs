Option Explicit

Sub Usage
    WScript.Echo "Usage: cscript.exe " & WScript.ScriptFullName & " [-P profile(s)] [-u url] [-d driver] [-l username] [-p password]"
End Sub

Function IsFlagArg(arg)
    'add others here when necessary
    IsFlagArg = (arg = "-h")
End Function

Function CreateArgumentDictionary(args)
    Dim dict
    Set dict = CreateObject("Scripting.Dictionary")

    If args.Count = 0 Then
        Set CreateArgumentDictionary = dict
        Exit Function
    End If

    Dim i, arg
    For i = 0 To args.Count - 1
        If i + 1 < args.Count Then
            arg = Trim(args(i))
            Dim value
            If IsFlagArg(arg) Then
                value = True
            Else
                value = args(i + 1)
                i = i + 1
            End If
            dict.Add arg, value
        End If
    Next
    Set CreateArgumentDictionary = dict
End Function

Function GetEnvironmentVariable(name)
    Dim sh : Set sh = CreateObject("WScript.Shell")
    
    Dim value, e, env(1)
    env(0) = "User"
    env(1) = "System"

    For Each e In env
        value = sh.Environment(e).Item(name)
        If Len(value) > 0 Then
            GetEnvironmentVariable = value
            Exit Function
        End If
    Next
    GetEnvironmentVariable = ""
End Function

Sub Monitor(ByRef proc)
    Do
        If Not proc.StdOut.AtEndOfStream Then WScript.StdOut.WriteLine proc.StdOut.ReadLine
    Loop While proc.Status = 0

    If Not proc.StdErr.AtEndOfStream Then WScript.StdErr.Write proc.StdErr.ReadAll
End Sub

Function RunningInCScript()  
	RunningInCscript = InStrRev(UCase(WScript.FullName), "CSCRIPT.EXE") > 0
End Function

Function ReadTextFile(filename)
    Dim fso, stream, contents
    Set fso = CreateObject("Scripting.FileSystemObject")
    Set stream = fso.OpenTextFile(filename, 1, False, -2)

    contents = stream.ReadAll
    stream.Close

    ReadTextFile = contents
End Function

'Main

If Not RunningInCScript() Then
    WScript.Echo _
        "This script may only be run in console mode; either " & vbNewLine & _
        "  * preface command with ""cscript.exe"" or" & vbNewLine & _
        "  * register *.vbs files to be run with cscript.exe."
    WScript.Quit(1)
End If

Dim args : Set args = CreateArgumentDictionary(WScript.Arguments)

Dim profiles : profiles = args("-P")
If Len(profiles) = 0 Then
    profiles = GetEnvironmentVariable("SPRING_PROFILES_ACTIVE")
    If Len(profiles) = 0 Then
        WScript.Echo "Profile(s) required if no SPRING_PROFILES_ACTIVE environment variable present"
        Usage
        WScript.Quit(1)
    End if
End If

Dim cmd : cmd = "cmd.exe /c mvn clean process-test-resources -P " & profiles
Dim sh : Set sh = CreateObject("WScript.Shell")
WScript.Echo "Executing: " & cmd
Dim proc : Set proc = sh.Exec(cmd)

Monitor(proc)

If proc.ExitCode <> 0 Then
    WScript.Echo "Error " & proc.ExitCode & " executing " & cmd
    WScript.Quit(proc.ExitCode)
End If

Set proc = Nothing

Dim generatedScriptFile, thisScriptPath
thisScriptPath = Left(WScript.ScriptFullName, InStrRev(WScript.ScriptFullName, WScript.ScriptName) - 1)
generatedScriptFile = thisScriptPath & "target\test-classes\run-webapp.vbs"

Dim script
script = ReadTextFile(generatedScriptFile)

If Len(script) = 0 Then
    Wscript.StdErr.WriteLine "Error reading generated script " & generatedScriptFile
    Wscript.Quit(2)
End If

ExecuteGlobal(script)
