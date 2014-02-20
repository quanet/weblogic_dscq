package cn.com.restarter.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhengjianwen on 14-2-14.
 */
public class FileUtils {

    //��·���ļ��������
    public static HttpServletResponse downLoadFilesByUrl(HttpServletRequest request, HttpServletResponse response, String url, String zipname) throws Exception {
        List<File> files = new ArrayList<File>();
        File file = new File(url);
        File[] files1 = file.listFiles();
        for (int i = 0; i < files1.length; i++) {
            files.add(files1[i]);
        }
        return downLoadFiles(files, zipname, request, response);
    }

    //�ļ��������
    public static HttpServletResponse downLoadFiles(List<File> files, String zipname,
                                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            /**������Ͼ�������Ҫ����������ļ���
             * ��������Ѿ�׼��������Ҫ������ļ�*/
            //List<File> files = new ArrayList<File>();

            /**����һ����ʱѹ���ļ���
             * ���ǻ���ļ���ȫ��ע�뵽����ļ���
             * ������ļ�������Զ�����.rar����.zip*/
            File file = new File("c:/" + zipname);
            if (!file.exists()) {
                file.createNewFile();
            }
            response.reset();
            //response.getWriter()
            //�����ļ������
            FileOutputStream fous = new FileOutputStream(file);
            /**����ķ������ǻ��õ�ZipOutputStream����һ�������,
             * �����������ǰ������ת��һ��*/
            ZipOutputStream zipOut
                    = new ZipOutputStream(fous);
            /**����������ܵľ���һ����Ҫ����ļ��ļ��ϣ�
             * ����һ��ZipOutputStream*/
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**ֱ���ļ��Ĵ���Ѿ��ɹ��ˣ�
         * �ļ��Ĵ�����̱��ҷ�װ��FileUtil.zipFile�����̬�����У�
         * �Ժ����ֳ������������ľ������ͻ���д������*/
        // OutputStream out = response.getOutputStream();


        return response;
    }

    /**
     * �ѽ��ܵ�ȫ���ļ����ѹ����
     */
    public static void zipFile
    (List files, ZipOutputStream outputStream) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    public static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        try {
            // ��������ʽ�����ļ���
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // ���response
            response.reset();

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                File f = new File(file.getPath());
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * ����������ļ�����������ļ����д��
     */
    public static void zipFile(File inputFile,
                               ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                /**�����Ŀ¼�Ļ������ǲ���ȡ�����ģ�
                 * ����Ŀ¼�Ĵ�������о���*/
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // ��ѹ���ļ����������
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // �رմ�����������
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
