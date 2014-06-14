@echo off
setlocal

rem There might be more than 9 arguments, so need to shift instead of using percent-asterisk notation

set ARGS=
:CONCAT
set ARG=%~1
if "%ARG%"=="" goto CONCAT_END
set ARGS=%ARGS% "%~1"
shift
goto CONCAT
:CONCAT_END

set GO=cscript.exe go.vbs %ARGS%
echo %GO%
%GO%
