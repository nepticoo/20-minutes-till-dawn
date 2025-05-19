package com.untillDawn.Model.Enums;

import com.untillDawn.Model.App;

public enum AllTexts {
    signUp("sign up", ""),
    login("login", ""),
    guest("enter as guest", ""),
    exit("exit", ""),
    enterUsername("enter username...", ""),
    enterPassword("enter password...", ""),
    enterAnswer("enter your answer...", ""),
    enterAllFieldsError("you must fill all the fields!", ""),
    notAvailableUsernameError("this username is not available!", ""),
    passwordWeakError("password is weak!", ""),
    passwordTooShortError("password is too short!", ""),
    back("back", ""),
    submit("submit", ""),
    ;

    private final String english;
    private final String french;

    AllTexts(String english, String french) {
        this.english = english;
        this.french = french;
    }

    public String getEnglish() {
        return english;
    }

    public String getFrench() {
        return french;
    }

    public String getVal(Language language) {
        if(language.equals(Language.french)) {
            return french;
        }
        return english;
    }

    public String getVal() {
        return getVal(App.getInstance().getLanguage());
    }
}
