package com.gsm.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public class FileUtils {
    /**
     * 校验目录是否存在，并创建
     *
     * @param directory
     * @return
     */
    public static boolean prepareDirectory(String directory) {
        File dir = new File(directory);
        //如果目录不存在，创建目录
        if (!dir.exists()) {
            dir.mkdirs();
            return true;
        }
        return false;
    }

    /**
     * 存储单个 MultipartFile 文件到 dir 目录下
     * 默认：文件名是 file 的文件名
     *
     * @param file      文件
     * @param directory 目录
     * @return 是否成功
     */
    public static boolean storeFile(MultipartFile file, String directory) throws IOException {
        String originalFilename = file.getOriginalFilename();
        return storeFileByName(file, directory, originalFilename);
    }

    /**
     * 存储单个 MultipartFile 文件到 dir 目录下
     * 文件名是指定名，确保唯一
     *
     * @param file      文件
     * @param directory 目录
     * @param fileName  文件名
     * @return 是否成功
     */
    public static boolean storeFileByName(MultipartFile file, String directory, String fileName) throws IOException {
        //校验目录
        prepareDirectory(directory);
        //存储文件
        File f = new File(directory, fileName);
        FileOutputStream fos = new FileOutputStream(f, true);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return true;
    }

    /**
     * 存储多个 MultipartFile 文件到 dir 目录下
     * 默认：文件名是 files 的文件名
     *
     * @param files     文件
     * @param directory 目录
     * @return 是否成功
     */
    public static boolean storeFiles(MultipartFile[] files, String directory) throws IOException {
        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getOriginalFilename();
        }
        return storeFilesByNames(files, directory, fileNames);
    }

    /**
     * 存储多个 MultipartFile 文件到 dir 目录下
     * 默认：文件名是指定的文件名
     *
     * @param files     文件
     * @param directory 目录
     * @param fileNames 文件名
     * @return 是否成功
     * @throws IOException
     */
    public static boolean storeFilesByNames(MultipartFile[] files, String directory, String[] fileNames) throws IOException {
        prepareDirectory(directory);
        //文件数量和文件名数量应该相等，容错允许文件数量大于文件名数量
        if (files.length != fileNames.length || files.length < fileNames.length) {
            return false;
        }
        File dir = null;
        FileOutputStream fos = null;
        for (int i = 0; i < files.length; i++) {
            dir = new File(directory, fileNames[i]);
            fos = new FileOutputStream(dir);
            fos.write(files[i].getBytes());
            fos.flush();
            fos.close();
        }
        return true;
    }

    /**
     * 删除文件夹
     * @param folderName 文件夹全路径名 例如：D:\aa\bb\cc 删除 cc 文件夹
     */
    public static boolean deleteFolder(String folderName) {
        File folder = new File(folderName);
        //如果是文件夹
        if (folder.isDirectory()) {
            /**
             * 删除该文件夹下的文件
             */
            String[] list = folder.list();
            // 字符串拼接优化
            StringBuilder sb = new StringBuilder();
            //遍历删除文件夹下的文件
            for (String sonFileName : list) {
                sb = sb.append(folderName).append(File.separator).append(sonFileName);
                File file = new File(sonFileName);
                file.delete();
                sb.setLength(0);
            }
            /**
             * 删除该文件夹下的文件夹
             */
            File[] files = folder.listFiles();
            // i 是该文件夹下的  文件名和文件夹名
            for (File i : files) {
                if (i.isDirectory()) {
                    //递归，因为要删除文件夹，必须确保文件夹是为空，不能删除有子文件的文件夹
                    FileUtils.deleteFolder(i.toString());
                    i.delete();
                }
                //删除文件
                if (i.isFile()) {
                    FileUtils.deleteFile(i.toString());
                }
            }
        }
        //如果传入的是文件名，不让删除
        if (folder.isFile()) {
            System.out.println("传入的是文件名");
            return false;
        }
        return false;
    }

    /**
     * 删除文件
     * @param fileName 文件全路径名 例如：D:\aa\bb\cc\a.jpg 删除 a.jpg 文件
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件或者文件夹
     * @param folderNameOrFileName  全路径名
     * @return
     */
    public static boolean deleteFolderOrFile(String folderNameOrFileName){
        File file = new File(folderNameOrFileName);
        if (file.isDirectory()) {
            FileUtils.deleteFolder(folderNameOrFileName);
        }
        if (file.isFile()) {
            FileUtils.deleteFile(folderNameOrFileName);
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
