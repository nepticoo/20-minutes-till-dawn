package com.untillDawn.Control;

import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.GameModels.Enums.HeroType;
import com.untillDawn.Model.GameModels.Enums.WeaponType;
import com.untillDawn.View.GameView;
import com.untillDawn.View.MainMenuView;

public class PreGameMenuController {
    private static PreGameMenuController instance;

    private PreGameMenuController() {}

    public static PreGameMenuController getInstance() {
        if (instance == null) {
            instance = new PreGameMenuController();
        }
        return instance;
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }

    public void play(HeroType hero, WeaponType weapon, int duration) {

        Game game = new Game(hero, weapon, duration * 60);
        App.getInstance().getCurrentUser().setCurrentGame(game);

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new GameView());
    }
}
