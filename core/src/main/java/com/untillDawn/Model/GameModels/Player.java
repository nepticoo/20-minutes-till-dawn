package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Control.CameControllers.GameController;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.GameModels.Enums.Ability;
import com.untillDawn.Model.GameModels.Enums.HeroType;
import com.untillDawn.View.AbilityDialog;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private float x, y;
    private CollisionRect rect;
    private float speed;
    private int hp;
    private int maxHp;
    private int kills;
    private int level;
    private int xp;
    private transient Texture texture;
    private transient Sprite sprite;
    private transient Animation<Texture> idleAnimation;
    private transient Animation<Texture> runAnimation;
    private boolean isRunning;
    private boolean sideLeft;
    private boolean isInvincible;
    private float invincibleTime;

    public Player(HeroType heroType) {
        name = heroType.name();
        x = 0f;
        y = 0f;
        speed = heroType.getSpeed() * 1.2f;
        hp = heroType.getHp();
        maxHp = hp;
        kills = 0;
        level = 1;
        xp = 0;
        texture = heroType.getTexture();
        sprite = heroType.getSprite();
        idleAnimation = heroType.getIdleAnimation();
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        runAnimation = heroType.getRunAnimation();
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
        isRunning = false;
        sideLeft = true;
        isInvincible = false;
        invincibleTime = -10f;

        sprite.setSize(texture.getWidth() * 3, texture.getHeight() * 3);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setPosition(
            (float) Gdx.graphics.getWidth() / 2f - sprite.getWidth() / 2f,
            (float) Gdx.graphics.getHeight() / 2f - sprite.getHeight() / 2f);
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void load() {
        AppAssetManager assetManager = AppAssetManager.getInstance();

        texture = assetManager.getHeroTexture(name);
        sprite = new Sprite(texture);
        idleAnimation = assetManager.getIdleAnimation(name);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        runAnimation = assetManager.getRunAnimation(name);
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sprite.setSize(texture.getWidth() * 3, texture.getHeight() * 3);
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setPosition(
            (float) Gdx.graphics.getWidth() / 2f - sprite.getWidth() / 2f,
            (float) Gdx.graphics.getHeight() / 2f - sprite.getHeight() / 2f);
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void move(float dx, float dy) {
        float len = (float) Math.sqrt(dx * dx + dy * dy);
        if (len < 0.01f)
            return;
        this.x += speed * dx / len;
        this.y += speed * dy / len;
        if (x >= (float) Gdx.graphics.getWidth() / 2f - sprite.getWidth() / 2f)
            x = (float) Gdx.graphics.getWidth() / 2f - sprite.getWidth() / 2f;
        if (y >= (float) Gdx.graphics.getHeight() / 2f - sprite.getHeight() / 2f)
            y = (float) Gdx.graphics.getHeight() / 2f - sprite.getHeight() / 2f;
        if (x <= -6400 + (float) Gdx.graphics.getWidth() / 2f + sprite.getWidth() / 2f)
            x = -6400 + (float) Gdx.graphics.getWidth() / 2f + sprite.getWidth() / 2f;
        if (y <= -6400 + (float) Gdx.graphics.getHeight() / 2f + sprite.getHeight() / 2f)
            y = -6400 + (float) Gdx.graphics.getHeight() / 2f + sprite.getHeight() / 2f;
    }

    public void turn() {
        sideLeft = !sideLeft;
        sprite.flip(true, false);
    }

    public void kill() {
        GameController.getInstance().endGame(false, false);
    }

    public void takeDamage(float time) {
        hp--;
        if (hp <= 0) {
            kill();
        }
        isInvincible = true;
        invincibleTime = time;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getKills() {
        return kills;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int calRequiredXp() {
        return 20 * level;
    }

    public void addMaxHp() {
        maxHp++;
        hp++;
    }

    public void startSpeedy() {
        speed *= 2f;
    }

    public void endSpeedy() {
        speed /= 2f;
    }

    public void addXp(int increment) {
        xp += increment;
        if (xp >= calRequiredXp()) {
            xp -= calRequiredXp();
            level++;
            GameController.getInstance().setPaused(true);
            new AbilityDialog(GameController.getInstance().getView().getStage());
        }
    }

    public void addKills() {
        kills++;
    }

    public void cheatAddSpeed() {
        speed += 1f;
    }

    public void cheatAddHp() {
        if(hp < maxHp) {
            hp++;
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Animation<Texture> getIdleAnimation() {
        return idleAnimation;
    }

    public Animation<Texture> getRunAnimation() {
        return runAnimation;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isSideLeft() {
        return sideLeft;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setSideLeft(boolean sideLeft) {
        this.sideLeft = sideLeft;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public float getInvincibleTime() {
        return invincibleTime;
    }
}
