package com.untillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Abby_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Dasher_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Diamond_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Hastur_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Hina_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Lilith_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Luna_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Raven_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Scarlett_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Shana_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Spark_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Yuki_Portrait.png")));

        allMusics = new ArrayList<>();
        allMusics.add(Gdx.audio.newMusic(Gdx.files.internal("Music/superepic.mp3")));
        allMusics.add(Gdx.audio.newMusic(Gdx.files.internal("Music/cherry-metal.mp3")));
        allMusics.add(Gdx.audio.newMusic(Gdx.files.internal("Music/teryak-forosh.mp3")));
    }

    public static AppAssetManager getInstance() {
        if (instance == null) {
            instance = new AppAssetManager();
        }
        return instance;
    }

    private final Skin skin;
    private final Image logo;
    private final ArrayList<Texture> allAvatars;
    private final ArrayList<Music> allMusics;


    public Skin getSkin() {
        return skin;
    }

    public Image getLogo() {
        return logo;
    }

    public ArrayList<Texture> getAllAvatars() {
        return allAvatars;
    }

    public ArrayList<Music> getAllMusics() {
        return allMusics;
    }
}
