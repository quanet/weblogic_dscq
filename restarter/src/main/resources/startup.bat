@ECHO OFF

  SET ROOT=%~dp0
  SET CONFIG_FILE=%ROOT%config.properties
   for /f "tokens=1,2 delims=^=" %%a in (%CONFIG_FILE%) do (
     if /I "%%a"=="URL" (
        echo µÚÒ»ÐÐºöÂÔ
      )
       if /I not "%%a"=="URL" (
   start %%b\startWebLogic.cmd
            )
            )