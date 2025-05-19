package com.untillDawn.Model.Enums;

public enum Language {
    english,
    french;

    public static Language getLanguage(String language) {
        if(language.equalsIgnoreCase("french") || language.equalsIgnoreCase("fr")
            || language.equalsIgnoreCase("f") || language.equalsIgnoreCase("france")) {
            return french;
        }
        return english;
    }
}
