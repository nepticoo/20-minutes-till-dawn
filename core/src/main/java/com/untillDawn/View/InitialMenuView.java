package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.InitialMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.Enums.Language;

public class InitialMenuView implements Screen {
    public InitialMenuView() {
        this.controller = InitialMenuController.getInstance();
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();

        buttonScale = 0.8f;

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        style.font.getData().setScale(buttonScale);

        this.logo = assetManager.getLogo();
        logo.setScaling(Scaling.fill);

        this.signupButton = new TextButton(AllTexts.signUp.getVal(), style);
        this.signupButton.setTransform(true);
        this.signupButton.setOrigin(Align.center);
        this.signupButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                signupButton.clearActions();
                signupButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                signupButton.clearActions();
                signupButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToSignup();
            }
        });

        this.loginButton = new TextButton(AllTexts.login.getVal(), style);
        this.loginButton.setTransform(true);
        this.loginButton.setOrigin(Align.center);
        this.loginButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                loginButton.clearActions();
                loginButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                loginButton.clearActions();
                loginButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToLogin();
            }
        });

        this.guestButton = new TextButton(AllTexts.guest.getVal(), style);
        this.guestButton.setTransform(true);
        this.guestButton.setOrigin(Align.center);
        this.guestButton.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                guestButton.clearActions();
                guestButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                guestButton.clearActions();
                guestButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.enterAsGuest();
            }
        });

        this.exitButton = new TextButton(AllTexts.exit.getVal(), style);
        this.exitButton.setTransform(true);
        this.exitButton.setOrigin(Align.center);
        this.exitButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitButton.clearActions();
                exitButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitButton.clearActions();
                exitButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.exit();
            }
        });

        this.table = new Table(skin);

        controller.setView(this);
    }

    private Stage stage;
    private final Image logo;
    private float buttonScale;
    private final TextButton signupButton;
    private final TextButton loginButton;
    private final TextButton guestButton;
    private final TextButton exitButton;
    public Table table;

    private final InitialMenuController controller;
    private final AppAssetManager assetManager;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();


        float screenWidth = Gdx.graphics.getWidth();
        float logoWidth = screenWidth * 0.3f;
        float logoHeight = logoWidth * 0.3f;

        table.add(logo).size(logoWidth, logoHeight);
        table.row().pad(50, 0, 20, 0);
        table.add(signupButton);
        table.row().pad(0, 0, 20, 0);
        table.add(loginButton);
        table.row().pad(0, 0, 20, 0);
        table.add(guestButton);
        table.row().pad(0, 0, 20, 0);
        table.add(exitButton);
        table.row().pad(0, 0, 20, 0);

        stage.addActor(table);
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
