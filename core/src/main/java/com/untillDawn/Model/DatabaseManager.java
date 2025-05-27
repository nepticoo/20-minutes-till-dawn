package com.untillDawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "username TEXT PRIMARY KEY," +
                "password TEXT," +
                "questionId INTEGER," +
                "answer TEXT," +
                "isGuest INTEGER," +
                "score INTEGER," +
                "kills INTEGER," +
                "maxAliveTime REAL" +
                ");";
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers(ArrayList<User> users) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT OR REPLACE INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (User user : users) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setInt(3, user.getQuestionId());
                ps.setString(4, user.getAnswer());
                ps.setInt(5, user.isGuest() ? 1 : 0);
                ps.setInt(6, user.getScore());
                ps.setInt(7, user.getKills());
                ps.setFloat(8, user.getMaxAliveTime());

                ps.addBatch();
            }

            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("questionId"),
                    rs.getString("answer")
                );
                user.addScore(rs.getInt("score"));
                user.addKills(rs.getInt("kills"));
                user.setMaxAliveTime(rs.getFloat("maxAliveTime"));
                user.setAvatar(new Texture(Gdx.files.local("avatars/" + user.getUsername() + ".png")));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
