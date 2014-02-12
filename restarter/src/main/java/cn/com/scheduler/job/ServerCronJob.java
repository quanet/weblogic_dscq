package cn.com.scheduler.job;

import cn.com.restarter.service.TaskHandler;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Author : lihaoquan
 * Description : 使用Spring的ThreadPoolTaskScheduler执行Cron式任务的类.
 */
public class ServerCronJob implements Runnable {

    private String cronExpression;

    private int shutdownTimeout = Integer.MAX_VALUE;

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private TaskHandler taskHandler;

    private static Logger logger = LoggerFactory.getLogger(ServerCronJob.class);

    @PostConstruct
    public void start() {
        logger.info("Cron Job 启动");
        Validate.notBlank(cronExpression);
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setThreadNamePrefix("ServerCronJob");
        threadPoolTaskScheduler.initialize();
        threadPoolTaskScheduler.schedule(this, new CronTrigger(cronExpression));
    }

    @PreDestroy
    public void stop() {
        logger.info("Cron Job 停止");
        ScheduledExecutorService scheduledExecutorService = threadPoolTaskScheduler.getScheduledExecutor();
        normalShutdown(scheduledExecutorService, shutdownTimeout, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        try {
            taskHandler.restart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 直接调用shutdownNow的方法, 有timeout控制.取消在workQueue中Pending的任务,并中断所有阻塞函数.
     */
    public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
        try {
            pool.shutdownNow();
            if (!pool.awaitTermination(timeout, timeUnit)) {
                System.err.println("Pool did not terminated");
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }


    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * 设置normalShutdown的等待时间,单位秒.
     */
    public void setShutdownTimeout(int shutdownTimeout) {
        this.shutdownTimeout = shutdownTimeout;
    }

}
