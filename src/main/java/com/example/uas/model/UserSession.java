package com.example.uas.model;

public class UserSession {
    private static UserSession instance;

    private String username;
    private int userId;
    private String role;

    private UserSession(String username, int userId, String role) {
        this.username = username;
        this.userId = userId;
        this.role = role;
    }

    public static void createSession(String username, int userId, String role) {
        if (instance == null) {
            instance = new UserSession(username, userId, role);
        }
    }
    public UserSession(){

    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void destroySession() {
        instance = null;
    }

    // Getter
    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
}

