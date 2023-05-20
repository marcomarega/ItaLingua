package com.example.italignua;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    class IntentRunnable implements Runnable {
        @Override
        public void run() {
            try{
                Thread.sleep(1000); //Приостанавливает поток на 1 секунду
            } catch(InterruptedException e) {}
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setTheme(R.style.splashScreenTheme);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread=new Thread(new IntentRunnable());
        thread.start();
    }
}