package com.untillDawn.Control;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.View.ChangeAvatarMenuView;
import com.untillDawn.View.InitialMenuView;
import com.untillDawn.View.MainMenuView;
import com.untillDawn.View.ProfileMenuView;


public class ProfileMenuController {
    private static ProfileMenuController instance;

    private ProfileMenuController() {}

    public static ProfileMenuController getInstance() {
        if (instance == null) {
            instance = new ProfileMenuController();
        }
        return instance;
    }

    ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void changeUsername() {
        App app = App.getInstance();
        Label label = view.getChangeUsernameLabel();
        String newUsername = view.getChangeUsername().getText();

        if(newUsername == null || newUsername.isEmpty()) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.enterAllFieldsError.getVal());
            return;
        }

        if(app.getCurrentUser().getUsername().equals(newUsername)) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.enterDifferentUsernameError.getVal());
            return;
        }
        if(app.getUserByUsername(newUsername) != null) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.notAvailableUsernameError.getVal());
            return;
        }

        app.getCurrentUser().setUsername(newUsername);

        label.setColor(AllColors.green.color);
        label.setText(AllTexts.usernameChanged.getVal());
    }

    public void changePassword() {
        App app = App.getInstance();
        Label label = view.getChangePasswordLabel();
        String newPassword = view.getChangePassword().getText();

        if(newPassword == null || newPassword.isEmpty()) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.enterAllFieldsError.getVal());
            return;
        }

        if(app.getCurrentUser().getPassword().equals(newPassword)) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.enterDifferentPasswordError.getVal());
            return;
        }

        if(newPassword.length() < 8) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.passwordTooShortError.getVal());
            return;
        }

        if (!newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*\\d.*") || !newPassword.matches(".*[@%$#&*()_].*")) {
            label.setColor(AllColors.red.color);
            label.setText(AllTexts.passwordWeakError.getVal());
            return;
        }

        app.getCurrentUser().setPassword(newPassword);

        label.setColor(AllColors.green.color);
        label.setText(AllTexts.passwordChanged.getVal());

    }

    public void goToChangeAvatar() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new ChangeAvatarMenuView());}

    public void deleteAccount() {
        App.getInstance().deleteCurrentUser();

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new InitialMenuView());
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }
}
