package com.example.uas.controller;

//package controller;

import com.example.uas.model.User;
import com.example.uas.model.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import com.example.uas.controller.DetailController;
import com.example.uas.database.DBConnection;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    Function helper = new Function();
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection conn = DBConnection.connect();) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password); // Belum hash
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Berhasil login
                int userId = rs.getInt("id");
                String role = rs.getString("role");
                User.setLoggedInUsername(username);
                UserSession.createSession(username, userId, role);


                helper.moveTo(usernameField, "/com/example/uas/view/dashboard.fxml");

            } else {
                showAlert("Login gagal", "Username atau password salah");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error",e.getMessage());
        }
    }

    @FXML
    private void goToRegister() {
        try {
            helper.moveTo(usernameField, "/com/example/uas/view/register.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }

}

