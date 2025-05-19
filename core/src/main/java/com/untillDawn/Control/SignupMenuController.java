package com.untillDawn.Control;

import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.Enums.Question;
import com.untillDawn.Model.User;
import com.untillDawn.View.InitialMenuView;
import com.untillDawn.View.MainMenuView;
import com.untillDawn.View.SignupMenuView;

public class SignupMenuController {
    private static SignupMenuController instance;

    private SignupMenuController() {

    }

    public static SignupMenuController getInstance() {
        if (instance == null) {
            instance = new SignupMenuController();
        }
        return instance;
    }

    private SignupMenuView view;

    public void setView(SignupMenuView view) {
        this.view = view;
    }

    public void submit() {
        App app = App.getInstance();
        String username = view.getUsername().getText();
        String password = view.getPassword().getText();
        Question question = view.getQuestionBox().getSelected();
        String answer = view.getAnswer().getText();

        if(username == null || username.isEmpty() || password == null || password.isEmpty() || answer == null || answer.isEmpty()) {
            view.setError(AllTexts.enterAllFieldsError.getVal());
            return;
        }

        if(app.getUserByUsername(username) != null) {
            view.setError(AllTexts.notAvailableUsernameError.getVal());
            return;
        }

        if(password.length() < 8) {
            view.setError(AllTexts.passwordTooShortError.getVal());
            return;
        }

        if (!password.matches(".*[A-Z].*") || !password.matches(".*\\d.*") || !password.matches(".*[@%$#&*()_].*")) {
            view.setError(AllTexts.passwordWeakError.getVal());
            return;
        }

        User user = new User(username, password, question.ordinal(), answer);
        app.addUser(user);

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());

    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new InitialMenuView());
    }
}
