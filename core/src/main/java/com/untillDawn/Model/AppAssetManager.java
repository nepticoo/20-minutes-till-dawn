package com.untillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;

public class AppAssetManager {
    private static AppAssetManager instance;

    private AppAssetManager() {
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        Texture logoTexture = new Texture(Gdx.files.internal("Images/Sprite/T/T_20logo.png"));
        TextureRegion logoRegion = new TextureRegion(logoTexture);
        Drawable logoDrawable = new TextureRegionDrawable(logoRegion);
        logo = new Image(logoDrawable);

        Texture backgroundTexture = new Texture(Gdx.files.internal("Images/Fixed/background.png"));
        backgroundImage = new Image(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



        allAvatars = new ArrayList<>();
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Shana_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Dasher_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Diamond_Portrait.png")));
        allAvatars.add(new Texture(Gdx.files.internal("Images/Sprite/T/T_Lilith_Portrait.png")));

        allMusics = new ArrayList<>();
        allMusics.add(Gdx.audio.newMusic(Gdx.files.internal("Music/superepic.mp3")));
        allMusics.add(Gdx.audio.newMusic(Gdx.files.internal("Music/cherry-metal.mp3")));
        allMusics.add(Gdx.audio.newMusic(Gdx.files.internal("Music/teryak-forosh.mp3")));

        heroTexture = new HashMap<>();
        idleAnimations = new HashMap<>();
        runAnimations = new HashMap<>();

        gameDetailsFont = new BitmapFont(Gdx.files.internal("Images/fixed/chevyLantern.fnt"));
        map = new Texture(Gdx.files.internal("Images/fixed/map.png"));
        soft = new Texture(Gdx.files.internal("Images/fixed/soft.png"));
        fakeCursor = new Texture(Gdx.files.internal("Images/fixed/fakeCursor.png"));
        Xp = new Texture(Gdx.files.internal("Images/fixed/Xp.png"));

        String[] heroes = {"shana", "diamond", "scarlett", "lilith", "dasher"};
        for (String hero : heroes) {
            Texture texture = new Texture(Gdx.files.internal("Images/fixed/Idle/" + hero + "/Idle_0.png"));
            heroTexture.put(hero, texture);

            Array<Texture> idle = new Array<>();
            for (int j = 0; j < 6; j++) {
                idle.add(new Texture(Gdx.files.internal("Images/fixed/Idle/" + hero + "/Idle_" + j + ".png")));
            }
            idleAnimations.put(hero, new Animation<>(0.1f, idle));

            Array<Texture> run = new Array<>();
            for (int j = 0; j < 4; j++) {
                run.add(new Texture(Gdx.files.internal("Images/fixed/Run/" + hero + "/Run_" + j + ".png")));
            }
            runAnimations.put(hero, new Animation<>(0.1f, run));
        }

        weaponTexture = new HashMap<>();
        reloadAnimations = new HashMap<>();

        String[] weapons = {"revolver", "shotgun", "smg"};
        for (String weapon : weapons) {
            Texture texture = new Texture(Gdx.files.internal("Images/fixed/Guns/" + weapon + "/" + weapon + ".png"));
            weaponTexture.put(weapon, texture);

            Array<Texture> reload = new Array<>();
            for (int j = 0; j < 4; j++) {
                FileHandle file = Gdx.files.internal("Images/fixed/Guns/" + weapon + "/" + weapon + "-reload_" + j + ".png");
                if(file.exists()) {
                    reload.add(new Texture(file));
                }
            }
            reloadAnimations.put(weapon, new Animation<>(0.1f, reload));
        }

        bulletTexture = new Texture(Gdx.files.internal("Images/fixed/Bullet/bullet.png"));
        enemyBulletTexture = new Texture(Gdx.files.internal("Images/fixed/Bullet/bullet-2.png"));
        bulletDeathAnimation = new Animation<>(0.1f
            , new Texture(Gdx.files.internal("Images/fixed/Bullet/bullet-death_0.png"))
            , new Texture(Gdx.files.internal("Images/fixed/Bullet/bullet-death_1.png"))
            , new Texture(Gdx.files.internal("Images/fixed/Bullet/bullet-death_2.png")));

        enemyTexture = new HashMap<>();
        enemyAnimation = new HashMap<>();

        String[] enemies = {"tree", "brainMonster", "eyeBat", "elder"};
        for(String enemy : enemies) {
            Texture texture = new Texture(Gdx.files.internal("Images/fixed/Enemy/" + enemy + "/" + enemy + "_0.png"));

            enemyTexture.put(enemy, texture);

            Array<Texture> animation = new Array<>();
            for(int i = 0; i < 6; i++) {
                FileHandle file = Gdx.files.internal("Images/fixed/Enemy/" + enemy + "/" + enemy + "_" + i + ".png");
                if(file.exists()) {
                    animation.add((new Texture(file)));
                }
            }
            enemyAnimation.put(enemy, new Animation<>((enemy.equals("tree") ? 0.3f : 0.1f), animation));
        }

        Array<Texture> death = new Array<>();
        for(int i = 0; i < 5; i++) {
            FileHandle file = Gdx.files.internal("Images/fixed/Enemy/death/death_" + i + ".png");
            if(file.exists()) {
                death.add(new Texture(file));
            }
        }
        enemyDeathAnimation = new Animation<>(0.1f, death);

        emptyHeart = new Texture(Gdx.files.internal("Images/fixed/Heart/emptyHeart.png"));
        Array<Texture> heart = new Array<>();
        for(int i = 0; i < 3; i++) {
            heart.add(new Texture(Gdx.files.internal("Images/fixed/Heart/fullHeart_" + i + ".png")));
        }
        fullHeart = new Animation<>(0.1f, heart);
        fullHeart.setPlayMode(Animation.PlayMode.LOOP);

        SFX = new HashMap<>();
        String[] SFXNames = {"enemyDeath", "lose", "win", "reload", "shot", "xp"};
        for (String sfx : SFXNames) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("Images/fixed/SFX/" + sfx + ".wav"));
            SFX.put(sfx, sound);
        }


    }

    public static AppAssetManager getInstance() {
        if (instance == null) {
            instance = new AppAssetManager();
        }
        return instance;
    }

    private final Skin skin;
    private final Image logo;
    private final Image backgroundImage;
    private final ArrayList<Texture> allAvatars;
    private final BitmapFont gameDetailsFont;
    private final Texture map;
    private final Texture soft;
    private final Texture fakeCursor;
    private final Texture Xp;
    private final ArrayList<Music> allMusics;
    private final HashMap<String, Texture> heroTexture;
    private final HashMap<String, Animation<Texture>> idleAnimations;
    private final HashMap<String, Animation<Texture>> runAnimations;
    private final HashMap<String, Texture> weaponTexture;
    private final HashMap<String, Animation<Texture>> reloadAnimations;
    private final Texture bulletTexture;
    private final Texture enemyBulletTexture;
    private final Animation<Texture> bulletDeathAnimation;
    private final HashMap<String, Texture> enemyTexture;
    private final HashMap<String, Animation<Texture>> enemyAnimation;
    private final Animation<Texture> enemyDeathAnimation;
    private final Texture emptyHeart;
    private final Animation<Texture> fullHeart;
    private final HashMap<String, Sound> SFX;


    public Skin getSkin() {
        return skin;
    }

    public Image getBackgroundImage() {
        return backgroundImage;
    }

    public Image getLogo() {
        return logo;
    }

    public ArrayList<Texture> getAllAvatars() {
        return allAvatars;
    }

    public Music getSelectedMusics(int option) {
        if(option < 0 || option >= allMusics.size()) {
            return null;
        }
        return allMusics.get(option);
    }

    public BitmapFont getGameDetailsFont() {
        return gameDetailsFont;
    }

    public Texture getMap() {
        return map;
    }

    public Texture getSoft() {
        return soft;
    }

    public Texture getFakeCursor() {
        return fakeCursor;
    }

    public Texture getXp() {
        return Xp;
    }

    public Texture getHeroTexture(String name) {
        return heroTexture.get(name);
    }

    public Animation<Texture> getIdleAnimation(String name) {
        return idleAnimations.get(name);
    }

    public Animation<Texture> getRunAnimation(String name) {
        return runAnimations.get(name);
    }

    public Texture getWeaponTexture(String name) {
        return weaponTexture.get(name);
    }

    public Animation<Texture> getReloadAnimation(String name) {
        return reloadAnimations.get(name);
    }

    public Texture getBulletTexture() {
        return bulletTexture;
    }

    public Texture getEnemyBulletTexture() {
        return enemyBulletTexture;
    }

    public Animation<Texture> getBulletDeathAnimation() {
        return bulletDeathAnimation;
    }

    public Texture getEnemyTexture(String name) {
        return enemyTexture.get(name);
    }

    public Animation<Texture> getEnemyAnimation(String name) {
        return enemyAnimation.get(name);
    }

    public Animation<Texture> getEnemyDeathAnimation() {
        return enemyDeathAnimation;
    }

    public Animation<Texture> getFullHeart() {
        return fullHeart;
    }

    public Texture getEmptyHeart() {
        return emptyHeart;
    }

    public Sound getSFX(String name) {
        return SFX.get(name);
    }
}

