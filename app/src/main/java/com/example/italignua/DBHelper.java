package com.example.italignua;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "italingua.db";
    public static final int DATABASE_VERSION = 1;

    public static final String LESSONS_TABLE_NAME = "lessons";
    public static final String LESSONS_COLUMN_ID = "_id";
    public static final String LESSONS_COLUMN_LESSON_ID = "lesson_id";

    public static final String WORDS_TABLE_NAME = "words";
    public static final String WORDS_COLUMN_ID = "_id";
    public static final String WORDS_COLUMN_WORD = "word";

    public static final String EXERCISES_TABLE_NAME = "exercises";
    public static final String EXERCISES_COLUMN_ID = "_id";
    public static final String EXERCISES_COLUMN_EXERCISE_ID = "exercise_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String lessons_table_create_query = "CREATE TABLE IF NOT EXISTS " + LESSONS_TABLE_NAME +
                "(" + LESSONS_COLUMN_ID + ", " + LESSONS_COLUMN_LESSON_ID + ");";
        db.execSQL(lessons_table_create_query);
        String words_table_create_query = "CREATE TABLE IF NOT EXISTS " + WORDS_TABLE_NAME +
                "(" + WORDS_COLUMN_ID + ", " + WORDS_COLUMN_WORD + ");";
        db.execSQL(words_table_create_query);
        String exercises_table_create_query = "CREATE TABLE IF NOT EXISTS " + EXERCISES_TABLE_NAME +
                "(" + EXERCISES_COLUMN_ID + ", " + EXERCISES_COLUMN_EXERCISE_ID + ");";
        db.execSQL(exercises_table_create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LESSONS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORDS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISES_TABLE_NAME);
        onCreate(db);
    }
}
