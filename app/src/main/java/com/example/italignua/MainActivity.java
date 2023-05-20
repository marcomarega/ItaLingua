package com.example.italignua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.Theme_ItaLignua);

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
                        break;
                }
            }
        };

        learnButton.setOnClickListener(lowerBarOnClickListener);
        profileButton.setOnClickListener(lowerBarOnClickListener);
        practiceButton.setOnClickListener(lowerBarOnClickListener);
    }
}