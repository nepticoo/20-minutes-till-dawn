package com.untillDawn.Control;

import com.untillDawn.Main;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.User;
import com.untillDawn.View.ForgetPasswordView;
import com.untillDawn.View.LoginMenuView;

public class ForgetPasswordController {
    private static ForgetPasswordController instance;

    private ForgetPasswordController() {

    }

    public static ForgetPasswordController getInstance() {
        if (instance == null) {
            instance = new ForgetPasswordController();
        }
        return instance;
    }

    ForgetPasswordView view;

    public void setView(ForgetPasswordView view) {
        this.view = view;
    }

    public void submit() {
        User user = view.getUser();
        String answer = view.getAnswer().getText();

        if(answer == null || !answer.equals(user.getAnswer())) {
            view.getErrorLabel().setText(AllTexts.enterAnswerError.getVal());
            view.getPasswordLabel().setText("");
            return;
        }

        String password = user.getPassword();
        view.getErrorLabel().setText("");
        view.getPasswordLabel().setText(AllTexts.yourPasswordIs.getVal() + password);
        return;
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new LoginMenuView());
    }
}
