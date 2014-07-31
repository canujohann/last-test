package com.example.alartest.services;

import com.example.alartest.DB.dao.UserDAO;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Service�N���X 
 * DB ���X�V����
 * @author canu
 */
public class MyService3 extends IntentService {

	final static String TAG = "ServiceTest3";

	public MyService3() {
		super(TAG);
	}

	/* �T�[�r�X���s */
	@Override
	protected void onHandleIntent(Intent intents) {
		
		//���[�U�[ID�擾(random)
		int x = (int) (Math.random() * 3) ;
		
		//DB�X�V
		UserDAO userdao = new UserDAO(getApplicationContext());
	    userdao.open();
	    userdao.update(x+1);
	    userdao.close();
	    
	    // broadCast���M
		Intent intent = new Intent();
		intent.setAction("response");
		intent.putExtra("user_id", x+1);							
		
		sendBroadcast(intent);
	}
}