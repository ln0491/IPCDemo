package com.liu.ipcdemo.aidl;

import java.util.List;
import com.liu.ipcdemo.aidl.Book;

interface IBookManager {
	
	List<Book> getBookList();
	void addBook(in Book book);
}
