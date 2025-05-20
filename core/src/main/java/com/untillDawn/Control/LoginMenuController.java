package com.untillDawn.Control;

import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.User;
import com.untillDawn.View.ForgetPasswordView;
import com.untillDawn.View.InitialMenuView;
import com.untillDawn.View.LoginMenuView;
import com.untillDawn.View.MainMenuView;

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

    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void submit() {
        App app = App.getInstance();
        String username = view.getUsername().getText();
        String password = view.getPassword().getText();

        if(username == null || username.isEmpty() || password == null || password.isEmpty()) {
            view.setError(AllTexts.enterAllFieldsError.getVal());
            return;
        }

        User user = app.getUserByUsername(username);
        if(user == null) {
            view.setError(AllTexts.noSuchUserError.getVal());
            return;
        }

        if(!user.getPassword().equals(password)) {
            view.setError(AllTexts.wrongPasswordError.getVal());
            return;
        }

        app.setCurrentUser(user);

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }

    public void forgetPassword() {
        App app = App.getInstance();
        String username = view.getUsername().getText();

        if(username == null || username.isEmpty()) {
            view.setError(AllTexts.enterUsernameError.getVal());
            return;
        }

//        User user = app.getUserByUsername(username);
        User user = new User();
        if(user == null) {
            view.setError(AllTexts.noSuchUserError.getVal());
            return;
        }

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new ForgetPasswordView(user));
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new InitialMenuView());
    }
}
