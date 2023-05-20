package com.example.italignua;

import java.util.Arrays;
import java.util.HashSet;

public class Lesson {
    final private String title;
    final private int xp;
    final private int[] exerciseIndexes;
    final private Exercise[] exercises;

    public Lesson(String title, int xp, int[] exercises) {
        this.title = title;
        this.xp = xp;
        this.exerciseIndexes = exercises;
        this.exercises = StudyBase.getExercises(exercises);
    }

    public HashSet<String> getWords() {
        HashSet<String> words = new HashSet<>();
        for (Exercise exercise : exercises) {
            words.addAll(Arrays.asList(exercise.getWords()));
        }
        return words;
    }

    public String getTitle() {
        return title;
    }

    public int getXp() {
        return xp;
    }

    public Exercise[] getExercises() {
        return exercises;
    }

    public int[] getExerciseIndexes() { return exerciseIndexes; }
}
