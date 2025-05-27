package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.untillDawn.Control.ScoreboardMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardMenuView implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final Table rootTable;
    private final ScrollPane scrollPane;
    private final Table scoresTable;
    private final AppAssetManager assetManager;
    private final User currentUser;
    private final ScoreboardMenuController controller;

    private final TextButton sortByScore;
    private final TextButton sortByUsername;
    private final TextButton sortByKills;
    private final TextButton sortBySurvival;
    private final TextButton backButton;

    public ScoreboardMenuView() {
        this.controller = ScoreboardMenuController.getInstance();
        assetManager = AppAssetManager.getInstance();
        skin = assetManager.getSkin();
        currentUser = App.getInstance().getCurrentUser();

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.center();

        scoresTable = new Table(skin);
        scoresTable.center();
        scrollPane = new ScrollPane(scoresTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setScrollbarsOnTop(true);

        sortByScore = makeSortButton("Sort by Score", Comparator.comparingInt(User::getScore).reversed());
        sortByUsername = makeSortButton("Sort by Username", Comparator.comparing(User::getUsername));
        sortByKills = makeSortButton("Sort by Kills", Comparator.comparingInt(User::getKills).reversed());
        sortBySurvival = makeSortButton("Sort by Time", Comparator.comparingInt(User::getMaxAliveTime).reversed());

        Table sortButtonTable = new Table();
        sortButtonTable.add(sortByScore).pad(5);
        sortButtonTable.add(sortByUsername).pad(5);
        sortButtonTable.add(sortByKills).pad(5);
        sortButtonTable.add(sortBySurvival).pad(5);

        backButton = new TextButton(AllTexts.back.getVal(), skin);
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

        rootTable.add(new Label("Scoreboard", skin, "title")).pad(20);
        rootTable.row();
        rootTable.add(scrollPane).height(Gdx.graphics.getHeight() * 0.6f).width(Gdx.graphics.getWidth() * 0.8f);
        rootTable.row().padTop(20);
        rootTable.add(sortButtonTable);
        rootTable.row().padTop(20);
        rootTable.add(backButton);

        stage.addActor(rootTable);
        sortAndDisplay(Comparator.comparingInt(User::getScore).reversed());
    }

    private TextButton makeSortButton(String text, Comparator<User> comparator) {
        TextButton button = new TextButton(text, skin);
        button.setTransform(true);
        button.setOrigin(Align.center);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sortAndDisplay(comparator);
            }
        });
        return button;
    }

    private void sortAndDisplay(Comparator<User> comparator) {
        scoresTable.clear();

        List<User> allUsers = new ArrayList<>(App.getInstance().getUsers());
        allUsers.sort(comparator);
        List<User> topUsers = allUsers.stream().limit(10).collect(Collectors.toList());

        for (int i = 0; i < topUsers.size(); i++) {
            User user = topUsers.get(i);

            Table container = new Table(skin);
            container.left();

            Table row = new Table(skin);
            row.left();

//            if (i == 0) row.setBackground("default-round");
            if (i == 1) row.setColor(AllColors.red.color);
            else if (i == 2) row.setColor(AllColors.green.color);

            if (user == currentUser) {
                row.setColor(Color.FIREBRICK);
            }

            ArrayList<Label> labels = new ArrayList<>();
            labels.add(new Label(String.valueOf(i + 1), skin));
            labels.add(new Label(user.getUsername(), skin));
            labels.add(new Label("Score: " + user.getScore(), skin));
            labels.add(new Label("Score: " + user.getScore(), skin));
            labels.add(new Label("Kills: " + user.getKills(), skin));
            String time = (int)user.getMaxAliveTime() / 60 + ":" + (int)user.getMaxAliveTime() % 60;
            labels.add(new Label("Time: " + time, skin));
            Color color;
            switch (i) {
                case 0:
                    color = AllColors.gold.color;
                    break;
                case 1:
                    color = AllColors.silver.color;
                    break;
                case 2:
                    color = AllColors.bronze.color;
                    break;
                default:
                    color = Color.WHITE;
                    break;
            }

            for (Label label : labels) {
                label.setColor(color);
            }

            row.add(labels.get(0)).width(30).padRight(10);
            row.add(new Image(user.getAvatar())).size(40).padRight(10);
            for (int j = 1; j < labels.size(); j++) {
                row.add(labels.get(j)).width(270);
            }
            container.add(row).expandX().fillX();
            if (user.equals(currentUser)) {
                container.row();
                Image underline = new Image(skin.newDrawable("white", color));
                underline.setColor(color);
                container.add(underline).height(2).expandX().fillX();
            }
            scoresTable.add(container).pad(5).row();

        }
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
        stage.dispose();
    }
}
