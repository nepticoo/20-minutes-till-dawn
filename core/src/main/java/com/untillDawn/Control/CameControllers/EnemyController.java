package com.untillDawn.Control.CameControllers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.GameModels.Bullet;
import com.untillDawn.Model.GameModels.Enemy;
import com.untillDawn.Model.GameModels.Enums.EnemyType;
import com.untillDawn.Model.GameModels.Game;
import com.untillDawn.Model.GameModels.XpSeed;
import com.untillDawn.View.GameView;

import java.util.Iterator;

public class EnemyController {
    private Game game;
    private GameView view;

    private float lastBrainMonsterSpawn = -10f;
    private float lastEyeBatSpawn = -10f;
    private boolean isBossSpawned = false;
    private float spawnX, spawnY;

    public EnemyController(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    private void setRandomSpawnPoint() {
        do {
            spawnX = MathUtils.random(-1500, 3500);
            spawnY = MathUtils.random(-1000, 2000);
        } while (spawnX > -50 && spawnY > -50 && spawnX < 2000 && spawnY < 1100);
    }

    public void spawnTree() {
        Sprite sprite = game.getPlayer().getSprite();
        for (int i = 0; i < 40; i++) {
            float x, y;
            do {
                x = MathUtils.random(50, 6350);
                y = MathUtils.random(50, 6350);
            } while (x > sprite.getX() - 50 && y > sprite.getY() - 50 && x < sprite.getX() + 50 && y < sprite.getY() + 50);
            Enemy tree = new Enemy(x, y, EnemyType.tree);
            game.getEnemies().add(tree);
        }

    }

    public void spawnBrainMonster() {
        if (game.getTime() < lastBrainMonsterSpawn + 3f) {
            return;
        }
        lastBrainMonsterSpawn = game.getTime();
        int count = ((int) game.getTime() / 60) / 30 + 1;
        for (int i = 0; i < count; i++) {
            setRandomSpawnPoint();
            Enemy brainMonster = new Enemy(spawnX, spawnY, EnemyType.brainMonster);
            game.getEnemies().add(brainMonster);
        }
    }

    public void spawnEyeBat() {
        if (game.getTime() < game.getWholeTime() / 4f) {
            return;
        }
        if (game.getTime() < lastEyeBatSpawn + 10f) {
            return;
        }
        lastEyeBatSpawn = game.getTime();
        int count = (4 * ((int) game.getTime() / 60) - ((int) game.getWholeTime() / 60) + 30) / 30;
        for (int i = 0; i < count; i++) {
            setRandomSpawnPoint();
            Enemy eyeBat = new Enemy(spawnX, spawnY, EnemyType.eyeBat);
            game.getEnemies().add(eyeBat);
        }
    }

    public void spawnBoss() {
        if (game.getTime() < game.getWholeTime() / 2 || game.isBossSpawned()) {
            return;
        }
        game.setBossSpawned(true);
        setRandomSpawnPoint();
        Enemy elder = new Enemy(spawnX, spawnY, EnemyType.elder);
        game.getEnemies().add(elder);

    }

    public void update() {
        spawnBrainMonster();
        spawnEyeBat();
        spawnBoss();

        Iterator<Enemy> enemyIterator = game.getEnemies().iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update(game.getPlayer().getSprite());
            Sprite sprite = enemy.getSprite();
            sprite.setPosition(-enemy.getX() + game.getMapX(), -enemy.getY() + game.getMapY());
            enemy.getRect().move(sprite.getX(), sprite.getY());
            sprite.draw(Main.getBatch());
            for (Bullet bullet : game.getBullets()) {
                if (enemy.getRect().intersects(bullet.getRect())) {
                    if (bullet.isDead() || bullet.isEnemyBullet() || enemy.isDead()) {
                        continue;
                    }
                    bullet.kill(game.getTime());
                    enemy.decreaseHp(bullet.getDamage(), game.getTime());
                    enemy.knockBack();
                }
            }
            if (enemy.getName().equals("eyeBat")) {
                Sprite player = game.getPlayer().getSprite();
                Bullet bullet = enemy.shoot((int) (player.getX() + player.getWidth() / 2), (int) (player.getY() + player.getHeight() / 2), game.getTime());
                if (bullet != null) {
                    game.getBullets().add(bullet);
                }
            }
            if (enemy.getName().equals("elder")) {
                if (enemy.isDashing() && game.getTime() > enemy.getDashingTime() + 1f) {
                    enemy.endDash(game.getTime());
                }
                if (!enemy.isDashing() && game.getTime() > enemy.getDashingTime() + 3.5f) {
                    enemy.startDash(game.getTime());
                }
            }
            if (!enemy.isDead()) {
                float time = game.getTime();
                if (enemy.isDashing()) {
                    time *= 3f;
                }
                sprite.setRegion(enemy.getAnimation().getKeyFrame(time));
            } else {
                sprite.setRegion(enemy.getDeathAnimation().getKeyFrame(game.getTime() - enemy.getDeathTime()));
            }
            if (enemy.isDead() && game.getTime() > enemy.getDeathTime() + enemy.getDeathDuration()) {
                enemyIterator.remove();
                game.getPlayer().addKills();
                game.getXpSeeds().add(new XpSeed(enemy.getX() - sprite.getWidth() / 2, enemy.getY() - sprite.getHeight() / 2, (enemy.getName().equals("elder") ? 20 : 3)));

            }
        }

        Iterator<XpSeed> xpSeedIterator = game.getXpSeeds().iterator();
        while (xpSeedIterator.hasNext()) {
            XpSeed xpSeed = xpSeedIterator.next();
            Sprite sprite = xpSeed.getSprite();
            sprite.setPosition(-xpSeed.getX() + game.getMapX(), -xpSeed.getY() + game.getMapY());
            xpSeed.getRect().move(sprite.getX(), sprite.getY());
            sprite.draw(Main.getBatch());
            if (game.getPlayer().getRect().intersects(xpSeed.getRect())) {
                xpSeedIterator.remove();
                game.getPlayer().addXp(xpSeed.getAmount());
                if(App.getInstance().getSettings().hasSFX()){
                    AppAssetManager.getInstance().getSFX("xp").play();
                }
            }


        }
    }

    public Enemy getNearestEnemy() {
        Enemy ret = null;
        float minDis = Float.MAX_VALUE;
        for (Enemy enemy : game.getEnemies()) {
            if (enemy.getName().equals("tree")) {
                continue;
            }
            float dx = enemy.getSprite().getX() - game.getPlayer().getSprite().getX();
            float dy = enemy.getSprite().getY() - game.getPlayer().getSprite().getY();
            float dis = (float) Math.sqrt(dx * dx + dy * dy);
            if (dis < minDis) {
                minDis = dis;
                ret = enemy;
            }
        }
        return ret;
    }
}
