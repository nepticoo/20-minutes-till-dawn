package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
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
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.Enums.Keybinding;
import com.untillDawn.Model.Settings;

import java.util.ArrayList;

public class HintMenuView implements Screen {
    private Stage stage;
    private final AppAssetManager assetManager = AppAssetManager.getInstance();
    private final Skin skin = assetManager.getSkin();
    private final Table contentTable = new Table();
    private final ScrollPane scrollPane;
    private final TextButton backButton;

    public HintMenuView() {
        contentTable.pad(20).top();
        contentTable.defaults().pad(15);
        contentTable.align(Align.top);
        contentTable.center();

        addHeroHintsSection();

        addSection(AllTexts.ACTIVE_KEY_BINDINGS.getVal(), getKeyBindingHints());

        addSection(AllTexts.CHEAT_CODES.getVal(), new String[]{
            AllTexts.CHEAT_U.getVal(),
            AllTexts.CHEAT_I.getVal(),
            AllTexts.CHEAT_J.getVal(),
            AllTexts.CHEAT_K.getVal(),
            AllTexts.CHEAT_M.getVal()
        });

        addSection(AllTexts.ABILITIES.getVal(), new String[]{
            AllTexts.ABILITY_VITALITY.getVal(),
            AllTexts.ABILITY_DAMAGER.getVal(),
            AllTexts.ABILITY_PROCREASE.getVal(),
            AllTexts.ABILITY_AMMOCREASE.getVal(),
            AllTexts.ABILITY_SPEEDY.getVal()
        });

        scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setOverscroll(false, true);
        scrollPane.setFillParent(false);

        backButton = createHoverButton(AllTexts.back.getVal(), () -> {
            Main.getInstance().setScreen(new MainMenuView());
        });
    }

    private void addHeroHintsSection() {
        Table card = new Table(skin);
        card.background(skin.getDrawable("window-round"));
        card.pad(20);
        card.align(Align.top);
        card.defaults().padBottom(10).center();

        Label titleLabel = new Label(AllTexts.HINTS_TITLE.getVal(), skin, "title");
        titleLabel.setColor(AllColors.gold.color);
        titleLabel.setFontScale(1.3f);
        titleLabel.setAlignment(Align.center);
        card.add(titleLabel).colspan(2).center().row();

        String[][] heroes = {
            {"shana", "SHANA", "HP: 4, SPEED: 4", AllTexts.SHANA_HINT.getVal()},
            {"diamond", "DIAMOND", "HP: 7, SPEED: 1", AllTexts.DIAMOND_HINT.getVal()},
            {"scarlett", "SCARLETT", "HP: 3, SPEED: 5", AllTexts.SCARLETT_HINT.getVal()},
            {"lilith", "LILITH", "HP: 5, SPEED: 3", AllTexts.LILITH_HINT.getVal()},
            {"dasher", "DASHER", "HP: 2, SPEED: 10", AllTexts.DASHER_HINT.getVal()}
        };

        for (String[] hero : heroes) {
            Texture texture = assetManager.getHeroTexture(hero[0]);

            Image heroImage = new Image(texture);
            heroImage.setScaling(Scaling.fit);
            heroImage.setColor(1f, 1f, 1f, 0.9f);
            heroImage.setSize(80, 80);

            Table textTable = new Table();
            Label heroName = new Label("~>" + hero[1] + ": (" + hero[2] + "):", skin);
            heroName.setColor(AllColors.gold.color);
            heroName.setFontScale(1.1f);
            heroName.setAlignment(Align.left);

            Label mythicLine = new Label("\"" + hero[3] + "\"", skin);
            mythicLine.setWrap(true);
            mythicLine.setFontScale(0.9f);
            mythicLine.setColor(0.8f, 0.8f, 0.8f, 1f);
            mythicLine.setAlignment(Align.left);

            textTable.add(heroName).left().row();
            textTable.add().height(6).row();
            textTable.add(mythicLine).width(400).left();

            Table heroRow = new Table();
            heroRow.add(heroImage).size(80, 80).padRight(15);
            heroRow.add(textTable).left();
            heroRow.center();

            card.add(heroRow).center().row();
            card.add().height(30).colspan(2).row();
        }

        contentTable.add(card).expandX().fillX().center().row();
    }

    private void addSection(String title, String[] lines) {
        Table card = new Table(skin);
        card.background(skin.getDrawable("window-round"));
        card.pad(20).top();
        card.defaults().padBottom(10).center();
        card.align(Align.top);

        Label titleLabel = new Label(title, skin, "title");
        titleLabel.setColor(AllColors.gold.color);
        titleLabel.setFontScale(1.3f);
        titleLabel.setAlignment(Align.center);
        card.add(titleLabel).center().row();

        for (String line : lines) {
            Label label = new Label(line, skin);
            label.setWrap(true);
            label.setAlignment(Align.center);
            card.add(label).width(800).center().row();
            card.add().height(20).row();
        }

        contentTable.add(card).center().fillX().row();
    }

    private String[] getKeyBindingHints() {
        Settings settings = App.getInstance().getSettings();
        ArrayList<String> lines = new ArrayList<>();
        for(Keybinding key : Keybinding.values()) {
            lines.add("~>" + key.name() + ": – " + Keybinding.toShortString(settings.getKeybinding(key)));
        }
        return lines.toArray(new String[0]);
    }

    private TextButton createHoverButton(String text, Runnable onClick) {
        TextButton button = new TextButton(text, skin);
        button.setTransform(true);
        button.setOrigin(Align.center);
        button.addListener(new ClickListener() {
            public void enter(InputEvent e, float x, float y, int p, Actor fromActor) {
                button.clearActions();
                button.addAction(Actions.scaleTo(1.1f, 1.1f, 0.1f));
            }

            public void exit(InputEvent e, float x, float y, int p, Actor toActor) {
                button.clearActions();
                button.addAction(Actions.scaleTo(1f, 1f, 0.1f));
            }

            public void clicked(InputEvent e, float x, float y) {
                onClick.run();
            }
        });
        return button;
    }

    @Override public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        stage.addActor(assetManager.getBackgroundImage());

        Table root = new Table();
        root.setFillParent(true);
        root.top().padTop(30);
        root.center();

        root.add(scrollPane).expand().center().padBottom(20).row();

        Table bottom = new Table();
        bottom.add(backButton).padTop(20).center();
        root.add(bottom).center();

        root.getColor().a = 0;
        root.addAction(Actions.fadeIn(0.6f));

        stage.addActor(root);
    }

    @Override public void render(float delta) {
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

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
