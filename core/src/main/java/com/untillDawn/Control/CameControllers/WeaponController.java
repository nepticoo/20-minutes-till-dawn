package com.untillDawn.Control.CameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.GameModels.Bullet;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.Model.GameModels.Weapon;
import com.untillDawn.View.GameView;

import java.util.ArrayList;

public class WeaponController {
    private Game game;
    private GameView view;
    private Weapon weapon;

    public WeaponController(Game game, GameView view) {
        this.game = game;
        this.view = view;
        this.weapon = game.getWeapon();
    }

    public void update() {
        if(weapon.isReloading(game.getTime())) {
            handleReloadAnimation();
        }
        else {
            weapon.getSprite().setRegion(weapon.getTexture());
        }
        weapon.getSprite().draw(Main.getInstance().getBatch());
    }

    public void handleWeaponRotation(int x, int y, boolean isFake) {
        if(game.isAutoAim() && !isFake) {
            return;
        }

        Sprite weaponSprite = weapon.getSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleShoot(int x, int y) {
        if(game.isAutoAim()) {
            x = (int) (game.getFakeCursor().getX() + game.getFakeCursor().getWidth()/2);
            y = (int) (Gdx.graphics.getHeight()-game.getFakeCursor().getY() - game.getFakeCursor().getHeight()/2);;
        }
        if(weapon.canShoot(game.getTime())) {
            ArrayList<Bullet> bullets =  weapon.shoot(x, y, game.getTime());
            for(Bullet bullet : bullets) {
                game.getBullets().add(bullet);
            }
            if(App.getInstance().getSettings().hasSFX()){
                AppAssetManager.getInstance().getSFX("shot").play();
            }
        }
        if(weapon.getAmmo() <= 0 && App.getInstance().getSettings().hasAutoReload()) {
            handleReload();
        }
    }

    public void handleReload() {
        if (weapon.getAmmo() == weapon.getMaxAmmo()) {
            return;
        }
        weapon.reload(game.getTime());
        if(App.getInstance().getSettings().hasSFX()){
            AppAssetManager.getInstance().getSFX("reload").play();
        }
    }

    public void handleReloadAnimation() {
        Animation<Texture> animation = weapon.getReloadAnimation();
        weapon.getSprite().setRegion(animation.getKeyFrame(game.getTime()));
    }
}
