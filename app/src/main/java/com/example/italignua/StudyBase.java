package com.example.italignua;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudyBase {
    private static ArrayList<Lesson> lessons = new ArrayList<>();
    private static ArrayList<Exercise> exercises = new ArrayList<>();

    public static void initLessons() {
        initExercises();
        lessons.add(new Lesson("Learn simple pronounses", 10, new int[]{
                1, 2, 3, 4, 5, 6
        }));
    }

    public static void initExercises() {
        exercises.clear();
        exercises.add(
                new Exercise("New", "Io", "I", new String[]{"Io"})
        );
        exercises.add(
                new Exercise("New", "Io sono", "I am", new String[]{"Io", "Sono"})
        );
        exercises.add(
                new Exercise("New", "Tu", "You", new String[]{"Tu"})
        );
        exercises.add(
                new Exercise("New", "Tu sei", "You are", new String[]{"Tu", "Sei"})
        );
        exercises.add(
                new Exercise("New", "E", "And", new String[]{"E"})
        );
        exercises.add(
                new Exercise("Task", "Io sono", "I am", new String[]{"Io", "Sono"})
        );
        exercises.add(
                new Exercise("Task", "Io sono e tu sei", "I am and you are", new String[]{"Io", "Sono", "E", "Tu", "Sei"})
        );
    }

    public static Exercise[] getExercises(int[] exercises_indexes) {
        Exercise[] out_exercises = new Exercise[exercises_indexes.length];
        for (int i = 0; i < exercises_indexes.length; i++) {
            out_exercises[i] = exercises.get(i);
        }
        return out_exercises;
    }

    public static ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public static ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
