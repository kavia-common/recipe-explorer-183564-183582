@ECHO OFF
@rem ##########################################################################
@rem Gradle startup script for Windows (text-mode wrapper for CI)
@rem ##########################################################################
setlocal

@rem Default JVM options
set DEFAULT_JVM_OPTS="-Xmx64m" "-Xms64m"

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set "APP_HOME=%DIRNAME%"
set "APP_BASE_NAME=%~n0"

set "CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar"

@rem Find Java
if defined JAVA_HOME (
  set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
) else (
  set "JAVA_EXE=java.exe"
)

"%JAVA_EXE%" -version >NUL 2>&1
if not "%ERRORLEVEL%"=="0" (
  echo ERROR: JAVA_HOME is not set and no 'java' command could be found on PATH.
  exit /b 1
)

@rem Execute Gradle Wrapper Main
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% -Dorg.gradle.appname=%APP_BASE_NAME% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
set EXIT_CODE=%ERRORLEVEL%

endlocal & exit /b %EXIT_CODE%
