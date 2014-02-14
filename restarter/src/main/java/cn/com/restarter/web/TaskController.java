package cn.com.restarter.web;

import cn.com.restarter.service.TaskHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author : ChelseaBlue
 * Description :
 */

@Controller
@RequestMapping("/task")
public class TaskController {

    /**
     * .进入前台首页
     * @return
     */
    @RequestMapping("/index.action")
    public String index() {
        System.out.println("--------------------------正在进入前台");
        return "/index.jsp";

    }

    /**
     * .点击重启按钮触发方法
     * @return
     */
    @RequestMapping("/restart.action")
    public String restart() throws Exception {
        TaskHandler.restart_sd();
        return "/restartWeblogic.jsp?type=1";

    }
}
