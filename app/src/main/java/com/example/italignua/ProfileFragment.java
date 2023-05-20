package com.example.italignua;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DBHelper dbHelper = new DBHelper(getContext());
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Cursor xpCursor = database.query(DBHelper.LESSONS_TABLE_NAME, null, DBHelper.LESSONS_COLUMN_ID + " = -1", null, null, null, null);
        int xp = 0;
        if (xpCursor.moveToFirst()) {
            int xpIndex = xpCursor.getColumnIndex(DBHelper.LESSONS_COLUMN_LESSON_ID);
            xp = xpCursor.getInt(xpIndex);
        }
        xpCursor.close();
        ((TextView)view.findViewById(R.id.xp_count)).setText(Integer.toString(xp));

        Cursor wordsCursor = database.query(DBHelper.WORDS_TABLE_NAME, null, null, null, null, null, null, null);
        int wordsCount = wordsCursor.getCount();
        wordsCursor.close();
        ((TextView)view.findViewById(R.id.words_count)).setText(Integer.toString(wordsCount));

        return view;
    }
}