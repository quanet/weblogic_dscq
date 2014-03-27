package cn.com.restarter.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileUtils {


    public static HttpServletResponse downLoadFilesByUrl(HttpServletRequest request, HttpServletResponse response, String url, String zipname) throws Exception {
        List<File> files = new ArrayList<File>();
        File file = new File(url);
        File[] files1 = file.listFiles();
        for (int i = 0; i < files1.length; i++) {
            files.add(files1[i]);
        }
        return downLoadFiles(files, zipname, request, response);
    }


    public static HttpServletResponse downLoadFiles(List<File> files, String zipname,
                                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {

            File file = new File("c:/" + zipname);
            if (!file.exists()) {
                file.createNewFile();
            }
            response.reset();

            FileOutputStream fous = new FileOutputStream(file);

            ZipOutputStream zipOut
                    = new ZipOutputStream(fous);

            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


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

            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

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


    public static void zipFile(File inputFile,
                               ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {

                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);

                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);

                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }

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
