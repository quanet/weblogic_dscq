import java.io.IOException;

/**
 * @author : lihaoquan
 */
public class TestDemo {

    private static final String filePath
            = "F:\\projects\\Weblogic定时控制程序\\weblogic_dscq\\restarter\\src\\main\\resources\\batfiles\\";

    public static String execCommand(String command){
        String errorMSG = "";
        try {
            String g = "";
            command=filePath+command;
            //Process p =
            Runtime.getRuntime().exec(new String[] {command, g});
        } catch (IOException e) {
            System.out.println("error Message:"+e.getMessage());
            e.printStackTrace();
        } finally{
            return errorMSG;
        }
    }

    /**
     *
     *   .在 startWebLogic.cmd 和 stopWebLogic.cmd 的结尾部分
     *
     *   if "%doExitFlag%"=="true" (
     *      exit
     *   )
     *
     *   .更改为 ：
     *
     *   if "%doExitFlag%"=="false" (
     *      exit
     *   )
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        //execCommand("hook.bat");

        execCommand("shutdownHook.bat");
        Thread.sleep(30000);
        execCommand("startupHook.bat");
    }
}
