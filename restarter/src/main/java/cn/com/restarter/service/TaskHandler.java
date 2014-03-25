package cn.com.restarter.service;

import cn.com.restarter.util.FileModify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        try {
            //批处理文件路径
            String filePath = Thread.currentThread()
                    .getContextClassLoader().getResource("").getPath();
            String g = "";
            command = filePath + command;

            //运行程序
            Runtime.getRuntime().exec(new String[]{command, g});
        } catch (Exception e) {
            System.out.println("error Message:" + e.getMessage());
            e.printStackTrace();
        } finally {
            return errorMSG;
        }
    }

    /**
     * 启动
     */
    public void start() {
        logger.info("启动weblogic应用");
        execCommand("startup.bat");
    }


    /**
     * 停止
     */
    public void stop() {
        logger.info("停止weblogic应用");
        execCommand("shutdown.bat");
    }


    /**
     * 重启
     */
    public static void restart() throws Exception {
        logger.info("重启weblogic应用");
        execCommand("shutdown.bat");
        Thread.sleep(20000);
        execCommand("startup.bat");
    }

    /**
     * 手动重启
     */
    public static void restart_sd() throws Exception {
        logger.info("手动重启weblogic应用");
        execCommand("shutdown.bat");
        Thread.sleep(20000);
        execCommand("startup.bat");
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
