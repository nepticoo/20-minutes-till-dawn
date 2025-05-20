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
import com.untillDawn.Control.ProfileMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;


public class ProfileMenuView implements Screen {
    public ProfileMenuView() {
        this.controller = ProfileMenuController.getInstance();
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();

        this.logo = assetManager.getLogo();
        logo.setScaling(Scaling.fit);

        this.changeUsername = new TextField("", skin);
        changeUsername.setMessageText(AllTexts.enterNewUsername.getVal());

        this.changeUsernameButton = new TextButton(AllTexts.apply.getVal(), skin);
        changeUsernameButton.setTransform(true);
        changeUsernameButton.setOrigin(Align.center);
        changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                changeUsernameButton.clearActions();
                changeUsernameButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                changeUsernameButton.clearActions();
                changeUsernameButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.changeUsername();
            }
        });

        changeUsernameLabel = new Label("", skin);

        this.changePassword = new TextField("", skin);
        changePassword.setMessageText(AllTexts.enterNewPassword.getVal());

        this.changePasswordButton = new TextButton(AllTexts.apply.getVal(), skin);
        changePasswordButton.setTransform(true);
        changePasswordButton.setOrigin(Align.center);
        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                changePasswordButton.clearActions();
                changePasswordButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                changePasswordButton.clearActions();
                changePasswordButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.changePassword();
            }
        });

        changePasswordLabel = new Label("", skin);

        this.deleteAccountButton = new TextButton(AllTexts.deleteAccount.getVal(), skin);
        deleteAccountButton.setTransform(true);
        deleteAccountButton.setOrigin(Align.center);
        deleteAccountButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                deleteAccountButton.clearActions();
                deleteAccountButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                deleteAccountButton.clearActions();
                deleteAccountButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Skin skin = assetManager.getSkin();

                Dialog confirmDialog = new Dialog("", skin) {
                    @Override
                    protected void result(Object object) {
                        boolean confirmed = (Boolean) object;
                        if (confirmed) {
                            controller.deleteAccount();
                        }
                    }
                };

                confirmDialog.text("Are you sure you want to delete your account?");
                confirmDialog.button("Yes", true);
                confirmDialog.button("No", false);
                confirmDialog.show(stage);
            }
        });

        this.changeAvatar = new TextButton(AllTexts.changeAvatar.getVal(), skin);
        changeAvatar.setTransform(true);
        changeAvatar.setOrigin(Align.center);
        changeAvatar.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                changeAvatar.clearActions();
                changeAvatar.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                changeAvatar.clearActions();
                changeAvatar.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToChangeAvatar();
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

        table = new Table(skin);
        controller.setView(this);
    }


    private Stage stage;
    private Image logo;
    private TextField changeUsername;
    private TextButton changeUsernameButton;
    private Label changeUsernameLabel;
    private TextField changePassword;
    private TextButton changePasswordButton;
    private Label changePasswordLabel;
    private TextButton deleteAccountButton;
    private TextButton changeAvatar;
    private TextButton backButton;
    public Table table;

    private final ProfileMenuController controller;
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

        Table usernameTable = new Table();
        usernameTable.add(changeUsername).width(screenWidth * 0.4f).padRight(20);
        usernameTable.add(changeUsernameButton).padLeft(20);
        table.add(usernameTable).width(screenWidth * 0.6f);
        table.row().pad(0, 0, 10, 0);
        table.add(changeUsernameLabel).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 10, 0);

        Table passwordTable = new Table();
        passwordTable.add(changePassword).width(screenWidth * 0.4f).padRight(20);
        passwordTable.add(changePasswordButton).padLeft(20);
        table.add(passwordTable).width(screenWidth * 0.6f);
        table.row().pad(0, 0, 10, 0);
        table.add(changePasswordLabel).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 10, 0);

        table.add(changeAvatar);
        table.row().pad(0, 0, 10, 0);

        table.add(deleteAccountButton);
        table.row().pad(0, 0, 10, 0);

        table.add(backButton);
        table.row().pad(0, 0, 10, 0);

        stage.addActor(table);

    }

    public TextField getChangeUsername() {
        return changeUsername;
    }

    public Label getChangeUsernameLabel() {
        return changeUsernameLabel;
    }

    public TextField getChangePassword() {
        return changePassword;
    }

    public Label getChangePasswordLabel() {
        return changePasswordLabel;
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
