package com.pdy.util;

/**
 * Created by pengdeyao on 2017/7/25.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * 测试gzip 压缩
 *
 */
public class GzipUtil {

    private static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(GzipUtil.class);

    /**
     * 字符串压缩为GZIP字节数组
     *
     * @param str
     * @return
     */
    public static byte[] compress(String str) {
        return compress(str, GZIP_ENCODE_UTF_8);
    }

    /**
     * 字符串压缩为GZIP字节数组
     *
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            logger.error("gzip compress error.", e);
        }
        return out.toByteArray();
    }

    /**
     * GZIP解压缩
     *
     * @param bytes
     * @return
     */
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            logger.error("gzip uncompress error.", e);
        }

        return out.toByteArray();
    }

    /**
     *
     * @param bytes
     * @return
     */
    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }

    /**
     *
     * @param bytes
     * @param encoding
     * @return
     */
    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            logger.error("gzip uncompress to string error.", e);
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str =
                "蓝牧座垫套夏皮革车垫坐垫起亚K2K345新佳乐智跑狮跑福瑞迪赛拉图专用汽车坐垫座套全包四季 标准版-红色 起亚K3帝豪GS福克斯帕萨特起亚K2赛欧三厢明锐";
        System.out.println("原长度：" + str.getBytes(GZIP_ENCODE_UTF_8).length);
        System.out.println("压缩后字符串：" + GzipUtil.compress(str).length);
        System.out.println("解压缩后字符串：" + new String(GzipUtil.uncompress(GzipUtil.compress(str)),"UTF-8"));
        System.out.println("解压缩后字符串：" + GzipUtil.uncompressToString(GzipUtil.compress(str)));
    }
}