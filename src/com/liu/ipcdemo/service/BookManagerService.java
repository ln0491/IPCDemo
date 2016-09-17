package com.liu.ipcdemo.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.liu.ipcdemo.aidl.Book;
import com.liu.ipcdemo.aidl.IBookManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

public class BookManagerService extends Service {
	private static final String TAG = "vivi";
	 private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
	
	public class MyBinder extends IBookManager.Stub{

		@Override
		public List<Book> getBookList() throws RemoteException {
			
			Log.d(TAG, "getBookList");
			 SystemClock.sleep(5000);
	            return mBookList;
		}

		@Override
		public void addBook(Book book) throws RemoteException {
			Log.d(TAG, "addBook");
			 mBookList.add(book);
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return new MyBinder();
	}
	
	@Override
	public void onCreate() {
		
		super.onCreate();
		mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "Ios"));
	}
	
}
