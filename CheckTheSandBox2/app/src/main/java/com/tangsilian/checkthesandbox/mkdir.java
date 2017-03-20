package com.tangsilian.checkthesandbox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * Created by tangsilian on 2017-3-20.
 */

public class mkdir {
    public static BufferedReader bufread;
    //指定文件路径和名称
    private static String path = "/data/data/com.tangsilian.checkthesandbox/sanbox.txt";
    private static File filename = new File(path);
    private static String readStr ="";

    public static void creatTxtFile() throws IOException {
        if (!filename.exists()) {
            filename.createNewFile();
            System.err.println(filename + "已创建！");
        }
    }


    /**
     * 追加文件：使用RandomAccessFile
     *
     * @param fileName
     *            文件名
     * @param content
     *            追加的内容
     */
    public static void writeTxtFile( String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(filename, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
