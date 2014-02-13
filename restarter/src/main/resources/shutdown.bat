@ECHO OFF

  SET ROOT=%~dp0
  SET CONFIG_FILE=%ROOT%project.ini

   for /f "tokens=1,2 delims=^=" %%a in (%CONFIG_FILE%) do (
   start %%b\bin\stopWebLogic.cmd
   )

