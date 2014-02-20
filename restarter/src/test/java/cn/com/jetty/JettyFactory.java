package cn.com.jetty;

import org.eclipse.jetty.server.Server;

/**
 * @author : lihaoquan
 */
public class JettyFactory {


    /**
     * 创建Jetty的Server
     * @param port
     * @param contextPath
     * @return
     */
    public static Server createServer(int port,String contextPath) {
        return null;
    }


    /**
     * 设置相关Jar包的名称
     */
    public static void setTldNames() {

    }


    /**
     * 用做重启applicationContext
     */
    public static void reloadContext(Server server) {

        System.out.println("正在进行重启操作... ...");
    }
}
