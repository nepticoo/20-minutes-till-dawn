package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.untillDawn.Control.CameControllers.GameController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllTexts;
import com.untillDawn.Model.GameModels.Enums.Ability;
import com.untillDawn.View.MainMenuView;

public class PauseDialog extends Window {

    private final Skin skin;
    private final AppAssetManager assetManager;
    private final Stage stage;

    public PauseDialog(Stage stage) {
        super(AllTexts.pauseTitle.getVal(), AppAssetManager.getInstance().getSkin());
        this.assetManager = AppAssetManager.getInstance();
        this.skin = assetManager.getSkin();
        this.stage = stage;

        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);
        this.align(Align.top);
        this.padTop(40);

        Table content = new Table();
        content.defaults().padTop(10).width(300);

        content.add(makeButton(AllTexts.resume.getVal(), this::resume)).row();
        content.add(makeButton(AllTexts.showCheatCodes.getVal(), this::showCheatCodes)).row();
        content.add(makeButton(AllTexts.showAbilities.getVal(), this::showAbilities)).row();
        content.add(makeButton(AllTexts.toggleGrayScale.getVal(), this::toggleGrayScale)).row();
        content.add(makeButton(AllTexts.saveAndExit.getVal(), this::save)).row();
        content.add(makeButton(AllTexts.giveUp.getVal(), this::giveUp)).row();

        this.add(content).pad(20);
        this.pack();

        this.setPosition(
            stage.getWidth() / 2f - this.getWidth() / 2f,
            stage.getHeight() / 2f - this.getHeight() / 2f
        );

        stage.addActor(this);

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton makeButton(String text, Runnable onClick) {
        TextButton button = new TextButton(text, skin);
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

            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClick.run();
            }
        });

        return button;
    }

    private void resume() {
        this.remove();
        Gdx.input.setInputProcessor(GameController.getInstance().getView());
        GameController.getInstance().setPaused(false);
    }

    private void showCheatCodes() {
        Dialog cheatDialog = new Dialog("", skin);
        cheatDialog.getContentTable().pad(20);
        cheatDialog.text(AllTexts.cheatDialogText.getVal());
        cheatDialog.button(AllTexts.ok.getVal());
        cheatDialog.show(stage);
    }

    private void showAbilities() {
        Dialog abilitiesDialog = new Dialog("", skin);
        abilitiesDialog.getContentTable().pad(20);
        String text = "";
        text += AllTexts.abilitiesDialogText.getVal() + "\n\n";
        for(Ability ability : Ability.values()) {
            text += ability.toString() + " " + App.getInstance().getCurrentUser().getCurrentGame().getAbility(ability) + "x\n\n";
        }
        abilitiesDialog.text(text);
        abilitiesDialog.button(AllTexts.ok.getVal());
        abilitiesDialog.show(stage);
    }

    private void toggleGrayScale() {
        App.getInstance().getSettings().setHasGrayScale(!App.getInstance().getSettings().hasGrayScale());
        GameController.getInstance().setPaused(false);
        Gdx.input.setInputProcessor(GameController.getInstance().getView());
        this.remove();
    }

    private void save() {
        Gdx.input.setInputProcessor(GameController.getInstance().getView());
        this.remove();
        GameController.getInstance().setPaused(false);
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }

    private void giveUp() {
        Gdx.input.setInputProcessor(GameController.getInstance().getView());
        this.remove();
        GameController.getInstance().setPaused(false);
        GameController.getInstance().endGame(false, true);
    }
}
