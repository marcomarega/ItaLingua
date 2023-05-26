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

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_fragment, new LearnFragment()).commit();

                break;
        }
    }
}