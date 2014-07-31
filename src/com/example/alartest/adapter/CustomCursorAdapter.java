package com.example.alartest.adapter;

import com.example.alartest.R;
import com.example.alartest.R.id;
import com.example.alartest.R.layout;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * CursorAdapterÇåpè≥
 * @author canu
 */
public class CustomCursorAdapter extends CursorAdapter {
	 
    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    } 
    
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.row, parent, false);
        return retView;
    } 
    
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewPersonName = (TextView) view.findViewById(R.id.person_name);
        textViewPersonName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        TextView textViewPersonPIN = (TextView) view.findViewById(R.id.person_score);
        textViewPersonPIN.setText(cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(2))));
    }
}
