package com.untillDawn.Model.GameModels.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.untillDawn.Model.AppAssetManager;

public enum EnemyType {
    tree(Integer.MAX_VALUE, 0f),
    brainMonster(25, 2.2f),
    eyeBat(50, 1.5f),
    elder(400, 1);

    EnemyType(int hp, float speed) {
        this.hp = hp;
        this.speed = speed;

        AppAssetManager assetManager = AppAssetManager.getInstance();
        texture = assetManager.getEnemyTexture(this.name());
        animation = assetManager.getEnemyAnimation(this.name());
        animation.setPlayMode(Animation.PlayMode.LOOP);
        deathAnimation = assetManager.getEnemyDeathAnimation();
    }

    private int hp;
    private float speed;
    private Texture texture;
    private Animation<Texture> animation;
    private Animation<Texture> deathAnimation;

    public int getHp() {
        return hp;
    }

    public float getSpeed() {
        return speed;
    }

    public Texture getTexture() {
        return texture;
    }

    public Animation<Texture> getAnimation() {
        return animation;
    }

    public Animation<Texture> getDeathAnimation() {
        return deathAnimation;
    }
}
