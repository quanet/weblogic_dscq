package cn.com.restarter.web;

import cn.com.restarter.service.TaskHandler;

import javax.servlet.ServletException;

/**
 * Ӧ������ʱ�޸�weblogic��bat�ļ�
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
