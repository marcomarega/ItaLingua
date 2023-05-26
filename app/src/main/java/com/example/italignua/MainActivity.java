package com.example.italignua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ItaLingua);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        StudyBase.initLessons();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_fragment, new LearnFragment()).commit();

        final Button learnButton = (Button)findViewById(R.id.learn_button);
        final Button profileButton = (Button)findViewById(R.id.profile_button);
        final Button practiceButton = (Button)findViewById(R.id.practice_button);

        View.OnClickListener lowerBarOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (view.getId()) {
                    case R.id.learn_button:
                        LearnFragment learnMenuFragment = new LearnFragment();
                        fragmentTransaction.replace(R.id.content_fragment, learnMenuFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.profile_button:
                        ProfileFragment profileFragment = new ProfileFragment();
                        fragmentTransaction.replace(R.id.content_fragment, profileFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.practice_button:
                        Intent intent = new Intent(getApplicationContext(), LessonActivity.class);
                        intent.putExtra("lesson_index", -1);
                        startActivityForResult(intent, 1);
                        break;
                }
            }
        };

        learnButton.setOnClickListener(lowerBarOnClickListener);
        profileButton.setOnClickListener(lowerBarOnClickListener);
        practiceButton.setOnClickListener(lowerBarOnClickListener);
    }
}