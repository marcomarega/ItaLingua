package com.example.italignua;

import java.util.ArrayList;
import java.util.Random;

public class Exercise {
    private String type;
    private String italian;
    private String translate;
    private String[] words;

    public Exercise(String type, String italian, String translate, String[] words) {
        this.type = type;
        this.italian = italian;
        this.translate = translate;
        if (type.equals("Task")) {
            Random random = new Random();
            boolean n = random.nextBoolean();
            if (n) {
                this.type = "TaskTranslate";
            }
            else {
                this.type = "TaskItalian";
            }
        }
        this.words = words;
    }

    public String getType() {
        return type;
    }

    public String getItalian() {
        return italian;
    }

    public String getTranslate() {
        return translate;
    }

    public String[] getWords() {
        return words;
    }
}