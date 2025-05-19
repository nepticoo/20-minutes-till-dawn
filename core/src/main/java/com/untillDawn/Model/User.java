package com.untillDawn.Model;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class User {
    private String username;
    private String password;
    private int questionId;
    private String answer;
    private boolean isGuest;
    private int score;
    private int kills;
    private int avatarId;
    private Game currentGame;

    public User(String username, String password, int questionId, String answer) {
        this.username = username;
        this.password = password;
        this.questionId = questionId;
        this.answer = answer;
        this.score = 0;
        this.kills = 0;
        Random rand = new Random();
        this.avatarId = rand.nextInt(AppAssetManager.getInstance().getAllAvatars().size());
        currentGame = null;
    }

    public User() {
        username = "Guest";
        password = "";
        questionId = 0;
        answer = "";
        isGuest = true;
        score = 0;
        kills = 0;
        Random rand = new Random();
        this.avatarId = rand.nextInt(AppAssetManager.getInstance().getAllAvatars().size());
        currentGame = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public int getKills() {
        return kills;
    }

    public void addKills(int increment) {
        kills += increment;
    }

    public Texture getAvatar() {
        return AppAssetManager.getInstance().getAllAvatars().get(avatarId);
    }
}
