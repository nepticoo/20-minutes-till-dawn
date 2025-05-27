package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.untillDawn.Control.CameControllers.GameController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.User;

public class EndGameMenuView implements Screen {
    private final Stage stage;
    private final Table table;
    private final boolean win;
    private final AppAssetManager assetManager;

    public EndGameMenuView(boolean win, boolean gaveUp) {
        this.win = win;
        this.stage = new Stage(new ScreenViewport());
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();
        this.table = new Table(skin);
        table.setFillParent(true);
        table.center();

        User user = App.getInstance().getCurrentUser();
        int kills = user.getCurrentGame().getPlayer().getKills();
        int timeSurvived = (int) user.getCurrentGame().getTime();
        int score = kills * timeSurvived;

        Label.LabelStyle labelStyle = new Label.LabelStyle(assetManager.getGameDetailsFont(), AllColors.silver.color);

        table.add(new Label(AllTexts.gameFinished.getVal(), skin, "title")).padBottom(40).colspan(2).row();

        TextureRegion avatarRegion = new TextureRegion((Texture) user.getAvatar());
        Image avatarImage = new Image(avatarRegion);
        avatarImage.setScaling(Scaling.fit);
        avatarImage.setSize(48, 48);

        Table usernameRow = new Table();
        usernameRow.add(avatarImage).size(48, 48).padRight(10);
        usernameRow.add(new Label(user.getUsername(), labelStyle)).align(Align.left);

        table.add(new Label(AllTexts.username.getVal(), labelStyle)).align(Align.left);
        table.add(usernameRow).align(Align.left).row();

        table.add(new Label(AllTexts.timeSurvived.getVal(), labelStyle)).align(Align.left);
        table.add(new Label(timeSurvived + " seconds", labelStyle)).align(Align.left).row();

        table.add(new Label(AllTexts.kills.getVal(), labelStyle)).align(Align.left);
        table.add(new Label(String.valueOf(kills), labelStyle)).align(Align.left).row();

        table.add(new Label(AllTexts.score.getVal(), labelStyle)).align(Align.left);
        table.add(new Label(String.valueOf(score), labelStyle)).align(Align.left).row();

        String resultText = win ? AllTexts.youWon.getVal() : (gaveUp ? AllTexts.youGaveUp.getVal() : AllTexts.youDied.getVal());
        Label resultLabel = new Label(resultText, skin, "title");
        resultLabel.setColor(win ? AllColors.green.color : AllColors.red.color);

        table.row().padTop(40);
        table.add(resultLabel).colspan(2).center();

        TextButton continueButton = new TextButton(AllTexts.continueText.getVal(), skin);
        continueButton.setTransform(true);
        continueButton.setOrigin(Align.center);
        continueButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                continueButton.clearActions();
                continueButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                continueButton.clearActions();
                continueButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameController.getInstance().continueToMain();
            }
        });
        table.row().padTop(30);
        table.add(continueButton).colspan(2).center();

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
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
    public void resize(int width, int height) {
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
