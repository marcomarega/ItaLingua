package com.example.italignua;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class LessonAdapter extends ArrayAdapter<Lesson> {

    public LessonAdapter(Context context, Lesson[] arr) {
        super(context, R.layout.lesson_adapter_item, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Lesson lesson = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lesson_adapter_item, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.title_text_view)).setText(lesson.getTitle());
        ((TextView) convertView.findViewById(R.id.xp_text_view)).setText(lesson.getXp() + "xp");

        DBHelper dataBaseHelper = new DBHelper(getContext());
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();

        Cursor cursor = database.query(DBHelper.LESSONS_TABLE_NAME, null, DBHelper.LESSONS_COLUMN_LESSON_ID + " = " + position, null, null, null, null);
        if (cursor.getCount() >= 1) {
            ((CheckBox) convertView.findViewById(R.id.lesson_passed)).setChecked(true);
        }
        else {
            ((CheckBox) convertView.findViewById(R.id.lesson_passed)).setChecked(false);
        }

        return convertView;
    }
}