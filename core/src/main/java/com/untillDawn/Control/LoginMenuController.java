package com.untillDawn.Control;

public class LoginMenuController {
    private static LoginMenuController instance;

    private LoginMenuController() {

    }

    public static LoginMenuController getInstance() {
        if (instance == null) {
            instance = new LoginMenuController();
        }
        return instance;
    }

}
