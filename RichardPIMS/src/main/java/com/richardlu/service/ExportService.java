package com.richardlu.service;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by Richard on 14-1-28.
 */
public class ExportService {

    private static String date = Integer.toString(Calendar.DATE);
    private static String ASSETS_NAME = "PatientDB.db";
    private static final String OUT_DB_NAME = date+"PatientDB.db";
    private static String DB_PATH = "/data/data/com.richardlu.richardpims/databases/";

    private static String fromFileUrl = DB_PATH+ASSETS_NAME;
    private static String toFileUrl = getSDPath()+"/PIMS/"+OUT_DB_NAME;

    private static String urlNull = "原文件路径不存在";
    private static String isFile = "原文件不是文件";
    private static String canRead = "原文件不能读";
    private static String notWrite = "备份文件不能写入";
    private static String message = "OK";
    private static String cFromFile = "创建原文件出错:";
    private static String ctoFile = "创建备份文件出错:";

    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            return sdDir.toString();
        }else
        {
            return "false";
        }

    }


    public static String copyFile() {
        File fromFile = null;
        File toFile = null;
        if (!toFileUrl.equals("false")) {
            try {
                fromFile = new File(fromFileUrl);
            } catch (Exception e) {
                return cFromFile + e.getMessage();
            }
            try {
                toFile = new File(toFileUrl);
            } catch (Exception e) {
                return ctoFile + e.getMessage();
            }
            if (!fromFile.exists()) {
                return urlNull;
            }
            if (!fromFile.isFile()) {
                return isFile;
            }
            if (!fromFile.canRead()) {
                return canRead;
            }
            // 复制到的路径如果不存在就创建
            if (!toFile.getParentFile().exists()) {
                toFile.getParentFile().mkdirs();
            }
            if (toFile.exists()) {
                toFile.delete();
            }
//            if (!toFile.canWrite()) {
//                return notWrite;
//            }
            try {
                java.io.FileInputStream fosfrom = new java.io.FileInputStream(fromFile);
                java.io.FileOutputStream fosto = new FileOutputStream(toFile);
                byte bt[] = new byte[1024];
                int c;
                while ((c = fosfrom.read(bt)) > 0) {
                    fosto.write(bt, 0, c); // 将内容写到新文件当中
                }
                //关闭数据流
                fosfrom.close();
                fosto.close();
            } catch (Exception e) {
                e.printStackTrace();
                message = "false";

            }
        } else {
            message = "false";
        }
        return message;
    }

    /**
     *
     * @param fromFileUrl
     *            旧文件地址和名称
     * @param toFileUrl
     *            新文件地址和名称
     * @return 返回备份文件的信息，ok是成功，其它就是错误
     */



    public static String copyFile(String fromFileUrl, String toFileUrl) {
        File fromFile = null;
        File toFile = null;
        try {
            fromFile = new File(fromFileUrl);
        } catch (Exception e) {
            return cFromFile + e.getMessage();
        }
        try {
            toFile = new File(toFileUrl);
        } catch (Exception e) {
            return ctoFile + e.getMessage();
        }
        if (!fromFile.exists()) {
            return urlNull;
        }
        if (!fromFile.isFile()) {
            return isFile;
        }
        if (!fromFile.canRead()) {
            return canRead;
        }
        // 复制到的路径如果不存在就创建
        if (!toFile.getParentFile().exists()) {
            toFile.getParentFile().mkdirs();
        }
        if (toFile.exists()) {
            toFile.delete();
        }
        try {
            java.io.FileInputStream fosfrom = new java.io.FileInputStream(fromFile);
            java.io.FileOutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c); // 将内容写到新文件当中
            }
            //关闭数据流
            fosfrom.close();
            fosto.close();
        } catch (Exception e) {
            e.printStackTrace();
            message = "备份失败!";

        }
        return message;
    }


}
