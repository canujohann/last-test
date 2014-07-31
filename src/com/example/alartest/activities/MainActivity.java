package com.example.alartest.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.alartest.R;
import com.example.alartest.DB.dao.UserDAO;
import com.example.alartest.services.MyService3;

/**
 * Main Activity
 * @author canu
 * 
 */
public class MainActivity extends Activity {


	//View
	private Button scheduleButton,cancelButton;
	protected ListView lv;
	
	//UserDAO
	private UserDAO userdao;

	//Broadcast Receiver
	private MyReceiver mReceiver;

	//Cursor (ListView)
	public SimpleCursorAdapter mAdapter;	
	protected Cursor mCursor;
	
	//�A�v���R���e�L�X�g
	public Context mContext;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = getBaseContext();

		// �J�n�{�^��
		scheduleButton = (Button) findViewById(R.id.schedule_button);
		scheduleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				scheduleService();
			}
		});

		// ��~�{�^��
		cancelButton = (Button) findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				cancelService();
			}
		});

		//ListView+adapter�ݒ�
		lv = (ListView) findViewById(R.id.listView);

		//���[�U�[�����擾
		userdao = new UserDAO(mContext);
		userdao.open();
		mCursor = userdao.getUsersScoreCursor();
				
		//SimpleCursorAdapter�p
		String[] columns = new String[] { "name", "score" };
		int[] to = new int[] { R.id.person_name, R.id.person_score };

		//adapter�C���X�^���X
		mAdapter = new SimpleCursorAdapter(this, R.layout.row, mCursor, columns,	to);
		lv.setAdapter(mAdapter);

		//Broadcast Receiver���Z�b�g
		setReceiver();

	}

	/* Alarm���J�n */
	protected void scheduleService() {

		Toast.makeText(mContext, "Timer�J�n", Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(mContext, MyService3.class);
		PendingIntent pendingIntent = PendingIntent.getService(mContext, -1,	intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
		alarmManager.setInexactRepeating(AlarmManager.RTC,System.currentTimeMillis(), 1000, pendingIntent);
	}

	/* Alarm���~ */
	protected void cancelService() {

		Toast.makeText(mContext, "Timer��~", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(mContext, MyService3.class);
		PendingIntent pendingIntent = PendingIntent.getService(mContext, -1,	intent, PendingIntent.FLAG_UPDATE_CURRENT);
		AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		userdao.close();
		try {
			cancelService();
		} catch (Exception e) {}
	}

	/*Broadcast Receiver��o�^ */
	private void setReceiver() {
		IntentFilter filter = new IntentFilter("response");
		mReceiver = new MyReceiver();
		registerReceiver(mReceiver, filter);
	}

	/* broadcast receiver �N���X */
	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			try {
				//adapter���X�V
				UserDAO ud = new UserDAO(mContext);
				ud.open();
				Cursor new_cursor = ud.getUsersScoreCursor();
				mAdapter.changeCursor(new_cursor);
				mAdapter.notifyDataSetChanged();
				ud.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
