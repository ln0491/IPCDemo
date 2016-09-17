package com.liu.ipcdemo.mode;

import java.io.Serializable;

import com.liu.ipcdemo.aidl.Book;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Serializable, Parcelable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	
	private static final long serialVersionUID = -3238601306537398491L;
	public int userId;
    public String userName;
    public boolean isMale;
	
    public Book book;
    
    
	
	public User() {
		super();
	}

	public User(int userId, String userName, boolean isMale,Book book) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.isMale = isMale;
		this.book = book;
	}

	

	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(userId);
		out.writeString(userName);
		out.writeInt(isMale?1:0);
		out.writeParcelable(book, 0);
		
	}
	
	private static final Parcelable.Creator<User> CREATOR = new Creator<User>() {
		
		@Override
		public User[] newArray(int size) {
			// TODO Auto-generated method stub
			return new User[size];
		}
		
		@Override
		public User createFromParcel(Parcel in) {
			// TODO Auto-generated method stub
			return new User(in);
		}
	};
	
	public User(Parcel in) {
		userId = in.readInt();
		userName = in.readString();
		isMale = in.readInt()==1? true : false;
		book = in .readParcelable(Thread.currentThread().getContextClassLoader());
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", isMale=" + isMale + ", book=" + book + "]";
	}
	
	
	
}
