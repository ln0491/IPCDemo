package com.liu.ipcdemo.utils;

import java.io.File;

import android.nfc.NfcAdapter.CreateBeamUrisCallback;
import android.os.Environment;

public class MyConstants {
	/**
	 * 目录
	 */
	public static final String USER_SER_DIR = Environment.getExternalStorageDirectory().getPath()
	        + "/singwhatiwanna/chapter_2/";
	/**
	 * 文件
	 */
	public static final String CACHE_FILE_PATH = USER_SER_DIR + "usercache";
	
	/**
	 * 创建文件
	 * @Title: createSerFile 
	 * @Description: TODO(创建文件) 
	 * @return   
	 * @return File    返回类型 
	 * @throws 
	 * @date 2016年9月17日 下午6:33:22
	 * @author 刘楠
	 */
	public static File createSerFile() {
		File dir = new File(USER_SER_DIR);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		return new File(CACHE_FILE_PATH);
		
	}
	
}
