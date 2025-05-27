package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;

import java.io.Serializable;

public class Bullet implements Serializable {
    private int damage;
    private float x, y;
    private float direction;
    private float speed;
    private boolean isEnemyBullet;
    private CollisionRect rect;
    private float shootingTime;
    private boolean isDead;
    private float deathTime;
    private float deathDuration;
    private transient Texture texture;
    private transient Sprite sprite;
    private transient Animation<Texture> deathAnimation;

    public Bullet(float originX, float originY, float targetX, float targetY, int damage, boolean isEnemyBullet, float time, float addition) {
        Game game = App.getInstance().getCurrentUser().getCurrentGame();
        this.x = game.getPlayer().getX() - originX;
        this.y = game.getPlayer().getY() - originY;
        this.damage = damage;
        if (!isEnemyBullet) {
            this.direction = -MathUtils.atan2(targetY - originY, targetX - originX) + addition;
            this.speed = 15f;
        } else {
            this.direction = -MathUtils.atan2(-targetY + originY, targetX - originX) + addition;
            this.speed = 2.5f;
        }
        this.isEnemyBullet = isEnemyBullet;
        this.shootingTime = time;
        this.isDead = false;
        this.deathTime = Float.MAX_VALUE;
        this.deathDuration = 0.30003f;

        AppAssetManager assetManager = AppAssetManager.getInstance();
        if(!isEnemyBullet) {
            texture = assetManager.getBulletTexture();
            sprite = new Sprite(texture);
            sprite.setSize(texture.getWidth(), texture.getHeight());
        }
        else {
            texture = assetManager.getEnemyBulletTexture();
            sprite = new Sprite(texture);
            sprite.setSize(texture.getWidth() * 1.4f, texture.getHeight() * 1.4f);
        }
        deathAnimation = assetManager.getBulletDeathAnimation();

        sprite.setPosition(originX, originY);
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void load() {
        AppAssetManager assetManager = AppAssetManager.getInstance();
        if(!isEnemyBullet) {
            texture = assetManager.getBulletTexture();
            sprite = new Sprite(texture);
            sprite.setSize(texture.getWidth(), texture.getHeight());
        }
        else {
            texture = assetManager.getEnemyBulletTexture();
            sprite = new Sprite(texture);
            sprite.setSize(texture.getWidth() * 1.4f, texture.getHeight() * 1.4f);
        }
        deathAnimation = assetManager.getBulletDeathAnimation();
    }

    public void update() {
        if (!isDead) {
            x -= MathUtils.cos(direction) * speed;
            y -= MathUtils.sin(direction) * speed;
        }
    }

    public void kill(float time) {
        isDead = true;
        deathTime = time;
    }

    public int getDamage() {
        return damage;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDirection() {
        return direction;
    }

    public float getSpeed() {
//        TODO: implement speed transition here;
        return speed;
    }

    public boolean isEnemyBullet() {
        return isEnemyBullet;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getShootingTime() {
        return shootingTime;
    }

    public boolean isDead() {
        return isDead;
    }

    public float getDeathTime() {
        return deathTime;
    }

    public float getDeathDuration() {
        return deathDuration;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Animation<Texture> getDeathAnimation() {
        return deathAnimation;
    }
}
