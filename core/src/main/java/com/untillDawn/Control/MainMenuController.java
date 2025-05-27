package com.untillDawn.Control;

import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.GameModels.Enums.HeroType;
import com.untillDawn.Model.GameModels.Enums.WeaponType;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.View.*;

public class MainMenuController {
    private static MainMenuController instance;

    private MainMenuController() {

    }

    public static MainMenuController getInstance() {
        if(instance == null) {
            instance = new MainMenuController();
        }
        return instance;
    }

    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void newGame() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new PreGameMenuView());
    }

    public void loadGame() {
        Game game = App.getInstance().getCurrentUser().getCurrentGame();
        if(game == null) {
            return;
        }
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new GameView());
    }

    public void goToSettings() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new SettingsMenuView());
    }

    public void goToProfile() {
        if(App.getInstance().getCurrentUser().isGuest()) {
            return;
        }
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new ProfileMenuView());
    }

    public void goToScoreboard() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new ScoreboardMenuView());
    }

    public void goToHint() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new HintMenuView());
    }

    public void logout() {
        App.getInstance().setCurrentUser(null);

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new InitialMenuView());
    }
}
