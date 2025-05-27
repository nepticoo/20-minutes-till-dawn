package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.Enums.Keybinding;
import com.untillDawn.Model.Settings;

import java.util.HashMap;
import java.util.Map;

public class KeybindingsMenuView implements Screen {
    private final AppAssetManager assetManager = AppAssetManager.getInstance();
    private final Settings settings = App.getInstance().getSettings();
    private final Map<Keybinding, TextButton> keyButtons = new HashMap<>();
    private Keybinding waitingForKey = null;

    private Stage stage;
    private Table table;

    private final Skin skin = assetManager.getSkin();
    private final TextButton backButton = new TextButton(AllTexts.back.getVal(), skin);

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(assetManager.getBackgroundImage());

        table = new Table(skin);
        table.setFillParent(true);
        table.center();

        Image logo = assetManager.getLogo();
        table.add(logo).colspan(4).padBottom(40).center();
        table.row();

        addKeybindingRow(this.table, Keybinding.up, Keybinding.down, Keybinding.right, Keybinding.left);
        table.row().pad(20, 0, 0, 0);

        Table secondRow = new Table();
        addKeybindingRow(secondRow, Keybinding.autoAim, Keybinding.reload, Keybinding.pause);
        table.add(secondRow).colspan(4).center();

        table.row().padTop(40);
        Table buttonRow = new Table();
        styleButton(backButton);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getInstance().getScreen().dispose();
                Main.getInstance().setScreen(new MainMenuView());
            }
        });

        buttonRow.add(backButton).pad(10);
        table.add(buttonRow).colspan(4).center();

        stage.addActor(table);
    }


    private void addKeybindingRow(Table targetTable, Keybinding... keys) {
        for (Keybinding key : keys) {
            int keyCode = settings.getAllKeybindings().get(key);
            String label = key.name().toUpperCase();
            String keyStr = Keybinding.toShortString(keyCode);
            TextButton keyButton = new TextButton(label + " : " + keyStr, skin);
            keyButtons.put(key, keyButton);
            styleButton(keyButton);

            keyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    waitingForKey = key;
                    keyButton.setText(label + " : ?");
                }
            });

            targetTable.add(keyButton).pad(10).width(Gdx.graphics.getWidth() * 0.18f).center();
        }
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

        // Listen for new key input if waiting
        if (waitingForKey != null) {
            for (int i = 0; i < Input.Keys.MAX_KEYCODE; i++) {
                if (Gdx.input.isKeyJustPressed(i)) {
                    settings.getAllKeybindings().put(waitingForKey, i);
                    keyButtons.get(waitingForKey).setText(waitingForKey.name().toUpperCase() + " : " + Keybinding.toShortString(i));
                    waitingForKey = null;
                    break;
                }
            }
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
