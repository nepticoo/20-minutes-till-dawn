package com.untillDawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untillDawn.Control.ChangeAvatarMenuController;
import com.untillDawn.Main;
import com.untillDawn.Model.App;
import com.untillDawn.Model.AppAssetManager;
import com.untillDawn.Model.Enums.AllColors;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class ChangeAvatarMenuView implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final Table table;
    private final ChangeAvatarMenuController controller;
    private final AppAssetManager assetManager;

    private Image selectedAvatarPreview;
    private Texture selectedTexture;
    private boolean isCustomTexture = false;

    public ChangeAvatarMenuView() {
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.assetManager = AppAssetManager.getInstance();
        this.skin = assetManager.getSkin();
        this.controller = ChangeAvatarMenuController.getInstance();

        this.table = new Table(skin);
        table.setFillParent(true);
        table.center();

        createUI();

        // Drag and drop handled by Lwjgl3Launcher + Main.handleDroppedFile()
        // so no need to set input processor here other than stage

        stage.addActor(table);
        controller.setView(this);
    }

    private void createUI() {
        table.clear();

        Label title = new Label("Choose Your Avatar", skin);
        table.add(title).colspan(2).pad(10);
        table.row();

        Table avatarGrid = new Table();
        ArrayList<Texture> avatars = assetManager.getAllAvatars();
        for (int i = 0; i < avatars.size(); i++) {
            final Texture avatar = avatars.get(i);
            Image img = new Image(avatar);
            img.setScaling(Scaling.fit);
            img.setSize(100, 100);
            img.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (isCustomTexture && selectedTexture != null) {
                        selectedTexture.dispose();
                    }
                    selectedTexture = avatar;
                    isCustomTexture = false;
                    updateSelectedPreview();
                }
            });
            avatarGrid.add(img).size(150, 150).pad(10);
            if ((i + 1) % 2 == 0) avatarGrid.row();
        }
        table.add(avatarGrid).colspan(2);
        table.row().pad(10);

        selectedAvatarPreview = new Image();
        selectedAvatarPreview.setSize(80, 80);
        selectedAvatarPreview.setScaling(Scaling.fit);
        table.add(new Label("Selected Avatar:", skin)).pad(5);
        table.add(selectedAvatarPreview).size(180, 180).pad(5);

        table.row().pad(10);

        TextButton uploadBtn = new TextButton("Upload Custom Image", skin);
        uploadBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String path = selectImageFileDialog();
                if (path != null) {
                    FileHandle file = Gdx.files.absolute(path);
                    if (file.exists()) {
                        if (isCustomTexture && selectedTexture != null) selectedTexture.dispose();
                        selectedTexture = new Texture(file);
                        updateSelectedPreview();
                    }
                }
            }
        });

        table.add(uploadBtn).colspan(2).pad(5);
        table.row();

        TextButton applyBtn = new TextButton("Apply", skin);
        applyBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedTexture != null) {
                    controller.setAvatar(selectedTexture);
                    controller.back();
                }
            }
        });

        TextButton backBtn = new TextButton("Back", skin);
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.back();
            }
        });

        Table btnTable = new Table();
        btnTable.add(applyBtn).padRight(20);
        btnTable.add(backBtn);
        table.add(btnTable).colspan(2).padTop(10);
    }

    private void updateSelectedPreview() {
        if (selectedTexture != null) {
            selectedAvatarPreview.setDrawable(new TextureRegionDrawable(new TextureRegion(selectedTexture)));
        }
    }

    private String selectImageFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    // This method will be called from Main.handleDroppedFile on drag-drop
    public void onImageDropped(FileHandle file) {
        if (file != null && file.exists()) {
            if (isCustomTexture && selectedTexture != null) {
                selectedTexture.dispose();
            }
            selectedTexture = new Texture(file);
            isCustomTexture = true;
            updateSelectedPreview();
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(AllColors.backGround.color);
        if (App.getInstance().getSettings().hasGrayScale()) {
            stage.getBatch().setShader(Main.getGrayScaleShader());
        } else {
            stage.getBatch().setShader(null);
        }
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
