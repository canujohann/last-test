package com.example.alartest.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper helper
 * @author canu
 */
public class DatabaseHandler extends SQLiteOpenHelper {

		//singleton
		private static DatabaseHandler mInstance = null;
		private Context mCxt;
		// DBバージョン
		private static final int DATABASE_VERSION = 5;
		// DB名
		private static final String DATABASE_NAME = "myDatabase";
		// テーブル名
		public static final String TABLE_USERS = "users";

		
		/* Constructor */
		private  DatabaseHandler(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.mCxt = context;
		}
		
		
		/* singleton取得 */
		public static DatabaseHandler getInstance(Context ctx) {
	        if (mInstance == null) {
	            mInstance = new DatabaseHandler(ctx.getApplicationContext());
	        }
	        return mInstance;
		}


		/* DB作成 */
		@Override
		public void onCreate(SQLiteDatabase db) {

			//カテゴリテーブル
			String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
					+ "_id integer primary key autoincrement not null, name TEXT, score INTEGER )";
			db.execSQL(CREATE_USERS_TABLE);
			
			String[] users = {"ichimura","matsukawa","canu"};
			
			for(int i=0; i< users.length; i++){
				ContentValues values = new ContentValues();
				values.put("name", users[i]);
				values.put("score", 1);			
				db.insert(TABLE_USERS, null, values);
			}
			
		}

		/* DATABASE_VERSION バージョンアップ */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
			onCreate(db);
		}
}
