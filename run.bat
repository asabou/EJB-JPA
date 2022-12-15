set JAVA_HOME=D:\tools\jdk-17.0.2
set GRADLE_HOME=D:\tools\gradle-7.3.3
set JBOSS_HOME=D:\tools\wildfly-preview-27.0.0.Beta1
set GF_HOME=D:\tools\glassfish6

set PATH=%JAVA_HOME%\bin;%PATH%
set PATH=%GRADLE_HOME%\bin;%PATH%
set PATH=%JBOSS_HOME%\bin;%PATH%
set PATH=%GF_HOME%\bin;%PATH%

set SERVER_PROJECT=D:\Master\An1\Sem1\TPJAD\EJB-JPA\com.tpjad.ejbjpa.groceries.server
set CLIENT_PROJECT=D:\Master\An1\Sem1\TPJAD\EJB-JPA\com.tpjad.ejbjpa.groceries.client

for /F "tokens=5 delims= " %%P in ('netstat -ano ^| findstr :8080') do taskkill /PID %%P /F

set SERVER=%1

if not "%SERVER%" == "wildfly" if not "%SERVER%" == "glassfish" (
    echo "No server was specified!"
    exit 1
)

del %SERVER_PROJECT%\src\main\resources\META-INF\persistence.xml

copy %SERVER_PROJECT%\persistence\%SERVER%\persistence.xml %SERVER_PROJECT%\src\main\resources\META-INF

if "%SERVER%" == "wildfly" (
    cd %SERVER_PROJECT%  & call gradle clean build 
    del %JBOSS_HOME%\standalone\deployments\com.tpjad.ejbjpa.groceries.server.war
    copy %SERVER_PROJECT%\build\libs\com.tpjad.ejbjpa.groceries.server.war %JBOSS_HOME%\standalone\deployments
    cd %CLIENT_PROJECT%  & call gradle clean build -Dserver=%SERVER%
    del %JBOSS_HOME%\standalone\deployments\com.tpjad.ejbjpa.groceries.client.war
    copy %CLIENT_PROJECT%\build\libs\com.tpjad.ejbjpa.groceries.client.war %JBOSS_HOME%\standalone\deployments
    start %JBOSS_HOME%\bin\standalone.bat
    start "" http://localhost:8080/com.tpjad.ejbjpa.groceries.server/LoginLocalServlet
    start "" http://localhost:8080/com.tpjad.ejbjpa.groceries.client/LoginWFServlet
)

if "%SERVER%" == "glassfish" (
    cd %SERVER_PROJECT%  & call gradle clean build 
    del %GF_HOME%\glassfish\domains\domain1\logs\server.log
    del %GF_HOME%\glassfish\domains\domain1\autodeploy\com.tpjad.ejbjpa.groceries.server.war
    copy %SERVER_PROJECT%\build\libs\com.tpjad.ejbjpa.groceries.server.war %GF_HOME%\glassfish\domains\domain1\autodeploy
    cd %CLIENT_PROJECT%  & call gradle clean build -Dserver=%SERVER%
    del %GF_HOME%\glassfish\domains\domain1\autodeploy\com.tpjad.ejbjpa.groceries.client.war
    copy %CLIENT_PROJECT%\build\libs\com.tpjad.ejbjpa.groceries.client.war %GF_HOME%\glassfish\domains\domain1\autodeploy
    start call %GF_HOME%\bin\asadmin.bat start-domain
    start "" http://localhost:8080/com.tpjad.ejbjpa.groceries.server/LoginLocalServlet
    start "" http://localhost:8080/com.tpjad.ejbjpa.groceries.client/LoginGFServlet
)
    






