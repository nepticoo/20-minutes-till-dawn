package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.GameModels.Enums.HeroType;
import com.untillDawn.Model.GameModels.Enums.WeaponType;

import java.util.ArrayList;

public class Game {
    private float time;
    private float wholeTime;
    private Player player;
    private Weapon weapon;
    private boolean isBossSpawned;
    private boolean isAutoAim;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<XpSeed> xpSeeds;
    private Texture map;
    private Sprite soft;
    private Sprite fakeCursor;
    private float mapX, mapY;

    public Game(HeroType heroType, WeaponType weaponType, float wholeTime) {
        this.time = 0.0f;
        player = new Player(heroType);
        weapon = new Weapon(weaponType);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        xpSeeds = new ArrayList<>();
        isBossSpawned = false;
        isAutoAim = false;

        map = AppAssetManager.getInstance().getMap();
        soft = new Sprite(AppAssetManager.getInstance().getSoft());
        fakeCursor = new Sprite(AppAssetManager.getInstance().getFakeCursor());
        mapX = 0;
        mapY = 0;
        this.wholeTime = wholeTime;
    }

    public float getTime() {
        return time;
    }

    public float getWholeTime() {
        return wholeTime;
    }

    public void addDeltaTime(float delta) {
        time += delta;
    }

    public Player getPlayer() {
        return player;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isBossSpawned() {
        return isBossSpawned;
    }

    public void setBossSpawned(boolean bossSpawned) {
        isBossSpawned = bossSpawned;
    }

    public boolean isAutoAim() {
        return isAutoAim;
    }

    public void ToggleAutoAim() {
        isAutoAim = !isAutoAim;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<XpSeed> getXpSeeds() {
        return xpSeeds;
    }

    public Texture getMap() {
        return map;
    }

    public float getMapX() {
        return mapX;
    }

    public float getMapY() {
        return mapY;
    }

    public void setMapX(float mapX) {
        this.mapX = mapX;
    }

    public void setMapY(float mapY) {
        this.mapY = mapY;
    }

    public Sprite getSoft() {
        return soft;
    }

    public Sprite getFakeCursor() {
        return fakeCursor;
    }
}
