package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.CameControllers.GameController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.GameModels.Game;

public class GameView implements Screen, InputProcessor {

    public GameView() {
        game = App.getInstance().getCurrentUser().getCurrentGame();
        controller = GameController.getInstance();
        controller.initialize(game, this);
    }

    private Stage stage;

    private final Game game;
    private final GameController controller;


    @Override
    public boolean keyDown(int keycode) {
        controller.handleKeyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        controller.handleKeyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        controller.getWeaponController().handleShoot(screenX, screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY, false);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(this);
        controller.getEnemyController().spawnTree();
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(AllColors.backGround.color);
        if (App.getInstance().getSettings().hasGrayScale()) {
            stage.getBatch().setShader(Main.getGrayScaleShader());
        } else {
            stage.getBatch().setShader(null);
        }
        Main.getBatch().begin();
        controller.updateGame(v);
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
