package com.untillDawn.Control;

import com.untillDawn.Main;
import com.untillDawn.View.MainMenuView;
import com.untillDawn.View.ScoreboardMenuView;

public class ScoreboardMenuController {
    private static ScoreboardMenuController instance;

    private ScoreboardMenuController() {
    }

    public static ScoreboardMenuController getInstance() {
        if (instance == null) {
            instance = new ScoreboardMenuController();
        }
        return instance;
    }

    private ScoreboardMenuView view;

    public void setView(ScoreboardMenuView view) {
        this.view = view;
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }
}
