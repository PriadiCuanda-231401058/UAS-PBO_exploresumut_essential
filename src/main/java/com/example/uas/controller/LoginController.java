package com.example.uas.controller;

//package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import com.example.uas.database.DBConnection;

import java.sql.*;

public class LoginController {
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
            } else {
                showAlert("Login gagal", "Username atau password salah");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan koneksi");
        }
    }

    @FXML
    private void goToRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
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

