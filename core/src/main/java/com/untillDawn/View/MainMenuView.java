package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.MainMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.User;

public class MainMenuView implements Screen {
    public MainMenuView(){
        this.controller = MainMenuController.getInstance();
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();

        float buttonScale = 0.6f;

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(skin.get(TextButton.TextButtonStyle.class));
        style.font.getData().setScale(buttonScale);

        this.logo = assetManager.getLogo();
        logo.setScaling(Scaling.fit);

        this.newGameButton = new TextButton(AllTexts.newGame.getVal(), style);
        newGameButton.setTransform(true);
        newGameButton.setOrigin(Align.center);
        newGameButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                newGameButton.clearActions();
                newGameButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                newGameButton.clearActions();
                newGameButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.newGame();
            }
        });

        this.loadGameButton = new TextButton(AllTexts.loadGame.getVal(), style);
        loadGameButton.setTransform(true);
        loadGameButton.setOrigin(Align.center);
        loadGameButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                loadGameButton.clearActions();
                loadGameButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                loadGameButton.clearActions();
                loadGameButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.loadGame();
            }
        });

        this.settingsButton = new TextButton(AllTexts.settings.getVal(), style);
        settingsButton.setTransform(true);
        settingsButton.setOrigin(Align.center);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                settingsButton.clearActions();
                settingsButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                settingsButton.clearActions();
                settingsButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToSettings();
            }
        });

        this.profileButton = new TextButton(AllTexts.profile.getVal(), style);
        profileButton.setTransform(true);
        profileButton.setOrigin(Align.center);
        profileButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                profileButton.clearActions();
                profileButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                profileButton.clearActions();
                profileButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToProfile();
            }
        });

        this.scoreboardButton = new TextButton(AllTexts.scoreboard.getVal(), style);
        scoreboardButton.setTransform(true);
        scoreboardButton.setOrigin(Align.center);
        scoreboardButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                scoreboardButton.clearActions();
                scoreboardButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                scoreboardButton.clearActions();
                scoreboardButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToScoreboard();
            }
        });

        this.hintButton = new TextButton(AllTexts.hint.getVal(), style);
        hintButton.setTransform(true);
        hintButton.setOrigin(Align.center);
        hintButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hintButton.clearActions();
                hintButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                hintButton.clearActions();
                hintButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToHint();
            }
        });

        this.logoutButton = new TextButton(AllTexts.logout.getVal(), style);
        logoutButton.setTransform(true);
        logoutButton.setOrigin(Align.center);
        logoutButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                logoutButton.clearActions();
                logoutButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                logoutButton.clearActions();
                logoutButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.logout();
            }
        });

        User user = App.getInstance().getCurrentUser();
        Drawable userAvatar = new TextureRegionDrawable(new TextureRegion(user.getAvatar()));
        ImageTextButton.ImageTextButtonStyle userButtonStyle = new ImageTextButton.ImageTextButtonStyle(skin.get(ImageTextButton.ImageTextButtonStyle.class));
        userButtonStyle.imageUp = userAvatar;
        this.userInfoButton = new ImageTextButton(user.getUsername() + "(" + user.getScore() + " pts)", userButtonStyle);
        this.userInfoButton.getImage().setDrawable(userAvatar);
        this.userInfoButton.getImageCell().size(48, 48);
        this.userInfoButton.setDisabled(true);
        this.userInfoButton.setTouchable(Touchable.disabled);


        this.table = new Table(skin);
        this.userInfoTable = new Table(skin);
        controller.setView(this);
    }

    private Stage stage;
    private Image logo;
    private TextButton newGameButton;
    private TextButton loadGameButton;
    private TextButton settingsButton;
    private TextButton profileButton;
    private TextButton scoreboardButton;
    private TextButton hintButton;
    private TextButton logoutButton;
    private ImageTextButton userInfoButton;
    public Table table;
    public Table userInfoTable;

    private final MainMenuController controller;
    private final AppAssetManager assetManager;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(assetManager.getBackgroundImage());

        table.setFillParent(true);
        table.center();

        float screenWidth = Gdx.graphics.getWidth();
        float logoWidth = screenWidth * 0.3f;
        float logoHeight = logoWidth * 0.3f;

        table.add(logo).size(logoWidth, logoHeight);
        table.row().pad(50, 0, 20, 0);

        float buttonSizeScale = 0.9f;

        Table buttonRow = new Table();
        buttonRow.add(newGameButton).size(newGameButton.getWidth(), newGameButton.getHeight() * buttonSizeScale).padRight(20);
        buttonRow.add(loadGameButton).size(loadGameButton.getWidth(), loadGameButton.getHeight() * buttonSizeScale).padLeft(20);

        table.add(buttonRow).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 20, 0);

        table.add(settingsButton).size(settingsButton.getWidth(), settingsButton.getHeight() * buttonSizeScale);
        table.row().pad(0, 0, 20, 0);
        table.add(profileButton).size(profileButton.getWidth(), profileButton.getHeight() * buttonSizeScale);
        table.row().pad(0, 0, 20, 0);
        table.add(scoreboardButton).size(scoreboardButton.getWidth(), scoreboardButton.getHeight() * buttonSizeScale);
        table.row().pad(0, 0, 20, 0);
        table.add(hintButton).size(hintButton.getWidth(), hintButton.getHeight() * buttonSizeScale);
        table.row().pad(0, 0, 20, 0);
        table.add(logoutButton).size(logoutButton.getWidth(), logoutButton.getHeight() * buttonSizeScale);
        table.row().pad(0, 0, 20, 0);


        userInfoTable.top().left().pad(10);
        userInfoTable.setFillParent(true);
        userInfoTable.add(userInfoButton);

        userInfoTable.add(userInfoButton);

        stage.addActor(table);
        stage.addActor(userInfoTable);
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
