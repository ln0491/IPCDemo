package com.liu.ipcdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import com.liu.ipcdemo.mode.User;
import com.liu.ipcdemo.utils.MyConstants;
import com.liu.ipcdemo.utils.MyUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class SecondActivity extends Activity {
	
	private static final String TAG = "vivi";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		recoverFromFile();
		
		
		
	}


	private void recoverFromFile() {
		ObjectInputStream ois = null;
		
		
		
		try {
			ois = new ObjectInputStream(new FileInputStream(MyConstants.createSerFile()));
		
		
		
			User user  = (User) ois.readObject();
			
			Log.d(TAG, "recoverFromFile   "+  user.toString());
			
		
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			MyUtils.close(ois);
		}
	}
}
