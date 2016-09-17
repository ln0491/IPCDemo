package com.liu.ipcdemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.liu.ipcdemo.aidl.Book;
import com.liu.ipcdemo.mode.User;
import com.liu.ipcdemo.utils.MyConstants;
import com.liu.ipcdemo.utils.MyUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class FirstActivity extends Activity {
	
	protected static final String TAG = "vivi";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		persistToFile();
	}
	
	/**
	 * 
	 * @Title: persistToFile 
	 * @Description: TODO(序列化)    
	 * @return void    返回类型 
	 * @throws 
	 * @date 2016年9月17日 下午6:38:40
	 * @author 刘楠
	 */
	private void persistToFile() {
		
		new Thread() {
			public void run() {
				
				Book book = new Book(123241, "Android 开发艺术探索 ");
				
				User user = new User(111111, "刘楠", true, book);
				
				ObjectOutputStream oos = null;
				
				
				try {
					oos = new ObjectOutputStream(new FileOutputStream(MyConstants.createSerFile()));
					
					
					oos.writeObject(user);
					
					Log.d(TAG, "persistToFile  "+user.toString());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					MyUtils.close(oos);
				}
				
			};
		}.start();
		
	}
	
}
