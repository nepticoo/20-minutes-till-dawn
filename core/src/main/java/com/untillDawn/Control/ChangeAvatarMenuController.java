package com.untillDawn.Control;

import com.badlogic.gdx.graphics.Texture;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.User;
import com.untillDawn.View.ChangeAvatarMenuView;
import com.untillDawn.View.ProfileMenuView;

public class ChangeAvatarMenuController {
    private static ChangeAvatarMenuController instance;

    private ChangeAvatarMenuController() {}

    public static ChangeAvatarMenuController getInstance() {
        if (instance == null) {
            instance = new ChangeAvatarMenuController();
        }
        return instance;
    }

    private ChangeAvatarMenuView view;

    public void setView(ChangeAvatarMenuView view) {
        this.view = view;
    }

    public void setAvatar(Texture avatar) {
        User user = App.getInstance().getCurrentUser();
        user.setAvatar(avatar);
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new ProfileMenuView());
    }
}
