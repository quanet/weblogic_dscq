package cn.com.restarter.web;

import cn.com.restarter.service.TaskHandler;
import cn.com.restarter.util.FileModify;
import cn.com.restarter.util.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/restart.action", method = RequestMethod.POST)
    @ResponseBody
    public String restart() throws Exception {
        TaskHandler.restart_sd();
        return "success";
    }

    /**
     * .点击重启按钮触发方法--重启单个
     *
     * @return
     */
    @RequestMapping(value = "/restartOne.action", method = RequestMethod.POST)
    @ResponseBody
    public String restartOne(String url) throws Exception {
        TaskHandler.restart_sd_One(url);
        return "success";
    }

    /**
     * 下载本应用日志
     *
     * @return
     */
    @RequestMapping("/downloadlog.action")
    public String downloadlog(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = "C:/restartlog";
        FileUtils.downLoadFilesByUrl(request, response, url, "restarter.zip");
        return null;
    }

    /**
     * 下载所部属应用日志
     *
     * @return
     */
    @RequestMapping("/downloadlogALL.action")
    public void downloadlogALL(HttpServletRequest request, HttpServletResponse response, int count) throws Exception {
        List<String> listStr = FileModify.readFileByLines(Thread.currentThread()
                .getContextClassLoader().getResource("").getPath() + "config.properties");
        String url[] = listStr.get(count).split("//");
        FileUtils.downLoadFilesByUrl(request, response, listStr.get(count) + "\\servers\\AdminServer\\logs", url[url.length - 1] + ".zip");
    }

    /**
     * 下载单个应用日志
     *
     * @return
     */
    @RequestMapping("/downloadlogOne.action")
    public void downloadlogOne(HttpServletRequest request, HttpServletResponse response,String url) throws Exception {
        String urls[] = url.split("//");
        FileUtils.downLoadFilesByUrl(request, response,url + "//servers//AdminServer//logs", urls[urls.length - 1] + ".zip");
    }

    //获取项目数量+1
    public static int getCount() {
        List<String> listStr = FileModify.readFileByLines(Thread.currentThread()
                .getContextClassLoader().getResource("").getPath() + "config.properties");
        return listStr.size();
    }


    /**
     * 获取项目weblogic部署地址
     *
     * @return
     */
    @RequestMapping(value = "/restartWeblogic.action")
    public String restartWeblogic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> listStr = FileModify.readFileByLines(Thread.currentThread()
                .getContextClassLoader().getResource("").getPath() + "config.properties");
        listStr.remove(0);
        request.setAttribute("weblogiclist",listStr);
        return "/restartWeblogic.jsp";
    }
}
