package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.GameModels.Enums.Ability;
import com.untillDawn.Model.GameModels.Enums.HeroType;
import com.untillDawn.Model.GameModels.Enums.WeaponType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Serializable {
    private float time;
    private float wholeTime;
    private Player player;
    private Weapon weapon;
    private boolean isBossSpawned;
    private boolean isAutoAim;
    private ArrayList<Enemy> enemies;
    private ArrayList<Bullet> bullets;
    private ArrayList<XpSeed> xpSeeds;
    private HashMap<Ability, Integer> abilities;
    private transient Texture map;
    private transient Sprite soft;
    private transient Sprite fakeCursor;
    private float mapX, mapY;

    public Game(HeroType heroType, WeaponType weaponType, float wholeTime) {
        this.time = 0.0f;
        player = new Player(heroType);
        weapon = new Weapon(weaponType);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        xpSeeds = new ArrayList<>();
        abilities = new HashMap<>();
        for (Ability ability : Ability.values()) {
            abilities.put(ability, 0);
        }
        isBossSpawned = false;
        isAutoAim = false;

        map = AppAssetManager.getInstance().getMap();
        soft = new Sprite(AppAssetManager.getInstance().getSoft());
        fakeCursor = new Sprite(AppAssetManager.getInstance().getFakeCursor());
        mapX = 0;
        mapY = 0;
        this.wholeTime = wholeTime;
    }

    public void load() {
        map = AppAssetManager.getInstance().getMap();
        soft = new Sprite(AppAssetManager.getInstance().getSoft());
        fakeCursor = new Sprite(AppAssetManager.getInstance().getFakeCursor());
    }

    public void cheatFastForward() {
        time += 60f;
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

    public int getAbility(Ability ability) {
        return abilities.get(ability);
    }

    public void increaseAbility(Ability ability) {
        abilities.put(ability, abilities.getOrDefault(ability, 0) + 1);
    }

    public static void saveGame(String username, Game game) {
        String directoryPath = "gameSaves";
        String filePath = directoryPath + "/" + username + ".dat";

        try {
            Files.createDirectories(Paths.get(directoryPath));
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
                out.writeObject(game);
                System.out.println("Game saved to " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Game loadGame(String username) {
        String path = "gameSaves/" + username + ".dat";
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(path)))) {
            Game game = (Game) in.readObject();
            System.out.println("Game loaded from " + path);

            game.load();
            game.getPlayer().load();
            game.getWeapon().load();
            for(Enemy enemy : game.getEnemies()) {
                enemy.load();
            }
            for(Bullet bullet : game.getBullets()) {
                bullet.load();
            }
            for(XpSeed xpSeed : game.getXpSeeds()) {
                xpSeed.load();
            }

            return game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
