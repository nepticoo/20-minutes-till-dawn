package com.untillDawn.Control.CameControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.Enums.Keybinding;
import com.untillDawn.Model.GameModels.Bullet;
import com.untillDawn.Model.GameModels.Enemy;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.Model.GameModels.Player;
import com.untillDawn.Model.Settings;
import com.untillDawn.View.GameView;

public class PlayerController {
    private Game game;
    private GameView view;
    private Player player;

    public PlayerController(Game game, GameView view) {
        this.game = game;
        this.view = view;
        player = game.getPlayer();
    }

    public void update() {

        boolean[] isKeyDown = GameController.getInstance().getIsKeyDown();
        float dx = 0, dy = 0;
        boolean f = false;
        if(isKeyDown[0]) {
            dy -= 1;
        }
        if(isKeyDown[1]) {
            dy += 1;
        }
        if (isKeyDown[2]) {
            dx += 1;
        }
        if (isKeyDown[3]) {
            dx -= 1;
        }
        for(boolean b : isKeyDown) {
            f |= b;
        }
        player.setRunning(f);

        player.move(dx, dy);

        if(player.isRunning()) {
            runAnimation();
        }
        else {
            idleAnimation();
        }
        if(player.isInvincible()) {
            float elapsedTime = game.getTime() - player.getInvincibleTime();
            elapsedTime *= 10f;
            if((int) elapsedTime % 2 == 0) {
                player.getSprite().setColor(1f, 1f, 1f, 0.3f);
            }
            else {
                player.getSprite().setColor(1f, 1f, 1f, 1f);
            }
        }

        if(player.isInvincible() && game.getTime() > player.getInvincibleTime() + 1f) {
            player.getSprite().setColor(1f, 1f, 1f, 1f);
            player.setInvincible(false);
        }

        if(!player.isInvincible()) {
            for(Enemy enemy : game.getEnemies()) {
                if(player.getRect().intersects(enemy.getRect()) && !enemy.isDead()) {
                    player.takeDamage(game.getTime());
                    break;
                }
            }
        }
        for(Bullet bullet : game.getBullets()) {
            if(!bullet.isDead() && bullet.isEnemyBullet()) {
                if(player.getRect().intersects(bullet.getRect())) {
                    bullet.kill(game.getTime());
                    if(!player.isInvincible()) {
                        player.takeDamage(game.getTime());
                    }
                }
            }
        }
        player.getSprite().draw(Main.getBatch());

    }

    void runAnimation() {
        Animation<Texture> animation = player.getRunAnimation();
        player.getSprite().setRegion(animation.getKeyFrame(game.getTime()));
        if(!player.isSideLeft()) {
            player.getSprite().flip(true, false);
        }
    }

    void idleAnimation() {
        Animation<Texture> animation = player.getIdleAnimation();
        player.getSprite().setRegion(animation.getKeyFrame(game.getTime()));
        if(!player.isSideLeft()) {
            player.getSprite().flip(true, false);
        }
    }
}
