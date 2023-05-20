package com.example.italignua;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashSet;

public class LearnFragment extends Fragment {
    DBHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        dataBaseHelper = new DBHelper(getContext());

        ListView listView = view.findViewById(R.id.learn_list);

        Object[] lessons_obj = StudyBase.getLessons().toArray();
        Lesson[] lessons = new Lesson[lessons_obj.length];
        for (int i = 0; i < lessons.length; i++) {
            lessons[i] = (Lesson)lessons_obj[i];
        }

        LessonAdapter adapter = new LessonAdapter(this.getContext(), lessons);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Intent intent = new Intent(getContext(), LessonActivity.class);
                intent.putExtra("lesson_index", position);
                startActivityForResult(intent, 1);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();

                double accuracy = data.getDoubleExtra("accuracy", 1);
                int lessonIndex = data.getIntExtra("lessonIndex", 0);
                int xp = StudyBase.getLessons().get(lessonIndex).getXp();
                HashSet<String> words = StudyBase.getLessons().get(lessonIndex).getWords();

                SQLiteDatabase database = dataBaseHelper.getWritableDatabase();

                for (String word : words) {
                    if (database.query(DBHelper.WORDS_TABLE_NAME, null, DBHelper.WORDS_COLUMN_WORD + " = \"" + word + "\"", null, null, null, null).getCount() != 0) continue;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DBHelper.WORDS_COLUMN_WORD, word);

                    database.insert(DBHelper.WORDS_TABLE_NAME, null, contentValues);
                }

                Cursor cursor = database.query(DBHelper.LESSONS_TABLE_NAME, new String[]{DBHelper.LESSONS_COLUMN_LESSON_ID}, "_id = -1", null, null, null, null);
                int previous_xp = 0;
                if (cursor.moveToFirst()) {
                    int xpIndex = cursor.getColumnIndex(DBHelper.LESSONS_COLUMN_LESSON_ID);
                    previous_xp = cursor.getInt(xpIndex);
                }
                cursor.close();

                database.delete(DBHelper.LESSONS_TABLE_NAME, "_id = -1", null);

                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.LESSONS_COLUMN_ID, -1);
                contentValues.put(DBHelper.LESSONS_COLUMN_LESSON_ID, previous_xp + xp);
                database.insert(DBHelper.LESSONS_TABLE_NAME, null, contentValues);

                if (database.query(DBHelper.LESSONS_TABLE_NAME, null, DBHelper.LESSONS_COLUMN_LESSON_ID + " = " + lessonIndex, null, null, null, null).getCount() == 0) {
                    contentValues = new ContentValues();
                    contentValues.put(DBHelper.LESSONS_COLUMN_LESSON_ID, lessonIndex);
                    database.insert(DBHelper.LESSONS_TABLE_NAME, null, contentValues);
                }

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, new LearnFragment()).commit();

                break;
        }
    }
}