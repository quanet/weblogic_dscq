/**
 * @author : lihaoquan
 *
 * 使用嵌入的Jetty运行本应用
 *
 */
public class RunServer {


    /**
     * 创建本地的服务器
     */
    public static void createLocalServer() {

    }

    /**
     *设置Jars包的名称
     */
    public static void setJarNames() {

    }


    /**
     * 上下文重启
     */
    public static void reloadContext() {

    }


    /**
     * 快速启动服务
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println("------> Test RunServer");
        // 等待用户输入回车重载应用.
        while (true) {
            char c = (char) System.in.read();
            if (c == '\n') {
                System.out.println("Reloading");
            }
        }
    }
}
