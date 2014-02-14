package cn.com.restarter.web;

import cn.com.restarter.service.TaskHandler;
import cn.com.restarter.util.FileModify;
import cn.com.restarter.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Author : ChelseaBlue
 * Description :
 */

@Controller
@RequestMapping("/task")
public class TaskController {

    /**
     * .进入前台首页
     *
     * @return
     */
    @RequestMapping("/index.action")
    public String index() {
        System.out.println("--------------------------正在进入前台");
        return "/index.jsp";

    }

    /**
     * .点击重启按钮触发方法
     *
     * @return
     */
    @RequestMapping("/restart.action")
    public String restart() throws Exception {
        TaskHandler.restart_sd();
        return "/restartWeblogic.jsp?type=1";

    }

    /**
     * 下载本应用日志
     *
     * @return
     */
    @RequestMapping("/downloadlog.action")
    public String downloadlog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "C:/restartlog" ;
        FileUtils.downLoadFilesByUrl(request,response,url);
        return null;
    }

    /**
     * 下载所部属应用日志
     *
     * @return
     */
    @RequestMapping("/downloadlog1.action")
    public String downloadlog1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> listStr = FileModify.readFileByLines(Thread.currentThread()
                .getContextClassLoader().getResource("").getPath() + "project.ini");
        for(int i =0;i<listStr.size();i++){
            FileUtils.downLoadFilesByUrl(request,response,listStr.get(i)+"\\servers\\AdminServer\\logs");
        }
        return null;
    }
}
