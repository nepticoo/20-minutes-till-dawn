package com.untillDawn.Control.CameControllers;

import com.untillDawn.Main;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.View.GameView;

public class WorldController {
    private Game game;
    private GameView view;

    public WorldController(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    public void update() {
        game.setMapX(game.getPlayer().getX());
        game.setMapY(game.getPlayer().getY());
        Main.getBatch().draw(game.getMap(), game.getMapX(), game.getMapY());
    }
}
