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
//        TODO

        Game game = new Game(HeroType.shana, WeaponType.shotgun, 10);
        App.getInstance().getCurrentUser().setCurrentGame(game);

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new GameView());
    }

    public void loadGame() {
//        TODO
    }

    public void goToSettings() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new SettingsMenuView());
    }

    public void goToProfile() {
//        TODO: uncomment this
//        if(App.getInstance().getCurrentUser().isGuest()) {
//            return;
//        }
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
