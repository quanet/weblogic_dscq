package cn.com.restarter.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileModify {
    public String read(String filePath) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();

        try {

            br = new BufferedReader(new FileReader(filePath));


            while ((line = br.readLine()) != null) {

                if (line.startsWith("if \"%doExitFlag%\"")) {
                   line=line.replace("true","false");
                }
                buf.append(line);
                buf.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // �ر���
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

    public void write(String filePath, String content) {
        BufferedWriter bw = null;

        try {

            bw = new BufferedWriter(new FileWriter(filePath));

            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

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
     * ������
     */
    public static void modify(String filePath) {
        FileModify obj = new FileModify();
        obj.write(filePath, obj.read(filePath));

    }


    public static List<String> readFileByLines(String fileName) {
        List<String> lineStr = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;

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
