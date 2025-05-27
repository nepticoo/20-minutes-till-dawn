package com.untillDawn.Model.GameModels.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Model.AppAssetManager;

public enum WeaponType {
    revolver(20, 1, 0.3f, 1f, 6),
    shotgun(10, 4, 0.5f, 1f, 2),
    smg(8, 1, 0.07f, 2f, 24);

    WeaponType(int damage, int projectile, float shootingDuration, float reloadDuration, int maxAmmo) {
        this.damage = damage;
        this.projectile = projectile;
        this.shootingDuration = shootingDuration;
        this.reloadDuration = reloadDuration;
        this.maxAmmo = maxAmmo;

        AppAssetManager assetManager = AppAssetManager.getInstance();

        texture = assetManager.getWeaponTexture(this.name());
        sprite = new Sprite(texture);
        reloadAnimation = assetManager.getReloadAnimation(this.name());
    }

    private int damage;
    private int projectile;
    private float shootingDuration;
    private float reloadDuration;
    private int maxAmmo;
    private Texture texture;
    private Sprite sprite;
    private Animation<Texture> reloadAnimation;

    public static WeaponType getWeaponType(String name) {
        for (WeaponType weaponType : WeaponType.values()) {
            if(weaponType.name().equals(name)) {
                return weaponType;
            }
        }
        return null;
    }

    public int getDamage() {
        return damage;
    }

    public int getProjectile() {
        return projectile;
    }

    public float getShootingDuration() {
        return shootingDuration;
    }

    public float getReloadDuration() {
        return reloadDuration;
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
