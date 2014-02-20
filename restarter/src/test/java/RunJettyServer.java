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
    private static final String[] TLD_JAR_NAMES = new String[] {};



    public static void main(String[] args) throws Exception {

        Server server = JettyFactory.createServer(PORT,CONTEXT);

        try {
            //当控制台中输入回车的时候,应用重启
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
