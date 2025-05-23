package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Model.GameModels.Enums.WeaponType;

import java.util.ArrayList;

public class Weapon {
    private int damage;
    private int projectiles;
    private float shootingDuration;
    private float lastShot;
    private float reloadDuration;
    private float lastReload;
    private int ammo;
    private int maxAmmo;
    private Texture texture;
    private Sprite sprite;
    private Animation<Texture> reloadAnimation;


    public Weapon(WeaponType type) {
        damage = type.getDamage();
        projectiles = type.getProjectile();
        shootingDuration = type.getShootingDuration();
        lastShot = -10f;
        reloadDuration = type.getReloadDuration();
        lastReload = -10f;
        maxAmmo = type.getMaxAmmo();
        ammo = maxAmmo;
        texture = type.getTexture();
        sprite = new Sprite(texture);
        reloadAnimation = type.getReloadAnimation();
        reloadAnimation.setPlayMode(Animation.PlayMode.LOOP);

        sprite.setSize((float) (texture.getWidth() * 2.5), (float) (texture.getHeight() * 2.5));
        sprite.setOrigin(0, sprite.getHeight() / 2);
        sprite.setPosition(
            (float) Gdx.graphics.getWidth() / 2f,
            (float) Gdx.graphics.getHeight() / 2f - sprite.getHeight() / 2f);

    }

    public boolean canShoot(float time) {
        if(time < lastShot + shootingDuration) {
            return false;
        }
        if(time < lastReload + reloadDuration) {
            return false;
        }
        if(ammo == 0) {
            return false;
        }
        return true;
    }

    public ArrayList<Bullet> shoot(int x, int y, float time) {
        ammo--;
        ArrayList<Bullet> ret = new ArrayList<>();
        for(int i = 0; i < projectiles; i++) {
            float addition = 0.1f * i  - ((projectiles - 1) * 0.1f)/2;
            ret.add(new Bullet(sprite.getX(), sprite.getY(), x, y, damage, false, time, addition));
        }
        return ret;
    }

    public void reload(float time) {
        lastReload = time;
        ammo = maxAmmo;
    }

    public boolean isReloading(float time) {
        return time < lastReload + reloadDuration;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectiles() {
        return projectiles;
    }


    public float getShootingDuration() {
        return shootingDuration;
    }

    public float getLastShot() {
        return lastShot;
    }

    public float getReloadDuration() {
        return reloadDuration;
    }

    public float getLastReload() {
        return lastReload;
    }

    public int getAmmo() {
        return ammo;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Animation<Texture> getReloadAnimation() {
        return reloadAnimation;
    }
}
