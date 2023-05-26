package com.example.italignua;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class StudyBase {
    private static ArrayList<Lesson> lessons = new ArrayList<>();
    private static ArrayList<Exercise> exercises = new ArrayList<>();

    public static void initLessons() {
        exercises.clear();
        // 0
        exercises.add(
                new Exercise("New", "Io", "I", new String[]{"Io"})
        );
        // 1
        exercises.add(
                new Exercise("New", "Io sono", "I am", new String[]{"Io", "Sono"})
        );
        // 2
        exercises.add(
                new Exercise("New", "Tu", "You", new String[]{"Tu"})
        );
        // 3
        exercises.add(
                new Exercise("New", "Tu sei", "You are", new String[]{"Tu", "Sei"})
        );
        // 4
        exercises.add(
                new Exercise("New", "E (or 'ed' if it goes before the vowel)", "And", new String[]{"E"})
        );
        // 5
        exercises.add(
                new Exercise("Task", "Io sono", "I am", new String[]{"Io", "Sono"})
        );
        // 6
        exercises.add(
                new Exercise("Task", "Io sono e tu sei", "I am and you are", new String[]{"Io", "Sono", "E", "Tu", "Sei"})
        );
        // 7
        exercises.add(
                new Exercise("New", "Lui", "He", new String[]{"Lui"})
        );
        // 8
        exercises.add(
                new Exercise("New", "Lei", "She", new String[]{"Lei"})
        );
        // 9
        exercises.add(
                new Exercise("Task", "Lui e lei", "He and she", new String[]{"Lui", "Lei"})
        );
        // 10
        exercises.add(
                new Exercise("New", "Lui e'", "He is", new String[]{"Lui", "E'"})
        );
        // 11
        exercises.add(
                new Exercise("Task", "Lui e' e lei e'", "He is and she is", new String[]{"Lui", "Lei", "E", "E'"})
        );

        lessons.add(new Lesson("Learn simple pronounses", 15, new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        }));
    }

    public static Exercise[] getExercises(int[] exercises_indexes) {
        Exercise[] out_exercises = new Exercise[exercises_indexes.length];
        for (int i = 0; i < exercises_indexes.length; i++) {
            out_exercises[i] = exercises.get(exercises_indexes[i]);
        }
        return out_exercises;
    }

    public static ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public static Lesson getLesson(int index, Context context) throws IllegalStateException {
        if (index == -1) {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            Cursor cursor = database.query(DBHelper.EXERCISES_TABLE_NAME, new String[]{DBHelper.EXERCISES_COLUMN_EXERCISE_ID}, null, null, null, null, null);
            int[] total_exercise_indexes = new int[cursor.getCount()];

            if (!cursor.moveToFirst()) throw new IllegalStateException("No exercises");
            int i = 0;
            while (!cursor.isAfterLast()) {
                int exerciseIndex = cursor.getColumnIndex(DBHelper.EXERCISES_COLUMN_EXERCISE_ID);
                total_exercise_indexes[i++] = cursor.getInt(exerciseIndex);
                cursor.moveToNext();
            }

            int[] exercise_indexes = new int[12];
            Random random = new Random();
            for (i = 0; i < exercise_indexes.length; i++) {
                exercise_indexes[i] = total_exercise_indexes[random.nextInt(total_exercise_indexes.length)];
            }
            Lesson lesson = new Lesson("Training", 10, exercise_indexes);
            return lesson;
        }
        else {
            return lessons.get(index);
        }
    }

    public static ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
