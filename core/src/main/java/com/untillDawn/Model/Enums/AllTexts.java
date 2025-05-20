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
    noSuchUserError("no such user exists!", ""),
    wrongPasswordError("wrong password!", ""),
    enterUsernameError("please enter your username!", ""),
    enterAnswerError("please enter the correct answer!", ""),
    yourPasswordIs("your password is ", ""),
    back("back", ""),
    submit("submit", ""),
    forgetPassword("forget password", ""),
    newGame("new game", ""),
    loadGame("load game", ""),
    settings("settings", ""),
    profile("profile", ""),
    scoreboard("scoreboard", ""),
    hint("hint", ""),
    logout("logout", ""),
    volume("volume", ""),
    selectedMusicTrack("selected music track:", ""),
    SFX("SFX", ""),
    autoReload("auto reload", ""),
    grayScale("gray scale", ""),
    keybindings("keybindings", ""),
    apply("apply", ""),
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
