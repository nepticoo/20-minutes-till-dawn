package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Control.CameControllers.GameController;
import com.untillDawn.Model.GameModels.Enums.Ability;

import java.util.*;
import java.util.List;

public class AbilityDialog extends Dialog {

    private final Skin skin;
    private final Stage stage;

    public AbilityDialog(Stage stage) {
        super("Choose an Ability", AppAssetManager.getInstance().getSkin());
        this.skin = AppAssetManager.getInstance().getSkin();
        this.stage = stage;

        this.setModal(true);
        this.setMovable(false);
        this.setResizable(false);
        this.align(Align.top);
        this.padTop(40);
        getContentTable().pad(20);
        getButtonTable().pad(10);

        List<Ability> allAbilities = new ArrayList<>(Arrays.asList(Ability.values()));
        Collections.shuffle(allAbilities);
        List<Ability> chosenAbilities = allAbilities.subList(0, 3);
        Gdx.input.setInputProcessor(stage);

        for (Ability ability : chosenAbilities) {
            TextButton button = new TextButton(ability.toString(), skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    GameController.getInstance().applyAbility(ability);
                    GameController.getInstance().setPaused(false);
                    Gdx.input.setInputProcessor(GameController.getInstance().getView());
                    hide();
                }
            });
            getContentTable().add(button).width(300).padTop(10).row();
        }

        show(stage);
    }
}
