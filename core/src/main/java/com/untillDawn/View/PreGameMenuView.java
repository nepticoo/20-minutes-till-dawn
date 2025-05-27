package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.PreGameMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.GameModels.Enums.HeroType;
import com.untillDawn.Model.GameModels.Enums.WeaponType;

public class PreGameMenuView implements Screen {

    private final AppAssetManager assetManager = AppAssetManager.getInstance();
    private final Skin skin = assetManager.getSkin();
    private Stage stage;

    private SelectBox<String> heroSelectBox;
    private SelectBox<String> weaponSelectBox;
    private SelectBox<String> durationSelectBox;

    private TextButton playButton;
    private TextButton backButton;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(assetManager.getBackgroundImage());

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center().top().padTop(50);

        Image logo = assetManager.getLogo();
        mainTable.add(logo).colspan(2).padBottom(40).center().row();

        Table firstRow = new Table();

        Table heroCol = new Table();
        Label heroLabel = new Label("Choose your hero:", skin);
        heroLabel.setFontScale(1.2f);
        heroSelectBox = createSelectBox(new String[]{"shana", "diamond", "scarlett", "lilith", "dasher"});
        heroCol.add(heroLabel).padBottom(10).row();
        heroCol.add(heroSelectBox).width(400).height(100);

        Table weaponCol = new Table();
        Label weaponLabel = new Label("Choose your weapon:", skin);
        weaponLabel.setFontScale(1.2f);
        weaponSelectBox = createSelectBox(new String[]{"revolver", "shotgun", "smg"});
        weaponCol.add(weaponLabel).padBottom(10).row();
        weaponCol.add(weaponSelectBox).width(400).height(100);

        firstRow.add(heroCol).pad(30);
        firstRow.add(weaponCol).pad(30);

        mainTable.add(firstRow).colspan(2).padBottom(40).row();

        Table durationCol = new Table();
        Label durationLabel = new Label("Choose time duration:", skin);
        durationLabel.setFontScale(1.2f);
        durationSelectBox = createSelectBox(new String[]{"2 mins", "5 mins", "10 mins", "20 mins"});
        durationCol.add(durationLabel).padBottom(10).row();
        durationCol.add(durationSelectBox).width(400).height(100);

        mainTable.add(durationCol).colspan(2).padBottom(40).center().row();

        backButton = new TextButton(AllTexts.back.getVal(), skin);
        playButton = new TextButton(AllTexts.play.getVal(), skin);
        styleButton(backButton);
        styleButton(playButton);

        backButton.pad(30);
        playButton.pad(30);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PreGameMenuController.getInstance().back();
            }
        });

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HeroType hero = HeroType.getHeroType(heroSelectBox.getSelected());
                WeaponType weapon = WeaponType.getWeaponType(weaponSelectBox.getSelected());
                String durationString = durationSelectBox.getSelected().trim();
                int duration = 0;
                switch (durationString) {
                    case "2 mins":
                        duration = 2;
                        break;
                    case "5 mins":
                        duration = 5;
                        break;
                    case "10 mins":
                        duration = 10;
                        break;
                    case "20 mins":
                        duration = 20;
                        break;
                }
                PreGameMenuController.getInstance().play(hero, weapon, duration);
            }
        });

        Table buttonRow = new Table();
        buttonRow.add(backButton).pad(20);
        buttonRow.add(playButton).pad(20);
        mainTable.add(buttonRow).colspan(2).center();

        stage.addActor(mainTable);
    }


    private SelectBox<String> createSelectBox(String[] options) {
        BitmapFont font = skin.getFont("subtitle2");
        font.getData().setScale(1.2f);

        SelectBox.SelectBoxStyle style = new SelectBox.SelectBoxStyle(skin.get(SelectBox.SelectBoxStyle.class));
        style.font = font;

        SelectBox<String> box = new SelectBox<>(style);
        box.setItems(options);
        return box;
    }


    private void styleButton(final TextButton button) {
        button.setTransform(true);
        button.setOrigin(Align.center);
        button.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                button.clearActions();
                button.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.clearActions();
                button.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(AllColors.backGround.color);

        if (App.getInstance().getSettings().hasGrayScale()) {
            stage.getBatch().setShader(Main.getGrayScaleShader());
        } else {
            stage.getBatch().setShader(null);
        }

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
