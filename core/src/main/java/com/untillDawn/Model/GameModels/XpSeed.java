package com.untillDawn.Model.GameModels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Model.AppAssetManager;

public class XpSeed {
    private float x, y;
    private int amount;
    private Sprite sprite;
    private CollisionRect rect;

    public XpSeed(float x, float y, int amount) {
        this.x = x;
        this.y = y;
        this.amount = amount;
        Texture texture = AppAssetManager.getInstance().getXp();
        this.sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth() * 0.7f, texture.getHeight() * 0.7f);
        this.rect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getAmount() {
        return amount;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public CollisionRect getRect() {
        return rect;
    }
}
