package cn.com.restarter.util;

import org.springframework.context.ApplicationContext;

/**
 * Author : lihaoquan
 * Description :
 */
public class ApplicationContextManagement {

    private static ApplicationContext appContext;

    public static void init(ApplicationContext appContext){
        ApplicationContextManagement.appContext = appContext;
    }

    public static ApplicationContext getApplicationContext(){
        return appContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

}
