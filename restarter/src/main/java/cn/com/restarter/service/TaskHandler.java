package cn.com.restarter.service;

import cn.com.restarter.domain.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : lihaoquan
 */
@Component
public class TaskHandler {

    private static Logger logger = LoggerFactory.getLogger(TaskHandler.class);

    public  String execCommand(String command){
        String errorMSG = "";
        try {
            //批处理文件路径
            String filePath = Thread.currentThread()
                    .getContextClassLoader().getResource("").getPath();
            String g = "";
            command=filePath+command;

            //运行程序
            Runtime.getRuntime().exec(new String[] {command, g});
        } catch (Exception e) {
            System.out.println("error Message:" + e.getMessage());
            e.printStackTrace();
        } finally{
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
    public void restart() throws Exception {
        logger.info("重启weblogic应用");
        execCommand("shutdown.bat");
        Thread.sleep(30000);
        execCommand("startup.bat");
    }
}
