package cn.com.restarter.service;

import cn.com.restarter.util.FileModify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : lihaoquan
 */
@Component
public class TaskHandler {

    private static Logger logger = LoggerFactory.getLogger(TaskHandler.class);

    public static String execCommand(String command) {
        String errorMSG = "";
        Process process;
        List list=new ArrayList();
        String line = null;
        try {
            //批处理文件路径
            String filePath = Thread.currentThread()
                    .getContextClassLoader().getResource("").getPath();
            String g = "";
            command = filePath + command;

            logger.info("bat路径:"+command);

            //运行程序
//            Process process = Runtime.getRuntime().exec(new String[]{command, g});
//            process.waitFor();
            //执行命令
            process = Runtime.getRuntime().exec(new String[]{command, g});
            //取得命令结果的输出流
            InputStream fis = process.getInputStream();
            //用一个读输出流类去读
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            //int c;
            //逐行读取输出到控制台
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
            if(process.waitFor()!=0){
                logger.info(command+"执行失败！");
            }
        } catch (Exception e) {
            System.out.println("error Message:" + e.getMessage());
            e.printStackTrace();
        } finally {
            return errorMSG;
        }
    }
    public static void creatBat(String command,String type){
        FileWriter fw=null;
        try {
            String filePath = Thread.currentThread()
                    .getContextClassLoader().getResource("").getPath();
            fw=new FileWriter(filePath+type);
            fw.write(command);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(0);
        }finally{
            if(fw!=null){
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
    }

    /**
     * 启动
     */
    public void start() {
        logger.info("启动weblogic应用");
        execCommand("startup.bat");
        logger.info("weblogic应用启动成功");
    }


    /**
     * 停止
     */
    public void stop() {
        logger.info("停止weblogic应用");
        execCommand("shutdown.bat");
        logger.info("weblogic应用停止成功");
    }


    /**
     * 重启
     */
    public static void restart() throws Exception {
        logger.info("自动重启weblogic应用");
        execCommand("shutdown.bat");
        logger.info("自动weblogic应用停止成功");
        Thread.sleep(30000);
        execCommand("startup.bat");
        logger.info("自动weblogic应用启动成功");
    }

    /**
     * 手动重启
     */
    public static void restart_sd() throws Exception {
        logger.info("手动重启weblogic应用");
        execCommand("shutdown.bat");
        logger.info("手动weblogic应用停止成功");
        Thread.sleep(30000);
        execCommand("startup.bat");
        logger.info("手动weblogic应用启动成功");
    }

    /**
     * 手动重启--单个
     */
    public static void restart_sd_One(String url) throws Exception {
        logger.info("手动重启weblogic应用");
        String commandstop = "@ECHO OFF\n" +
                "  SET ROOT=%~dp0\n" +
                "    start "+url+"//bin//stopWebLogic.cmd";
        creatBat(commandstop,"shutdownOne.bat");
        String commandstartup = "@ECHO OFF\n" +
                "  SET ROOT=%~dp0\n" +
                "    start "+url+"//startWebLogic.cmd";
        creatBat(commandstartup,"startupOne.bat");
        logger.info("手动重启weblogic应用(单个):"+url);
        execCommand("shutdownOne.bat");
        logger.info("手动weblogic应用停止成功(单个):"+url);
        Thread.sleep(30000);
        execCommand("startupOne.bat");
        logger.info("手动weblogic应用启动成功(单个):"+url);
    }

    /**
     * 改变应用的startWebLogic.cmd和stopWebLogic.cmd
     */
    public static void  changeBat() throws Exception {
        List<String> listStr = FileModify.readFileByLines(Thread.currentThread()
                .getContextClassLoader().getResource("").getPath()+"config.properties");
        for(int i = 1 ;i<listStr.size();i++){
            FileModify.modify(listStr.get(i)+"//bin//startWebLogic.cmd");
            FileModify.modify(listStr.get(i)+"//bin//stopWebLogic.cmd");
        }

    }




}
