package com.example.italignua;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;

public class LessonActivity extends AppCompatActivity {
    private String state;
    private int lessonIndex;
    private int taskCount = 0;
    private int correctTaskCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        lessonIndex = getIntent().getIntExtra("lesson_index", 0);
        Lesson lesson = StudyBase.getLessons().get(lessonIndex);

        Exercise[] exercises = lesson.getExercises();
        if (Objects.equals(exercises[0].getType(), "New"))
            state = "New";
        else
            state = "Task";
        initExerciseInterface(exercises[0]);

        ArrayDeque<Exercise> exerciseDdeque = new ArrayDeque<>();
        for (Exercise exercise : exercises) {
            exerciseDdeque.offer(exercise);
        }

        final Button exercise_button = (Button)findViewById(R.id.exercise_button);
        exercise_button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (Objects.equals(state, "New")) {
                    exerciseDdeque.poll();
                    if (exerciseDdeque.isEmpty()) {
                        finishAndResult();
                        return;
                    }
                    else if (Objects.equals(exerciseDdeque.peek().getType(), "New"))
                        state = "New";
                    else
                        state = "Task";
                    initExerciseInterface(exerciseDdeque.peek());
                }
                else if (Objects.equals(state, "Task")) {
                    Exercise exercise = exerciseDdeque.peek();
                    String correctAnswer = "";
                    if (Objects.equals(exercise.getType(), "TaskTranslate")) {
                        correctAnswer = exercise.getTranslate();
                    }
                    if (Objects.equals(exercise.getType(), "TaskItalian")) {
                        correctAnswer = exercise.getItalian();
                    }
                    String userAnswer = ((EditText)findViewById(R.id.exercise_plaintext)).getText().toString();
                    correctAnswer = correctAnswer.toLowerCase();
                    userAnswer = userAnswer.toLowerCase();
                    String[] correctAnswerWords = correctAnswer.split(" ");
                    String[] userAnswerWords = userAnswer.split(" ");
                    state = "Verdict";
                    if (Arrays.equals(correctAnswerWords, userAnswerWords)) {
                        ((TextView)findViewById(R.id.exercise_verdict)).setBackgroundColor(R.color.correct_color);
                        ((TextView)findViewById(R.id.exercise_verdict)).setText("Correct!");
                        taskCount++;
                        correctTaskCount++;
                    }
                    else {
                        ((TextView)findViewById(R.id.exercise_verdict)).setBackgroundColor(R.color.wrong_color);
                        ((TextView)findViewById(R.id.exercise_verdict)).setText("Mistake! Correct: " + correctAnswer);
                        taskCount++;
                        exerciseDdeque.offer(exerciseDdeque.peek());
                    }
                }
                else if (Objects.equals(state, "Verdict")) {
                    exerciseDdeque.poll();
                    if (exerciseDdeque.isEmpty()) {
                        finishAndResult();
                        return;
                    }
                    else if (Objects.equals(exerciseDdeque.peek().getType(), "New"))
                        state = "New";
                    else
                        state = "Task";
                    initExerciseInterface(exerciseDdeque.peek());
                }
            }
        });
    }

    private void finishAndResult() {
        Intent intent = new Intent();
        intent.putExtra("accuracy", (double) correctTaskCount / (double) taskCount);
        intent.putExtra("lessonIndex", lessonIndex);
        setResult(RESULT_OK, intent);
        finish();
    }

    @SuppressLint("ResourceAsColor")
    private void initExerciseInterface(Exercise exercise) {
        if (Objects.equals(exercise.getType(), "New")) {
            findViewById(R.id.exercise_plaintext).setVisibility(View.GONE);

            ((TextView)findViewById(R.id.exercise_title)).setText("New!");
            ((TextView)findViewById(R.id.exercise_textview1)).setText(exercise.getItalian());
            ((TextView)findViewById(R.id.exercise_textview2)).setText(exercise.getTranslate());
            ((TextView)findViewById(R.id.exercise_verdict)).setText("");
            findViewById(R.id.exercise_verdict).setBackgroundColor(R.color.white);
            ((Button)findViewById(R.id.exercise_button)).setText("OK");
            ((EditText)findViewById(R.id.exercise_plaintext)).setText("");
        }
        if (Objects.equals(exercise.getType(), "TaskTranslate")) {
            findViewById(R.id.exercise_plaintext).setVisibility(View.VISIBLE);

            ((TextView)findViewById(R.id.exercise_title)).setText("Write on English");
            ((TextView)findViewById(R.id.exercise_textview1)).setText(exercise.getItalian());
            ((TextView)findViewById(R.id.exercise_textview2)).setText("");
            ((TextView)findViewById(R.id.exercise_verdict)).setText("");
            findViewById(R.id.exercise_verdict).setBackgroundColor(R.color.white);
            ((Button)findViewById(R.id.exercise_button)).setText("OK");
            ((EditText)findViewById(R.id.exercise_plaintext)).setText("");
        }
        if (Objects.equals(exercise.getType(), "TaskItalian")) {
            findViewById(R.id.exercise_plaintext).setVisibility(View.VISIBLE);

            ((TextView)findViewById(R.id.exercise_title)).setText("Write on Italian");
            ((TextView)findViewById(R.id.exercise_textview1)).setText(exercise.getTranslate());
            ((TextView)findViewById(R.id.exercise_textview2)).setText("");
            ((TextView)findViewById(R.id.exercise_verdict)).setText("");
            findViewById(R.id.exercise_verdict).setBackgroundColor(R.color.white);
            ((Button)findViewById(R.id.exercise_button)).setText("OK");
            ((EditText)findViewById(R.id.exercise_plaintext)).setText("");
        }
    }
}