package com.wzrd.m.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDCardUtils {

    /**
     * 判断sd卡 是否可以使用 挂载
     *
     * @return
     */
    public static boolean sdState() {
        // 根据 外界环境变量 获取 外部存储的状态 判断这个状态是否等于 已经安装好 安装好 返回 true 否则 false
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡的 根目录
     *
     * @return
     */
    public static String getSDBasePath() {
        if (sdState()) {
            // 获取SD卡的 根目录 的绝对路径
            //Environment.getExternalStorageDirectory().getAbsolutePath()
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * SD卡的 总大小 单位是 兆（M）
     *
     * @return
     */
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static long getSDTotalSize() {
        long size = 0;
        if (sdState()) {
            // 实例化能够获取SD 大小的类
            StatFs statFs = new StatFs(getSDBasePath());
            // 当版本大于 18 使用 直接获取方式
            if (Build.VERSION.SDK_INT >= 18) {
                size = statFs.getTotalBytes();
            } else {
                // 当版本小于18 需要 通过计算获取
                size = statFs.getBlockSize() * statFs.getBlockCount();
            }
            return size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取 sd卡中的 可用的 空间大小 返回值 单位 兆（M）
     *
     * @return
     */
    @SuppressLint("NewApi")
    public static long getSDAvailableSize() {

        long size = 0;
        if (sdState()) {
            StatFs statFs = new StatFs(getSDBasePath());
            if (Build.VERSION.SDK_INT >= 18) {
                size = statFs.getAvailableBytes();
            } else {
                size = statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
            return size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取 sd卡中的 空闲 空间大小 返回值 单位 兆（M）
     *
     * @return
     */
    @SuppressLint("NewApi")
    public static long getSDFreeSize() {

        long size = 0;
        if (sdState()) {
            StatFs statFs = new StatFs(getSDBasePath());
            if (Build.VERSION.SDK_INT >= 18) {
                size = statFs.getFreeBytes();
            } else {
                size = statFs.getFreeBlocks() * statFs.getBlockSize();
            }
            return size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 将文件存储到 9大公有目录中
     *
     * @param bytes    文件数组
     * @param type     文件存放的公有目录类型
     * @param fileName 存放文件的名称
     * @return
     */

    public static boolean saveFileToPublicSDCard(byte[] bytes, String type, String fileName) {

        if (sdState()) {
            // 获取 共有目录
            File file = Environment.getExternalStoragePublicDirectory(type);
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            try {
                fos = new FileOutputStream(new File(file, fileName));
                bos = new BufferedOutputStream(fos);
                bos.write(bytes, 0, bytes.length);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    /**
     * 将文件存到自定义文件夹中
     *
     * @param bytes    文件数组
     * @param path     存放的文件路径
     * @param fileName 文件名
     * @return
     */
    public static boolean saveFileToCustomSdCard(byte[] bytes, String path, String fileName) {
        if (sdState()) {
            // 根据自定义的 路径 获取文件
            File file = new File(getSDBasePath() + File.separator + path);

            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;

            try {
                fos = new FileOutputStream(new File(file, fileName));
                bos = new BufferedOutputStream(fos);
                bos.write(bytes, 0, bytes.length);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 根据路径取数据
     *
     * @param filePath 存放文件的路径
     * @return
     */
    public static byte[] getDataFromSdCard(String filePath) {
        if (sdState()) {
            BufferedInputStream bis = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            File file = new File(filePath);
            if (file.exists()) {
                try {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    int len = 0;
                    byte[] buffer = new byte[1024];

                    while ((len = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                        bos.flush();
                    }

                    return bos.toByteArray();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bis != null) {
                            bis.close();
                        }
                        if (bos != null) {
                            bos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    /**
     * 将文件保存到 sd 卡中的 私有目录中files
     *
     * @param context  上下文对象
     * @param fileName 文件名
     * @param bytes    文件数组
     * @param type
     * @return
     */
    public static boolean saveFileToPrivate(Context context, String fileName, byte[] bytes, String type) {
        if (sdState()) {

            File file = context.getExternalFilesDir(type);
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(bytes, 0, bytes.length);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 存放数据到 Sd卡中私有目录中的 cache文件夹下
     *
     * @param context
     * @param bytes
     * @param fileName
     * @return
     */
    public static boolean saveFileToPrivateCache(Context context, byte[] bytes, String fileName) {
        if (sdState()) {
            File file = context.getExternalCacheDir();
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(bytes, 0, bytes.length);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 将图片存入 cache中
     *
     * @param context
     * @param bitmap
     * @param fileName
     * @return
     */
    public static boolean saveBitmapToPrivateCache(Context context, Bitmap bitmap, String fileName) {
        if (sdState()) {
            File file = context.getExternalCacheDir();
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                if (fileName != null && (fileName.contains(".png") || fileName.contains(".PNG"))) {
                    /*
                     * 参数一：图片格式 参数二：图片压缩质量 参数三：写入的流
					 */
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                }

                bos.flush();
                return true;

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 从SD卡中 取图片
     *
     * @param path
     * @param context
     * @return
     */
    public static Bitmap getBitmapFromSd(String path, Context context) {
        if (sdState()) {
            return BitmapFactory.decodeFile(path);
        }
        return null;
    }

}
