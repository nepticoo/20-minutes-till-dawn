package com.untillDawn.Model;

import com.untillDawn.Model.Enums.Language;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class App {
    private static App instance;

    private App() {
        this.language = Language.english;
        this.users = new ArrayList<>();
        this.settings = new Settings();
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Settings getSettings() {
        return settings;
    }
}
