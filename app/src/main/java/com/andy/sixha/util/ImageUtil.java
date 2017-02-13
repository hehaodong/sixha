package com.andy.sixha.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.util
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-24 16:50
 */
public class ImageUtil {
    public static ImageUtil instance;
    private static final String SDCARD_CACHE_IMG_PATH = Environment
            .getExternalStorageDirectory().getPath() + "/llc/images/";
    public  static ImageUtil getInstance(){
        instance = new ImageUtil();
        return instance;
    }
    /**
     * 保存图片到SD卡
     * @param imagePath
     * @param buffer
     * @throws IOException
     */
    public static void saveImage(String imagePath, byte[] buffer)
            throws IOException {
        File f = new File(imagePath);
        if (f.exists()) {
            return;
        } else {
            File parentFile = f.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagePath);
            fos.write(buffer);
            fos.flush();
            fos.close();
        }
    }

    /**
     * 从SD卡加载图片
     * @param imagePath
     * @return
     */
    public static Bitmap getImageFromLocal(String imagePath){
        File file = new File(imagePath);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            file.setLastModified(System.currentTimeMillis());
            return bitmap;
        }
        return null;
    }

    /**
     * Bitmap转换到Byte[]
     * @param bm
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bas);
        return bas.toByteArray();
    }

    /**
     * 从本地或者服务端加载图片
     * @return
     * @throws IOException
     */
    public static Bitmap loadImage(final String imagePath,final String imgUrl,final ImageCallback callback) {
        Bitmap bitmap = getImageFromLocal(imagePath);
        if(bitmap != null){
            return bitmap;
        }else{//从网上加载
            final Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.obj!=null){
                        Bitmap bitmap = (Bitmap) msg.obj;
                        callback.loadImage(bitmap, imagePath);
                    }
                }
            };

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url;
                        url = new URL(imgUrl);
                        Log.e("图片加载", imgUrl);
                        URLConnection conn = url.openConnection();
                        conn.connect();
                        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(),8192) ;
                        Bitmap bitmap = BitmapFactory.decodeStream(bis);
                        //保存文件到sd卡
                        saveImage(imagePath,bitmap2Bytes(bitmap));
                        Message msg = handler.obtainMessage();
                        msg.obj = bitmap;
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        Log.e(ImageUtil.class.getName(), "保存图片到本地存储卡出错！");
                    }
                }
            };
//            ThreadPoolManager.getInstance().addTask(runnable);
        }
        return null;
    }

    // 返回图片存到sd卡的路径
    public static String getCacheImgPath() {
        return SDCARD_CACHE_IMG_PATH;
    }

    public static String md5(String paramString) {
        String returnStr;
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            returnStr = byteToHexString(localMessageDigest.digest());
            return returnStr;
        } catch (Exception e) {
            return paramString;
        }
    }

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    public static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     *
     * @author Mathew
     *
     */
    public interface ImageCallback{
        public void loadImage(Bitmap bitmap, String imagePath);
    }
    /**
     * 调用系统自带裁图工具
     *
     * @param activity
     * @param size
     * @param uri
     * @param action
     * @param cropFile
     */
    public static void cropPicture(Activity activity, int size, Uri uri, int action, File cropFile) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");
            // 返回格式
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            intent.putExtra("crop", true);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", size);
            intent.putExtra("outputY", size);
            intent.putExtra("scale", true);
            intent.putExtra("scaleUpIfNeeded", true);
            intent.putExtra("cropIfNeeded", true);
            intent.putExtra("return-data", false);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropFile));
            activity.startActivityForResult(intent, action);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用系统自带裁图工具
     * outputX = 250
     * outputY = 250
     *
     * @param activity
     * @param uri
     * @param action
     * @param cropFile
     */
    public static void cropPicture(Activity activity, Uri uri, int action, File cropFile) {
        cropPicture(activity, 250, uri, action, cropFile);
    }

    /**
     * 调用系统自带裁图工具
     * 并保存文件
     * outputX = 250
     * outputY = 250
     *
     * @param activity
     * @param uri
     * @param action
     * @param appName
     * @param application
     * @return
     */
    public static File cropPicture(Activity activity, Uri uri, int action, String appName, Application application) {
        File resultFile = createImageFile(appName, application);
        cropPicture(activity, 250, uri, action, resultFile);
        return resultFile;
    }

    /**
     * 创建图片文件
     *
     * @param appName
     * @param application
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static File createImageFile(String appName, Application application) {
        File folder = createImageFileInCameraFolder(appName, application);
        String filename = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
        return new File(folder, filename + ".jpg");
    }

    /**
     * 创建图片文件夹
     *
     * @param appName
     * @param application
     * @return
     */
    public static File createImageFileInCameraFolder(String appName, Application application) {
        String folder = ImageUtil.createAPPFolder(appName, application);
        File file = new File(folder, "image");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 创建App文件夹
     *
     * @param appName
     * @param application
     * @return
     */
    public static String createAPPFolder(String appName, Application application) {
        return createAPPFolder(appName, application, null);
    }
    /**
     * 创建App文件夹
     *
     * @param appName
     * @param application
     * @param folderName
     * @return
     */
    public static String createAPPFolder(String appName, Application application, String folderName) {
        File root = Environment.getExternalStorageDirectory();
        File folder;
        /**
         * 如果存在SD卡
         */
//        if (DeviceUtil.isSDCardAvailable() && root != null) {
        if (root != null) {
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        } else {
            /**
             * 不存在SD卡，就放到缓存文件夹内
             */
            root = application.getCacheDir();
            folder = new File(root, appName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        if (folderName != null) {
            folder = new File(folder, folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return folder.getAbsolutePath();
    }

}
