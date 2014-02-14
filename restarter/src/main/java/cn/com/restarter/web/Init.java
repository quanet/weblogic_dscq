package cn.com.restarter.web;

import cn.com.restarter.service.TaskHandler;

import javax.servlet.ServletException;

/**
 * 应用启动时修改weblogic的bat文件
 * Created by zhengjianwen on 14-2-14.
 */
public class Init extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    @Override
    public void init() throws ServletException {
        try {
            TaskHandler.changeBat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.init();
    }
}
