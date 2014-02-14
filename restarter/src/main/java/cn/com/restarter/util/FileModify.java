package cn.com.restarter.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 修改文件
 */
public class FileModify {

    /**
     * 读取文件内容
     *
     * @param filePath
     * @return
     */
    public String read(String filePath) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();

        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                // 此处根据实际需要修改某些行的内容
                if (line.startsWith("if \"%doExitFlag%\"")) {
                   line=line.replace("true","false");
                }
                buf.append(line);
                buf.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }

        return buf.toString();
    }

    /**
     * 将内容回写到文件中
     *
     * @param filePath
     * @param content
     */
    public void write(String filePath, String content) {
        BufferedWriter bw = null;

        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }

    /**
     * 主方法
     */
    public static void modify(String filePath) {
        FileModify obj = new FileModify();
        obj.write(filePath, obj.read(filePath)); // 读取修改文件
    }

    //一行一行读取ini文件应用路径
    public static List<String> readFileByLines(String fileName) {
        List<String> lineStr = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                String[] strarray = tempString.split("=");
                lineStr.add(strarray[1].replace("\\","//"));
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return lineStr;
    }
}
