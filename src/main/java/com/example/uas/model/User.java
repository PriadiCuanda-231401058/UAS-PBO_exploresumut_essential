package com.example.uas.model;

public class User {
    private static String loggedInUsername;


    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    public static void setLoggedInUsername(String loggedInUsername) {
        User.loggedInUsername = loggedInUsername;
    }
}
