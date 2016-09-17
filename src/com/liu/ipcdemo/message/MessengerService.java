package com.liu.ipcdemo.message;

import com.liu.ipcdemo.utils.MyConstants;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
	
	public static final String TAG="vivi";
	private final Messenger mMessenger = new Messenger(new MessengerHandler());
	private static class MessengerHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
				case MyConstants.MSG_FROM_CLIENT:
					Log.d(TAG, "接收到的消息："+msg.getData().getString("msg"));
					
					
					Messenger client  = msg.replyTo;
					Message replyMessage  = Message.obtain(null, MyConstants.MSG_FROM_SERVICE);
					
					Bundle bundle = new Bundle();
					
					bundle.putString("reply", " 好的，你发来的消息我收到了，稍后回复您。。  这里是服务端");
					
					replyMessage.setData(bundle);
					
					try {
						client.send(replyMessage);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					
					
					break;
				
				default:
					super.handleMessage(msg);
					break;
			}
			
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return mMessenger.getBinder();
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
}
