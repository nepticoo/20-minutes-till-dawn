package com.untillDawn.Control;

import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.Settings;
import com.untillDawn.View.MainMenuView;
import com.untillDawn.View.SettingsMenuView;

public class SettingsMenuController {
    private static SettingsMenuController instance;

    private SettingsMenuController() {}

    public static SettingsMenuController getInstance() {
        if (instance == null) {
            instance = new SettingsMenuController();
        }
        return instance;
    }

    private SettingsMenuView view;

    public void setView(SettingsMenuView view) {
        this.view = view;
    }

    public void goToKeybindings() {

    }

    public void apply() {
        App app = App.getInstance();
        Settings settings = app.getSettings();

        settings.setMusicVolume(view.getVolumeSlider().getValue());
        String chosenString = view.getMusicBox().getSelected();
        if(chosenString.startsWith("1")) {
            settings.setChosenMusic(0);
        }
        else if(chosenString.startsWith("2")) {
            settings.setChosenMusic(1);
        }
        else if(chosenString.startsWith("3")) {
            settings.setChosenMusic(2);
        }
        else {
            settings.setChosenMusic(-1);
        }
        settings.setHasSFX(view.getSFXCheckbox().isChecked());
        settings.setHasAutoReload(view.getAutoReloadCheckbox().isChecked());
        settings.setHasGrayScale(view.getGrayScaleCheckbox().isChecked());

//        TODO: handle KeyBinding

        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }

    public void back() {
        Main.getInstance().getScreen().dispose();
        Main.getInstance().setScreen(new MainMenuView());
    }
}
