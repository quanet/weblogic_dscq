package cn.com.jetty;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.List;

/**
 * @author : lihaoquan
 */
public class JettyFactory {

    private static final String DEFAULT_WEBAPP_PATH = "basic";
    private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault-windows.xml";

    /**
     * 创建Jetty的Server
     * @param port
     * @param contextPath
     * @return
     */
    public static Server createServer(int port,String contextPath) {

        Server server = new Server();
        server.setStopAtShutdown(true);


        SelectChannelConnector connector = new SelectChannelConnector();
        connector.setPort(port);
        connector.setReuseAddress(false);

        server.setConnectors(new Connector[]{connector});

        WebAppContext webAppContext = new WebAppContext(DEFAULT_WEBAPP_PATH,contextPath);
        webAppContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);

        server.setHandler(webAppContext);

        return server;
    }


    /**
     * 设置相关Jar包的名称
     */
    public static void setTldNames(Server server,String... jarNames) {

        WebAppContext context = (WebAppContext) server.getHandler();
        List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
        for (String jarName : jarNames) {
            jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
        }
        context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                StringUtils.join(jarNameExprssions, '|'));

    }


    /**
     * 用做重启applicationContext
     */
    public static void reloadContext(Server server) throws Exception {
        WebAppContext context = (WebAppContext) server.getHandler();
        System.out.println("[INFO] Application reloading");
        context.stop();

        WebAppClassLoader classLoader = new WebAppClassLoader(context);
        classLoader.addClassPath("basic/WEB-INF/classes");
        classLoader.addClassPath("basic/WEB-INF/classes");
        context.setClassLoader(classLoader);
        context.start();
        System.out.println("[INFO] Application reloaded");
    }
}
