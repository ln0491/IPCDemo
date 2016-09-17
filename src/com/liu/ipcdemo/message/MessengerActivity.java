package com.liu.ipcdemo.message;

import com.liu.ipcdemo.R;
import com.liu.ipcdemo.R.layout;
import com.liu.ipcdemo.utils.MyConstants;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerActivity extends Activity {
	
	//private MessengerService mMessengerService;
	private MyServiceConn mMyServiceConn;
	
	private Messenger mMessenger;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messenger);
		
		toBindService();
		
	}
	
	private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());
	
	/**
	 *  用于接收服务端返回的数据
	 * @ClassName: MessengerHandler
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author 刘楠
	 * @date 2016年9月17日 下午9:12:57
	 *
	 */
	private static class MessengerHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case MyConstants.MSG_FROM_SERVICE:
					
					Log.d("vivi", msg.getData().getString("reply"));
					
					break;
				
				default:
					super.handleMessage(msg);
					break;
			}
			
		}
	}
	


	/**
	 * 绑定服务
	 * @Title: toBindService 
	 * @Description: TODO(这里用一句话描述这个方法的作用)    
	 * @return void    返回类型 
	 * @throws 
	 * @date 2016年9月17日 下午7:11:06
	 * @author 刘楠
	 */
	private void toBindService() {
		Intent intent = new Intent(this,MessengerService.class);
		
		if(mMyServiceConn ==null){
			mMyServiceConn =  new MyServiceConn();
		}
		
		//绑定服务
		bindService(intent, mMyServiceConn, Context.BIND_AUTO_CREATE);
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mMyServiceConn!=null){
		unbindService(mMyServiceConn);
		}
	}
	
	
	/**
	 * 连接服务
	 * @ClassName: MyServiceConn
	 * @Description: TODO(这里用一句话描述这个类的作用)
	 * @author 刘楠
	 * @date 2016年9月17日 下午9:13:26
	 *
	 */
	private class MyServiceConn  implements ServiceConnection{

		private static final String TAG = "vivi";

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mMessenger = new Messenger(service);
			
			Message msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT);
			
			Bundle bundle = new Bundle();
			
			bundle.putString("msg", "Hello 这里的客户端......");
			
			msg.setData(bundle);
			msg.replyTo=mGetReplyMessenger;
			
			Log.d(TAG, "onServiceConnected");
			try {
				mMessenger.send(msg);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mMessenger = null;
		}
		
	}
	
}
