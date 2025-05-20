package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.LoginMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;

public class LoginMenuView implements Screen {
    public LoginMenuView() {
        this.controller = LoginMenuController.getInstance();
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();

        this.logo = assetManager.getLogo();
        logo.setScaling(Scaling.fit);

        this.username = new TextField("", skin);
        username.setMessageText(AllTexts.enterUsername.getVal());

        this.password = new TextField("", skin);
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        password.setMessageText(AllTexts.enterPassword.getVal());

        this.forgetPassword = new TextButton(AllTexts.forgetPassword.getVal(), skin);
        forgetPassword.setTransform(true);
        forgetPassword.setOrigin(Align.center);
        forgetPassword.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                forgetPassword.clearActions();
                forgetPassword.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                forgetPassword.clearActions();
                forgetPassword.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.forgetPassword();
            }
        });

        this.submitButton = new TextButton(AllTexts.submit.getVal(), skin);
        submitButton.setTransform(true);
        submitButton.setOrigin(Align.center);
        submitButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                submitButton.clearActions();
                submitButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                submitButton.clearActions();
                submitButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.submit();
            }
        });

        this.backButton = new TextButton(AllTexts.back.getVal(), skin);
        backButton.setTransform(true);
        backButton.setOrigin(Align.center);
        backButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backButton.clearActions();
                backButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                backButton.clearActions();
                backButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
            }
        });

        Label.LabelStyle style = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        style.fontColor = AllColors.red.color;
        this.errorLabel = new Label("", style);


        this.table = new Table(skin);
        controller.setView(this);
    }

    private Stage stage;
    private Image logo;
    private TextField username;
    private TextField password;
    private TextButton forgetPassword;
    private TextButton submitButton;
    private TextButton backButton;
    private Label errorLabel;
    public Table table;

    private final LoginMenuController controller;
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
        table.row().pad(0, 0, 20, 0);

        table.add(username).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 20, 0);

        table.add(password).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 20, 0);

        table.add(errorLabel);
        table.row().pad(0, 0, 20, 0);

        table.add(forgetPassword);
        table.row().pad(0, 0, 20, 0);

        Table buttonRow = new Table();
        buttonRow.add(submitButton).padRight(20);
        buttonRow.add(backButton).padLeft(20);

        table.add(buttonRow).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 20, 0);

        stage.addActor(table);
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public void setError(String error) {
        errorLabel.setText(error);
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
