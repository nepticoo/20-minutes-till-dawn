package com.untillDawn.Control;

import com.badlogic.gdx.Gdx;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.User;
import com.untillDawn.View.InitialMenuView;
import com.untillDawn.View.LoginMenuView;
import com.untillDawn.View.MainMenuView;
import com.untillDawn.View.SignupMenuView;

public class InitialMenuController {
    private static InitialMenuController instance;

    private InitialMenuController() {
    }

    public static InitialMenuController getInstance() {
        if (instance == null) {
            instance = new InitialMenuController();
        }
        return instance;
    }

    private InitialMenuView view;

    public void setView(InitialMenuView view) {
        this.view = view;
    }

    public void goToSignup() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new SignupMenuView());
    }

    public void goToLogin() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new LoginMenuView());
    }

    public void enterAsGuest() {
        App app = App.getInstance();
        app.setCurrentUser(new User());

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }

    public void exit() {
        Gdx.app.exit();
    }
}
