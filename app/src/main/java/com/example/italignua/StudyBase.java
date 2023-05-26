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

        lessons.add(new Lesson("Learn simple pronouns", 15, new int[]{
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        }));

        // 12
        exercises.add(
                new Exercise("New", "Noi", "We", new String[]{"Noi"})
        );
        // 13
        exercises.add(
                new Exercise("New", "Noi siamo", "We are", new String[]{"Noi", "Siamo"})
        );
        // 14
        exercises.add(
                new Exercise("New", "Voi", "You (plural, you all)", new String[]{"Voi"})
        );
        // 15
        exercises.add(
                new Exercise("New", "Voi siete", "You are (plural)", new String[]{"Voi", "Siete"})
        );
        // 16
        exercises.add(
                new Exercise("Task", "Noi siamo", "We are", new String[]{"Noi", "Siamo"})
        );
        // 17
        exercises.add(
                new Exercise("Task", "Noi siamo e voi siete", "We are and you are", new String[]{"Noi", "Siamo", "E", "Voi", "Siete"})
        );
        // 18
        exercises.add(
                new Exercise("New", "Loro", "They", new String[]{"Loro"})
        );
        // 19
        exercises.add(
                new Exercise("New", "Loro sono", "They are", new String[]{"Loro", "Sono"})
        );
        // 20
        exercises.add(
                new Exercise("Task", "Voi siete", "You are", new String[]{"Voi", "Siete"})
        );
        // 21
        exercises.add(
                new Exercise("Task", "Voi siete e loro sono", "You are and they are", new String[]{"Voi", "Siete", "E", "Loro", "Sono"})
        );
        // 22
        exercises.add(
                new Exercise("Task", "Noi, voi e loro", "We, you and they", new String[]{"Voi", "Noi", "E", "Loro"})
        );

        lessons.add(new Lesson("Learn simple pronouns II", 15, new int[] {
                12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
        }));

        // 23
        exercises.add(
                new Exercise("New", "All verbs that we have learned at that time are forms of the verb 'essere'\n" +
                        "Io sono - I am\n" +
                        "Tu sei - You are\n" +
                        "Lui/Lei e' - He/She is\n" +
                        "\n" +
                        "Noi siamo - We are\n" +
                        "Voi siete - You are (plural)\n" +
                        "Loro sono - They are", "", new String[]{})
        );
        // 24
        exercises.add(
                new Exercise("Task", "Io e loro siamo", "I and they are", new String[]{"Io", "E", "Loro", "Siamo"})
        );
        // 25
        exercises.add(
                new Exercise("Task", "Voi e loro siete", "You and they are", new String[]{"Noi", "E", "Loro", "Siete"})
        );
        // 26
        exercises.add(
                new Exercise("Task", "Io e tu siamo", "I and you are", new String[]{"Io", "E", "Tu", "Siamo"})
        );
        // 27
        exercises.add(
                new Exercise("Task", "Lui e lei sono", "He and she are", new String[]{"Lui", "E", "Lei", "Sono"})
        );
        // 28
        exercises.add(
                new Exercise("Task", "Lui e loro sono", "He and they are", new String[]{"Lui", "E", "Loro", "Sono"})
        );
        // 29
        exercises.add(
                new Exercise("Task", "Noi e voi siamo", "We and you are", new String[]{"Noi", "E", "Voi", "Siamo"})
        );

        lessons.add(
                new Lesson("Learn simple pronouns III", 18, new int[] {
                        24, 25, 26, 27, 28, 29
                })
        );
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
