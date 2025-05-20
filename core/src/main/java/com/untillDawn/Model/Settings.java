package com.untillDawn.Model;

import com.badlogic.gdx.Input;
import com.untillDawn.Model.Enums.Keybinding;

import java.util.HashMap;

public class Settings {
    private float musicVolume;
    private int chosenMusic;
    private boolean hasSFX;
    private boolean hasGrayScale;
    private boolean hasAutoReload;
    HashMap<Keybinding, Integer> keybindings;

    public Settings() {
        musicVolume = 0.5f;
        chosenMusic = -1;
        hasSFX = true;
        hasGrayScale = false;
        hasAutoReload = false;
        keybindings = new HashMap<>();
//        TODO: add keybindings
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int getChosenMusic() {
        return chosenMusic;
    }

    public void setChosenMusic(int chosenMusic) {
        this.chosenMusic = chosenMusic;
    }

    public boolean hasSFX() {
        return hasSFX;
    }

    public void setHasSFX(boolean hasSFX) {
        this.hasSFX = hasSFX;
    }

    public boolean hasGrayScale() {
        return hasGrayScale;
    }

    public void setHasGrayScale(boolean hasGrayScale) {
        this.hasGrayScale = hasGrayScale;
    }

    public boolean hasAutoReload() {
        return hasAutoReload;
    }

    public void setHasAutoReload(boolean hasAutoReload) {
        this.hasAutoReload = hasAutoReload;
    }

    public void setKeybinding(Keybinding keybinding, int key) {
        keybindings.put(keybinding, key);
    }

    public void getKeybinding(Keybinding keybinding) {
        keybindings.get(keybinding);
    }
}
