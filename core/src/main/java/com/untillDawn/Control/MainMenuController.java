package com.untillDawn.Control;

public class MainMenuController {
    private static MainMenuController instance;

    private MainMenuController() {
    }

    public static MainMenuController getInstance() {
        if (instance == null) {
            instance = new MainMenuController();
        }
        return instance;
    }
}
