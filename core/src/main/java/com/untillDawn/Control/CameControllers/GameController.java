package com.untillDawn.Control.CameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;
import com.untillDawn.Model.Enums.Keybinding;
import com.untillDawn.Model.GameModels.Enemy;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.Model.Settings;
import com.untillDawn.View.GameView;

public class GameController {

    private static GameController instance;

    private GameController() {

    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private Game game;
    private GameView view;
    private PlayerController playerController;
    private WeaponController weaponController;
    private BulletController bulletController;
    private EnemyController enemyController;
    private WorldController worldController;
    private boolean[] isKeyDown;

    private BitmapFont font;
    private ShapeRenderer shapeRenderer;
    private Sprite fullHeart;
    private Sprite emptyHeart;


    public void initialize(Game game, GameView view) {
        this.game = game;
        this.view = view;
        playerController = new PlayerController(game, view);
        weaponController = new WeaponController(game, view);
        bulletController = new BulletController(game, view);
        enemyController = new EnemyController(game, view);
        worldController = new WorldController(game, view);

        isKeyDown = new boolean[4];
        for (int i = 0; i < isKeyDown.length; i++)
            isKeyDown[i] = false;

        AppAssetManager assetManager = AppAssetManager.getInstance();
        shapeRenderer = new ShapeRenderer();
        font = assetManager.getGameDetailsFont();
        fullHeart = new Sprite(assetManager.getFullHeart().getKeyFrame(1f));
        emptyHeart = new Sprite(assetManager.getEmptyHeart());
        fullHeart.setSize(32, 32);
        emptyHeart.setSize(32, 32);

        Settings settings = App.getInstance().getSettings();
        if(settings.getChosenMusic() != -1) {
            Music music = AppAssetManager.getInstance().getSelectedMusics(settings.getChosenMusic());
            if(music != null) {
                music.setLooping(true);
                music.setVolume(settings.getMusicVolume());
                music.play();
            }
        }
    }

    public void updateGame(float delta) {
        game.addDeltaTime(delta);
        worldController.update();
        playerController.update();
        weaponController.update();
        bulletController.update();
        enemyController.update();
        updateFakeCursor();
        updateSoft();
        updateGameDetails();

    }
    public void updateGameDetails() {
        // Get XP bar dimensions
        int level = game.getPlayer().getLevel();
        float xp = game.getPlayer().getXp();
        float requiredXp = game.getPlayer().calRequiredXp();

        float barWidth = Gdx.graphics.getWidth() * 0.8f;
        float barHeight = 40f;
        float barX = (Gdx.graphics.getWidth() - barWidth) / 2f;
        float barY = Gdx.graphics.getHeight() - 50;

        Main.getBatch().end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(AllColors.darkGrey.color);
        shapeRenderer.rect(barX, barY, barWidth, barHeight);
        shapeRenderer.setColor(AllColors.darkGreen.color);
        shapeRenderer.rect(barX, barY, (xp / requiredXp) * barWidth, barHeight);
        shapeRenderer.end();

        Main.getBatch().begin();

        font.draw(Main.getBatch(), "LEVEL " + level, Gdx.graphics.getWidth() / 2f - 30, Gdx.graphics.getHeight() - 15);

        // HP
        fullHeart.setRegion(AppAssetManager.getInstance().getFullHeart().getKeyFrame(game.getTime()));
        int maxHp = game.getPlayer().getMaxHp();
        int hp = game.getPlayer().getHp();
        int empty = maxHp - hp;
        for(int i = 0; i < maxHp; i++) {
            Sprite heart = (i < empty ? emptyHeart : fullHeart);
            heart.setPosition(Gdx.graphics.getWidth() - 40 * (i + 1), Gdx.graphics.getHeight() - 85);
            heart.draw(Main.getBatch());
        }

        // TIME
        int totalSeconds = (int) (game.getWholeTime() - game.getTime());
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        font.draw(Main.getBatch(), timeString, Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 95);

        // AMMO
        int ammo = game.getWeapon().getAmmo();
        int maxAmmo = game.getWeapon().getMaxAmmo();
        font.draw(Main.getBatch(), ammo + "/" + maxAmmo + " AMMO", 10, Gdx.graphics.getHeight() - 55);

        // KILLS
        int kills = game.getPlayer().getKills();
        font.draw(Main.getBatch(), kills + " KILLS", 10, Gdx.graphics.getHeight() - 95);
    }


    public void updateFakeCursor() {
        if (!game.isAutoAim()) {
            return;
        }
        Sprite sprite = game.getFakeCursor();
        Enemy nearest = enemyController.getNearestEnemy();
        if (nearest == null) {
            return;
        }
        float x = nearest.getSprite().getX() + nearest.getSprite().getWidth() / 2;
        float y = nearest.getSprite().getY() + nearest.getSprite().getHeight() / 2;
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
        weaponController.handleWeaponRotation((int) sprite.getX(), (int) (Gdx.graphics.getHeight() - sprite.getY()), true);
        sprite.draw(Main.getBatch());
    }

    public void updateSoft() {
        Sprite sprite = game.getSoft();
        sprite.setSize(Gdx.graphics.getHeight() * 0.7f, Gdx.graphics.getHeight() * 0.7f);
        sprite.setColor(1, 1, 1, 0.05f);
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2, Gdx.graphics.getHeight() / 2 - sprite.getHeight() / 2);
        sprite.draw(Main.getBatch());

    }

    public void handleKeyDown(int keyCode) {
        Settings settings = App.getInstance().getSettings();
        if (keyCode == settings.getKeybinding(Keybinding.up)) {
            isKeyDown[0] = true;
        }
        if (keyCode == settings.getKeybinding(Keybinding.down)) {
            isKeyDown[1] = true;
        }
        if (keyCode == settings.getKeybinding(Keybinding.left)) {
            isKeyDown[2] = true;
            if (game.getPlayer().isSideLeft()) {
                game.getPlayer().turn();
            }
        }
        if (keyCode == settings.getKeybinding(Keybinding.right)) {
            isKeyDown[3] = true;
            if (!game.getPlayer().isSideLeft()) {
                game.getPlayer().turn();
            }
        }
        if (keyCode == settings.getKeybinding(Keybinding.reload)) {
            weaponController.handleReload();
        }
        if (keyCode == settings.getKeybinding(Keybinding.space)) {
            game.ToggleAutoAim();
        }


        // CHEAT CODES

    }

    public void handleKeyUp(int keyCode) {
        Settings settings = App.getInstance().getSettings();
        if (keyCode == settings.getKeybinding(Keybinding.up)) {
            isKeyDown[0] = false;
        }
        if (keyCode == settings.getKeybinding(Keybinding.down)) {
            isKeyDown[1] = false;
        }
        if (keyCode == settings.getKeybinding(Keybinding.left)) {
            isKeyDown[2] = false;
        }
        if (keyCode == settings.getKeybinding(Keybinding.right)) {
            isKeyDown[3] = false;
        }

    }

    public Game getGame() {
        return game;
    }

    public GameView getView() {
        return view;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public EnemyController getEnemyController() {
        return enemyController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public boolean[] getIsKeyDown() {
        return isKeyDown;
    }
}
