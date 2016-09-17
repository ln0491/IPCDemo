package com.liu.ipcdemo.testaidl;


import java.util.List;

import com.liu.ipcdemo.aidl.Book;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

public interface IBookManager extends IInterface {
	/**
	 * Binder唯一标识
	 */
	static final String DESCRIPTOR = "com.liu.ipcdemo.testaidl.IBookManager";
	
	/**
	 * ID代表2个方法
	 */
	static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION+0;
	static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION+1;
	
	/**
	 * 
	 * @Title: getBookList 
	 * @Description: TODO(要实现的2个方法) 
	 * @throws RemoteException   
	 * @return List<Book>    返回类型 
	 * @date 2016年9月17日 下午4:53:11
	 * @author 刘楠
	 */
	public List<Book> getBookList() throws RemoteException ;
	public List<Book> addBook(Book book) throws RemoteException ;
	
}
