package com.untillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Arrays;

public class AppAssetManager {
    private static AppAssetManager instance;

    private AppAssetManager() {
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Texture logoTexture = new Texture(Gdx.files.internal("Images/Sprite/T/T_20logo.png"));
        TextureRegion logoRegion = new TextureRegion(logoTexture);
        Drawable logoDrawable = new TextureRegionDrawable(logoRegion);
        logo = new Image(logoDrawable);

        allAvatars = new ArrayList<>();

    }

    public static AppAssetManager getInstance() {
        if (instance == null) {
            instance = new AppAssetManager();
        }
        return instance;
    }

    private final Skin skin;
    private Image logo;
    private final ArrayList<Texture> allAvatars;


    public Skin getSkin() {
        return skin;
    }

    public Image getLogo() {
        return logo;
    }

    public ArrayList<Texture> getAllAvatars() {
        return allAvatars;
    }
}
