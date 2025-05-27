package com.untillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.untillDawn.Model.Enums.Language;
import com.untillDawn.Model.GameModels.Game;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class App {
    private static App instance;

    private App() {
        DatabaseManager.initializeDatabase();

        this.language = Language.english;
        this.users = new ArrayList<>();
        this.settings = new Settings();


        loadUsersFromJson();
//        loadFromSql();
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    private Language language;
    private ArrayList<User> users;
    private User currentUser;
    private Settings settings;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void deleteCurrentUser() {
        if(currentUser != null && users.contains(currentUser)) {
            users.remove(currentUser);
        }
        this.currentUser = null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Settings getSettings() {
        return settings;
    }

    public void saveAvatarTexture(Texture texture, String username) {
        int width = texture.getWidth();
        int height = texture.getHeight();

        FrameBuffer fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        fbo.begin();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        SpriteBatch batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texture, 0, 0, width, height);
        batch.end();

        Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(0, 0, width, height);

        fbo.end();
        fbo.dispose();
        batch.dispose();

        ByteBuffer pixels = pixmap.getPixels();
        ByteBuffer flipped = ByteBuffer.allocate(pixels.capacity());
        int bytesPerPixel = 4;
        for (int y = 0; y < height; y++) {
            int offset = (height - y - 1) * width * bytesPerPixel;
            for (int x = 0; x < width * bytesPerPixel; x++) {
                flipped.put(pixels.get(offset + x));
            }
        }
        flipped.rewind();
        Pixmap flippedPixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        flippedPixmap.getPixels().put(flipped);

        FileHandle file = Gdx.files.local("avatars/" + username + ".png");
        PixmapIO.writePNG(file, flippedPixmap);

        pixmap.dispose();
        flippedPixmap.dispose();
    }

    public void saveUsersAvatar() {
        for(User user : users) {
            saveAvatarTexture(user.getAvatar(), user.getUsername());
        }
    }

    public void loadUsersAvatar() {
        for (User user : users) {
            user.setAvatar(new Texture(Gdx.files.internal("avatars/" + user.getUsername() + ".png")));
        }
    }

    public void saveUsersGame() {
        for(User user : users) {
            if(user.getCurrentGame() != null) {
                Game.saveGame(user.getUsername(), user.getCurrentGame());
            }
        }
    }

    public void loadUsersGame() {
        for(User user : users) {
            Game game = Game.loadGame(user.getUsername());
            if(game != null) {
                user.setCurrentGame(game);
            }
        }
    }

    public void saveUsersToJson() {
        saveUsersAvatar();
        saveUsersGame();
        Json json = new Json();
        FileHandle file = Gdx.files.local("users.json");
        file.writeString(json.prettyPrint(users), false);
    }

    public void loadUsersFromJson() {
        FileHandle file = Gdx.files.local("users.json");
        if (file.exists()) {
            Json json = new Json();
            users = json.fromJson(ArrayList.class, User.class, file);
            loadUsersAvatar();
            loadUsersGame();
        }
    }

    public void saveToSql() {
        saveUsersAvatar();
        saveUsersGame();
        DatabaseManager.saveUsers(users);
    }

    public void loadFromSql() {
        users = DatabaseManager.loadUsers();
        loadUsersGame();
    }


}
