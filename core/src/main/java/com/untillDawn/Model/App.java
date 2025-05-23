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

        User user;
        user = new User("ali", "a", 0, "");
        user.addScore(200);
        user.addKills(100);
        users.add(user);

        user = new User("ali", "", 0, "");
        user.addScore(300);
        user.addKills(1500);
        users.add(user);

        user = new User("ali", "", 0, "");
        user.addScore(50);
        user.addKills(2000);
        users.add(user);

        user = new User("mahsa", "a", 0, "");
        user.addScore(40000);
        user.addKills(26000);
        users.add(user);

        user = new User("ali", "", 0, "");
        user.addScore(478);
        user.addKills(23);
        users.add(user);

        user = new User("ali", "", 0, "");
        user.addScore(444);
        user.addKills(101210);
        users.add(user);
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
}
