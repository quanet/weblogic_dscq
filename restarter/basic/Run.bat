@echo off
echo [Pre-Requirement] Makesure install JDK 6.0+ and set the JAVA_HOME.

set MVN=mvn
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=128m

cd WEB-INF
cd classes

java RunJettyServer

:end
pause
