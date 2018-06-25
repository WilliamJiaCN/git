package com.hivescm.tms.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 文件处理工具类
 * @author 杨彭伟
 * @date 2017-12-11 19:19
 */
public class FileUtils {

    /**
     * 获取类路径下的文件
     * @param path 类路径 classpath: file.txt
     * @return
     */
    public static File getClassPathFile(String path) {
        try {
            return ResourceUtils.getFile(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取类路径下的资源
     * @param path 类路径 test.txt
     * @return
     */
    public static Resource getResource(String path) {
        return new ClassPathResource(path);
    }

    /**
     * 读取文件中的内容到字符串
     * @param file 文件
     * @return
     */
    public static String getFileString(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }




}
