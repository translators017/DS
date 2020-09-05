rem cd C:\DSBuild

rem copy /b/v/y C:\DSBuild\unitTouch.txt C:\DSBuild\temp

rem set userwarname=%2
rem set dirname=%userwarname:.war=%
rem set warname=%userwarname:.war=%

rem svn checkout %1 C:\DSBuild\%dirname%\

set maven_path=D:\softwares\apache-maven-3.6.3\bin
set WORKSPACE=D:\DataSwitch\BuildFolder

call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\ConnectorSpecs\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\DataTypeConverter\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\FunctionConverter\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


IF EXIST "%WORKSPACE%\CoreLibs\ScriptCommonDTO\ScriptCommonDTO\pom.xml" call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\ScriptCommonDTO\ScriptCommonDTO\ScriptCommonDTO\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\ETLMapping\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\SQLConverter\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\TalendCodegen\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\SQLRevEngg\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


IF EXIST "%WORKSPACE%\CoreLibs\ScriptTranspiler\ScriptTranspiler\pom.xml" call %maven_path%\mvn -f %WORKSPACE%\CoreLibs\ScriptTranspiler\ScriptTranspiler\ScriptTranspiler\pom.xml clean install -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


rem copy /b/v/y C:\DSBuild\live\DS_config.xml %WORKSPACE%\GUI\DS_Portal\WebContent\conf
call %maven_path%\mvn -f %WORKSPACE%\DS_Portal\pom.xml clean package -U --log-file "C:\\unitTest.txt"
echo Exit Code = %ERRORLEVEL%


rem copy %WORKSPACE%\GUI\DS_Portal\target\DS_beta_live.war C:\DSBuild\temp
rem ren C:\DSBuild\temp\DS_beta_live.war %2
rem IF EXIST "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps\%warname%.war" curl "http://DSadmin:DS_2020@localhost:8080/manager/text/undeploy?path=/%warname%"
rem copy C:\DSBuild\temp\%2 "C:\Program Files\Apache Software Foundation\Tomcat 8.5\webapps"
rem aws s3 cp C:\DSBuild\temp\%2 s3://DS-beta-release/%2
rem del C:\DSBuild\temp\%2

rem RD /S /Q C:\DSBuild\%dirname%
rem echo "Unit Test Build Successfully Completed"
rem java -jar C:\\DSBuild\mail.jar C:\\emailList.txt C:\unitMail.txt "EC2 DS Build Status - Unit Test"
rem del C:\DSBuild\temp\unitTouch.txt