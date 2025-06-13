package com.example.uas.controller;

//package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.uas.database.DBConnection;

import java.sql.*;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try (Connection conn = DBConnection.connect();) {
            String query = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password); // Belum hash
            stmt.executeUpdate();

            showAlert("Sukses", "Registrasi berhasil!");
            goToLogin();

        } catch (SQLIntegrityConstraintViolationException e) {
            showAlert("Gagal", "Username sudah digunakan.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Terjadi kesalahan.");
        }
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }
}

