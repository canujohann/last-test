package com.example.alartest.DB.dao;


import com.example.alartest.DB.DatabaseHandler;
import com.example.alartest.DB.domain.User;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * User@DAO ƒNƒ‰ƒX
 * @author canu
 */
public class UserDAO {

	private SQLiteDatabase database;
	private DatabaseHandler dbHelper;
	private Context mContext;
	
	private String[] allColumns = {"_id", "name" , "score" };
	
	public UserDAO(Context context) {
		this.dbHelper = DatabaseHandler.getInstance(context);
		this.mContext=context;
	}
	
	/* DB open */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	/*DB close */
	public void close() {
		dbHelper.close();
	}	
	
	/* Get user score */
	public Cursor getUsersScoreCursor() {		
		return database.query(DatabaseHandler.TABLE_USERS, allColumns,null, null , null, null, null);
	}
	
	/* Update user */
	public void update(int UserId){
		database.execSQL("UPDATE " + DatabaseHandler.TABLE_USERS + " SET "
                + "score = score + 1 WHERE _id =" + UserId);
	}
	
	/* cursor‚©‚çUser‚Ö */
	private User cursorToUser(Cursor cursor) {
		User his = new User();		
		if (cursor.getCount() > 0) {			
			his.setId(cursor.getLong(0));
			his.setName(cursor.getString(1));				
			his.setScore(cursor.getInt(2));
		}			
		return his;
	}
	
}
