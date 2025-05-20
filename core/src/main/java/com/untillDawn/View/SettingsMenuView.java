package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.SettingsMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.Settings;

public class SettingsMenuView implements Screen {
    public SettingsMenuView() {
        this.controller = SettingsMenuController.getInstance();
        this.assetManager = AppAssetManager.getInstance();

        Skin skin = assetManager.getSkin();

        this.logo = assetManager.getLogo();
        logo.setScaling(Scaling.fit);

        Settings settings = App.getInstance().getSettings();

        float volume = settings.getMusicVolume();
        this.volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(volume);
        this.volumeLabel = new Label(AllTexts.volume.getVal() + " : "+ (int) (volume * 100) + "%", skin);

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float newVolume = volumeSlider.getValue();
                volumeLabel.setText(AllTexts.volume.getVal() + " : " + (int) (newVolume * 100) + "%");
            }
        });

        this.musicBox = new SelectBox<>(skin);
        musicBox.setItems("-none-",
            "1- supersonic",
            "2- cherry metal",
            "3- teryak forosh");
        String chosen;
        switch (settings.getChosenMusic()) {
            case 0:
                chosen = "1- supersonic";
                break;
            case 1:
                chosen = "2- cherry metal";
                break;
            case 2:
                chosen = "3- teryak forosh";
                break;
            default:
                chosen = "-none-";
                break;
        }
        musicBox.setSelected(chosen);
        this.musicLabel = new Label(AllTexts.selectedMusicTrack.getVal(), skin);

        this.SFXCheckbox = new CheckBox(AllTexts.SFX.getVal(), skin);
        SFXCheckbox.setChecked(settings.hasSFX());

        this.autoReloadCheckbox = new CheckBox(AllTexts.autoReload.getVal(), skin);
        autoReloadCheckbox.setChecked(settings.hasAutoReload());

        this.grayScaleCheckbox = new CheckBox(AllTexts.grayScale.getVal(), skin);
        grayScaleCheckbox.setChecked(settings.hasGrayScale());

        this.keybindingsButton = new TextButton(AllTexts.keybindings.getVal(), skin);
        keybindingsButton.setTransform(true);
        keybindingsButton.setOrigin(Align.center);
        keybindingsButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                keybindingsButton.clearActions();
                keybindingsButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                keybindingsButton.clearActions();
                keybindingsButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToKeybindings();
            }
        });

        this.applyButton = new TextButton(AllTexts.apply.getVal(), skin);
        applyButton.setTransform(true);
        applyButton.setOrigin(Align.center);
        applyButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                applyButton.clearActions();
                applyButton.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                applyButton.clearActions();
                applyButton.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.apply();
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

        this.table = new Table(skin);
        controller.setView(this);
    }

    private Stage stage;
    private Image logo;
    private Label volumeLabel;
    private Slider volumeSlider;
    private Label musicLabel;
    private SelectBox<String> musicBox;
    private CheckBox SFXCheckbox;
    private CheckBox autoReloadCheckbox;
    private CheckBox grayScaleCheckbox;
    private TextButton keybindingsButton;
    private TextButton applyButton;
    private TextButton backButton;
    public Table table;

    private final SettingsMenuController controller;
    private final AppAssetManager assetManager;

    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    public SelectBox<String> getMusicBox() {
        return musicBox;
    }

    public CheckBox getSFXCheckbox() {
        return SFXCheckbox;
    }

    public CheckBox getAutoReloadCheckbox() {
        return autoReloadCheckbox;
    }

    public CheckBox getGrayScaleCheckbox() {
        return grayScaleCheckbox;
    }

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

        table.add(volumeLabel).padBottom(5).row();
        table.add(volumeSlider).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 20, 0);

        table.add(musicLabel).padBottom(5).row();
        table.add(musicBox).width(screenWidth * 0.4f);
        table.row().pad(0, 0, 20, 0);

        table.add(SFXCheckbox);
        table.row().pad(0, 0, 20, 0);

        table.add(autoReloadCheckbox);
        table.row().pad(0, 0, 20, 0);

        table.add(grayScaleCheckbox);
        table.row().pad(0, 0, 20, 0);

        table.add(keybindingsButton);
        table.row().pad(0, 0, 20, 0);

        Table buttonRow = new Table();
        buttonRow.add(applyButton).padRight(20);
        buttonRow.add(backButton).padLeft(20);

        table.add(buttonRow).width(screenWidth * 0.4f);
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
