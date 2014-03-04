/*
 * Created on 2005-3-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cn.com.restarter.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    private static Config cfg = null;

    private final static String ERR_MSG = "从配置文件中不能取得传入参数的返回值：";

    private Config() {
        properties = new Properties();
        InputStream is = null;
        try {
            is = Config.class.getResourceAsStream("/config/config.properties");
            properties.load(is);
        } catch (Exception exception) {
            System.out.println("Can't read the properties file. ");
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static Config getInstance() {
        if (cfg == null) {
            cfg = new Config();
        }
        return cfg;
    }

    /**
     * Retun a value for certain key.
     *
     * @param key a certain key define in properties file.
     * @return value
     */
    public String getValue(String key) {
        if (!properties.containsKey(key))
            return null;
        String value = properties.getProperty(key);
        if (value == null) {
            System.out.println("未能在配置文件中找到" + key + "的值或为空！");
        }

        return value;
    }

    public static void main(String args[]) {
    }
}