package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.GameModels.Enums.EnemyType;

import java.io.Serializable;

public class Enemy implements Serializable {
    private String name;
    private int hp;
    private float x, y;
    private float direction;
    private float speed;
    private CollisionRect rect;
    private boolean isDead;
    private float deathTime;
    private float deathDuration;
    private float lastShot;
    private boolean isDashing;
    private float dashingTime;
    private transient Texture texture;
    private transient Sprite sprite;
    private transient Animation<Texture> animation;
    private transient Animation<Texture> deathAnimation;

    public Enemy(float originX, float originY, EnemyType type) {
        this.name = type.name();
        Game game = App.getInstance().getCurrentUser().getCurrentGame();
        this.x = game.getPlayer().getX() - originX;
        this.y = game.getPlayer().getY() - originY;
        this.direction = 0;
        hp = type.getHp();
        speed = type.getSpeed();
        this.isDead = false;
        this.deathTime = Float.MAX_VALUE;
        this.deathDuration = 0.50003f;
        this.isDashing = false;
        this.dashingTime = -10f;
        this.lastShot = 0;

        texture = type.getTexture();
        sprite = new Sprite(texture);
        float ratio = 1;
        if (name.equals("tree")) {
            ratio = 1.5f;
        }
        if (name.equals("brainMonster")) {
            ratio = 2f;
        }
        if (name.equals("elder")) {
            ratio = 1.3f;
        }
        sprite.setSize(texture.getWidth() * ratio, texture.getHeight() * ratio);
        animation = type.getAnimation();
        deathAnimation = type.getDeathAnimation();

        sprite.setPosition(originX, originY);
        rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void load() {
        AppAssetManager assetManager = AppAssetManager.getInstance();
        texture = assetManager.getEnemyTexture(name);
        sprite = new Sprite(texture);
        float ratio = 1;
        if (name.equals("tree")) {
            ratio = 1.5f;
        }
        if (name.equals("brainMonster")) {
            ratio = 2f;
        }
        if (name.equals("elder")) {
            ratio = 1.3f;
        }
        sprite.setSize(texture.getWidth() * ratio, texture.getHeight() * ratio);
        animation = assetManager.getEnemyAnimation(name);
        deathAnimation = assetManager.getEnemyDeathAnimation();
    }

    public void update(Sprite target) {
        if (!isDead) {
            float ratio = 1f;
            if (!isDashing) {
                this.direction = -MathUtils.atan2(-target.getY() + sprite.getY(), target.getX() - sprite.getX());
            } else {
                ratio = 10f;
            }
            x -= MathUtils.cos(direction) * speed * ratio;
            y -= MathUtils.sin(direction) * speed * ratio;
        }
    }

    public void knockBack() {
        if(speed < 0.1f) {
            return;
        }
        float ratio = 20f;
        x += MathUtils.cos(direction) * ratio;
        y += MathUtils.sin(direction) * ratio;

    }

    public void kill(float time) {
        isDead = true;
        deathTime = time;
        if(App.getInstance().getSettings().hasSFX()){
            AppAssetManager.getInstance().getSFX("enemyDeath").play();
        }
    }

    public Bullet shoot(int x, int y, float time) {
        if (!isDead && time > lastShot + 3f) {
            lastShot = time;
            return new Bullet(sprite.getX(), sprite.getY(), x, y, 1, true, time, 0f);
        }
        return null;
    }

    public void decreaseHp(int hp, float time) {
        this.hp -= hp;
        if (this.hp <= 0) {
            kill(time);
        }
    }

    public void startDash(float time) {
        isDashing = true;
        dashingTime = time;
    }

    public void endDash(float time) {
        isDashing = false;
        dashingTime = time;

    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
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
        return speed;
    }

    public CollisionRect getRect() {
        return rect;
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

    public boolean isDashing() {
        return isDashing;
    }

    public float getDashingTime() {
        return dashingTime;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public Animation<Texture> getDeathAnimation() {
        return deathAnimation;
    }
}
