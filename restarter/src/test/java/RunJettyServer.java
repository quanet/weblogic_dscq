import cn.com.jetty.JettyFactory;
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
    private static final int PORT = 8080;
    private static final String CONTEXT = "/basic";
    private static final String[] TLD_JAR_NAMES = new String[] {"sitemesh", "spring-webmvc"};



    public static void main(String[] args) throws Exception {

        Server server = JettyFactory.createServer(PORT,CONTEXT);
        JettyFactory.setTldNames(server, TLD_JAR_NAMES);

        try {
            //当控制台中输入回车的时候,应用重启

            server.start();
            System.out.println("Jetty Server running at http://localhost:" + PORT + CONTEXT);
            System.out.println("Hit Enter to reload the application quickly");

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
