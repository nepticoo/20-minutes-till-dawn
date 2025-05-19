package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.MainMenuController;
import com.untillDawn.Model.AppAssetManager;

import java.util.Stack;

public class MainMenuView implements Screen {
    private static MainMenuView instance;

    private MainMenuView() {
        this.controller = MainMenuController.getInstance();
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();

        this.playButton = new TextButton("Play", skin);
        this.appTitle = new Label("Title", skin);
        this.field = new TextField("Field", skin);
        this.table = new Table(skin);
    }

    public static MainMenuView getInstance() {
        if (instance == null) {
            instance = new MainMenuView();
        }
        return instance;
    }

    private Stage stage;
    private final TextButton playButton;
    private final Label appTitle;
    private final TextField field;
    public Table table;
    private final MainMenuController controller;
    private final AppAssetManager assetManager;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.center();
        table.add(appTitle);
        table.row().pad(10, 0, 10, 0);
        table.add(field).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(playButton);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
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
