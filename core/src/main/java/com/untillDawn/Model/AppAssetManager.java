package com.untillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AppAssetManager {
    private static AppAssetManager instance;

    private AppAssetManager() {
    }

    public static AppAssetManager getInstance() {
        if (instance == null) {
            instance = new AppAssetManager();
        }
        return instance;
    }

    private Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }
}
