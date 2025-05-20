package com.untillDawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.untillDawn.Model.App;
import com.untillDawn.View.InitialMenuView;

public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private static Cursor customCursor;
    private static ShaderProgram grayScaleShader;

    public static Main getInstance() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static ShaderProgram getGrayScaleShader() {
        return grayScaleShader;
    }

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();

        setCustomCursor("Images/Sprite/T/T_Cursor.png", 0, 0);

        grayScaleShader = new ShaderProgram(Gdx.files.internal("shaders/default.vert"), Gdx.files.internal("shaders/grayscale.frag"));
        main.setScreen(new InitialMenuView());
    }

    @Override
    public void render() {
        if (App.getInstance().getSettings().hasGrayScale()) {
            batch.setShader(grayScaleShader);
        } else {
            batch.setShader(null);
        }
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        if (customCursor != null) customCursor.dispose();
        if (grayScaleShader != null) grayScaleShader.dispose();
    }

    public static void setCustomCursor(String filePath, int xHotspot, int yHotspot) {
        if (customCursor != null) customCursor.dispose();

        Pixmap pixmap = new Pixmap(Gdx.files.internal(filePath));
        customCursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        Gdx.graphics.setCursor(customCursor);
        pixmap.dispose();
    }

    public static void setSystemCursor(Cursor.SystemCursor systemCursor) {
        Gdx.graphics.setSystemCursor(systemCursor);
    }
}
