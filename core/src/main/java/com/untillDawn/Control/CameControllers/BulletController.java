package com.untillDawn.Control.CameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untillDawn.Main;
import com.untillDawn.Model.GameModels.Bullet;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.View.GameView;

import java.util.ArrayList;
import java.util.Iterator;

public class BulletController {
    private Game game;
    private GameView view;
    public BulletController(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    public void update() {
        Iterator<Bullet> bulletIterator = game.getBullets().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            bullet.update();
            Sprite sprite = bullet.getSprite();
            sprite.setPosition(-bullet.getX() + game.getMapX(), -bullet.getY() + game.getMapY());
            bullet.getRect().move(sprite.getX(), sprite.getY());
            sprite.draw(Main.getBatch());
            if(!bullet.isDead()) {
                sprite.setRegion(bullet.getTexture());
            }
            else {
                sprite.setRegion(bullet.getDeathAnimation().getKeyFrame(game.getTime() - bullet.getDeathTime()));
            }
            if(game.getTime() > bullet.getShootingTime() + (bullet.isEnemyBullet() ? 10 : 5) && !bullet.isDead()) {
                bullet.kill(game.getTime());
            }
            if(bullet.isDead() && game.getTime() > bullet.getDeathTime() + bullet.getDeathDuration()) {
                bulletIterator.remove();
            }
        }
    }

}
