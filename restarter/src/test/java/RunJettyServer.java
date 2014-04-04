import cn.com.jetty.JettyFactory;
import cn.com.restarter.config.Config;
import org.eclipse.jetty.server.Server;

/**
 * @author : lihaoquan
 *
 * 运行Jetty服务器
 *
 */
public class RunJettyServer {

    /**
     * 相关启动参数
     */
    private static final int PORT =Integer.parseInt( Config.getInstance().getValue("PORT") ) ;//应用端口
    private static final String CONTEXT = "/basic";//上下文名称

    public static void main(String[] args) throws Exception {

        Server server = JettyFactory.createServer(PORT,CONTEXT);//创建服务
        JettyFactory.setTldNames(server, new String[] {"spring-webmvc"});//设置JARS...

        try {
            //当控制台中输入回车的时候,应用重启

            server.start();
            System.out.println("application start success!");
            System.out.println("url: http://localhost:" + PORT + CONTEXT+"/web/task/restartWeblogic.action");
            System.out.println("press enter will restart this application!");

            while(true) {
                char c = (char) System.in.read();
                if(c=='\n') {
                    JettyFactory.reloadContext(server);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
