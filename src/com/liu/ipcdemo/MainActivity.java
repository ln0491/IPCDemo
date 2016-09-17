package com.liu.ipcdemo;

import java.util.List;

import com.liu.ipcdemo.aidl.Book;
import com.liu.ipcdemo.aidl.IBookManager;
import com.liu.ipcdemo.message.MessengerActivity;
import com.liu.ipcdemo.service.BookManagerService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "vivi";
	private IBookManager mRemoteBookManager;
	
	private MyServiceConnection mMyServiceConnection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//toBindService();
		
	}

	private void toBindService() {
		Intent service = new Intent(this,BookManagerService.class);
		
		if(mMyServiceConnection==null){
			mMyServiceConnection = new MyServiceConnection();
		}
		bindService(service, mMyServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	private DeathRecipient mDeathRecipient = new DeathRecipient() {
		
		@Override
		public void binderDied() {
			if(mRemoteBookManager==null){
			return;
			}
			mRemoteBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
			mRemoteBookManager = null;
			//重新绑定 绑定远程Service
			if(mMyServiceConnection==null){
				mMyServiceConnection = new MyServiceConnection();
			}
			Intent service = new Intent(MainActivity.this,BookManagerService.class);
			bindService(service, mMyServiceConnection, Context.BIND_AUTO_CREATE);
		}
	};
	
	private class MyServiceConnection implements ServiceConnection{

		

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.d(TAG, "onServiceConnected"+Thread.currentThread().getName());
			IBookManager iBookManager = IBookManager.Stub.asInterface(service);
			mRemoteBookManager = iBookManager;
			
			try {
				mRemoteBookManager.asBinder().linkToDeath(mDeathRecipient, 0);
				List<Book> bookList = iBookManager.getBookList();
				
				Log.d(TAG, "bookList "+bookList.getClass().getCanonicalName());
				
				for(Book book:bookList){
					Log.d(TAG, " oldquery  "+book.toString());
				}
				
				 Book newBook = new Book(3, "Android进阶");
				 iBookManager.addBook(newBook);
				 List<Book> newBookList = iBookManager.getBookList();
					
					Log.d(TAG, "newBookList "+newBookList.getClass().getCanonicalName());
					
					for(Book book:newBookList){
						Log.d(TAG, " new query  "+book.toString());
					}
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
			mRemoteBookManager  = null;
			Log.d(TAG, "onServiceDisconnected"+Thread.currentThread().getName());
		}
		
	}
	
	 public void onButton1Click(View view) {
	        Toast.makeText(this, "click button1", Toast.LENGTH_SHORT).show();
	        new Thread(new Runnable() {

	            @Override
	            public void run() {
	                if (mRemoteBookManager != null) {
	                    try {
	                        List<Book> newList = mRemoteBookManager.getBookList();
	                        
	                        Log.d(TAG, "click "+newList.toString());
	                    } catch (RemoteException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }).start();
	    }
	 
	 @Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(mMyServiceConnection);
	}
	 
	 
	 public void startFirst(View view){
		 Intent intent = new Intent(this,FirstActivity.class);
		 startActivity(intent);
	 }
	 
	 public void startSecond(View view){
		 Intent intent = new Intent(this,SecondActivity.class);
		 startActivity(intent);
	 }
	 public void startMessenger(View view){
		 Intent intent = new Intent(this,MessengerActivity.class);
		 startActivity(intent);
	 }
	 
	 
	 
}
