@ECHO OFF

  SET ROOT=%~dp0
  SET CONFIG_FILE=%ROOT%project.ini

  FOR /F "tokens=1,2 delims==" %%i in (%CONFIG_FILE%) DO (
     SET %%i=%%j
  )

  start %project_domain_path%\bin\stopWebLogic.cmd

