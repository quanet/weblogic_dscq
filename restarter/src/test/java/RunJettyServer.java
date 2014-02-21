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
    private static final int PORT = 9394;//应用端口
    private static final String CONTEXT = "/basic";//上下文名称

    public static void main(String[] args) throws Exception {

        Server server = JettyFactory.createServer(PORT,CONTEXT);//创建服务
        JettyFactory.setTldNames(server, new String[] {"spring-webmvc"});//设置JARS...

        try {
            //当控制台中输入回车的时候,应用重启

            server.start();
            System.out.println("前台操作可进入 http://localhost:" + PORT + CONTEXT+"/restartWeblogic.jsp");
            System.out.println("输入回车即可重启本工具");

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
