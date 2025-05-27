package com.untillDawn.Model.GameModels.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Model.AppAssetManager;

public enum HeroType {
    shana(4, 4),
    diamond(7, 1),
    scarlett(3, 5),
    lilith(5, 3),
    dasher(2, 10);

    HeroType(int hp, int speed) {
        this.hp = hp;
        this.speed = speed;

        AppAssetManager assetManager = AppAssetManager.getInstance();

        texture = assetManager.getHeroTexture(this.name());
        sprite = new Sprite(texture);
        idleAnimation = assetManager.getIdleAnimation(this.name());
        runAnimation = assetManager.getRunAnimation(this.name());
    }

    private int hp, speed;
    private Texture texture;
    private Sprite sprite;
    private Animation<Texture> idleAnimation;
    private Animation<Texture> runAnimation;

    public static HeroType getHeroType(String name) {
        for(HeroType heroType : HeroType.values()) {
            if(heroType.name().equals(name)) {
                return heroType;
            }
        }
        return null;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
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
}
