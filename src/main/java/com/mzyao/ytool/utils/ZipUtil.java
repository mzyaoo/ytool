package com.mzyao.ytool.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip包工具类
 */
public class ZipUtil {

    /**
     * 将一组文件写入 ZipOutputStream
     */
    public static void zipFiles(Map<String, String> fileMap, OutputStream outputStream) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(outputStream)) {
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                ZipEntry zipEntry = new ZipEntry(entry.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(entry.getValue().getBytes("UTF-8"));
                zipOut.closeEntry();
            }
        }
    }

    /**
     * 以压缩包的方式下载文件
     *
     * @param response    响应
     * @param optPutStreamMap
     * @param zipName     压缩包名称
     */
    public static void downloadFileForZip(HttpServletResponse response, Map<String, ByteArrayOutputStream> optPutStreamMap, String zipName) throws IOException {
        // 文件名外的双引号处理firefox的空格截断问题
        ZipOutputStream out = null;
        try {
            response.setContentType("application/*");
            response.setHeader("content-disposition", "attachment;filename=" + new String(zipName.getBytes("gb2312"), "ISO8859-1"));
            response.setCharacterEncoding("UTF-8");
            out = new ZipOutputStream(response.getOutputStream());

            for (String fileName : optPutStreamMap.keySet()) {
                ByteArrayOutputStream outputStream = optPutStreamMap.get(fileName);
                ZipEntry entry = new ZipEntry(fileName);
                out.putNextEntry(entry);
                out.write(outputStream.toByteArray());
                // 关闭输入流
                out.closeEntry();
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
