package com.untillDawn.Model.Enums;

public enum Question {
    first("What was the name of your first pet?"),
    second("What is your mother's maiden name?"),
    third("What was the name of your elementary/primary school?"),
    forth("What was the make and model of your first car?"),
    fifth("In what city were you born?");

    Question(String question) {
        this.question = question;
    }

    private String question;

    public String getQuestion() {
        return question;
    }

    public static Question getQuestionById(int id) {
        return values()[id];
    }

    @Override
    public String toString() {
        return question;
    }
}
