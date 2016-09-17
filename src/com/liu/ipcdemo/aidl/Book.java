package com.liu.ipcdemo.aidl;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable,Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	
	private static final long serialVersionUID = 492030337291339100L;
	public int bookId;
	public String bookName;
	
	public Book() {
		super();
	}
	
	public Book(int bookId, String bookName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(bookId);
		dest.writeString(bookName);
	}
	
	public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
		
		@Override
		public Book[] newArray(int size) {
			return new Book[size];
		}
		
		@Override
		public Book createFromParcel(Parcel in) {
			
			return new Book(in);
		}
	};
	
	private Book(Parcel in) {
		
		bookId = in.readInt();
		
		bookName = in.readString();
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + "]";
	}
	
}
