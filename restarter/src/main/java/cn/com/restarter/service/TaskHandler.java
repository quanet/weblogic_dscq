package cn.com.restarter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author : lihaoquan
 */
@Component
public class TaskHandler {

    private static Logger logger = LoggerFactory.getLogger(TaskHandler.class);

    /**
     * 启动
     */
    public void start() {
        logger.info("启动weblogic应用");
    }


    /**
     * 停止
     */
    public void stop() {
        logger.info("停止weblogic应用");
    }


    /**
     * 重启
     */
    public void restart() {
        logger.info("重启weblogic应用");
    }
}
