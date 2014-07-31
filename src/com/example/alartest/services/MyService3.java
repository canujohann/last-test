package com.example.alartest.services;

import com.example.alartest.DB.dao.UserDAO;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Serviceクラス 
 * DB を更新する
 * @author canu
 */
public class MyService3 extends IntentService {

	final static String TAG = "ServiceTest3";

	public MyService3() {
		super(TAG);
	}

	/* サービス実行 */
	@Override
	protected void onHandleIntent(Intent intents) {
		
		//ユーザーID取得(random)
		int x = (int) (Math.random() * 3) ;
		
		//DB更新
		UserDAO userdao = new UserDAO(getApplicationContext());
	    userdao.open();
	    userdao.update(x+1);
	    userdao.close();
	    
	    // broadCast送信
		Intent intent = new Intent();
		intent.setAction("response");
		intent.putExtra("user_id", x+1);							
		
		sendBroadcast(intent);
	}
}