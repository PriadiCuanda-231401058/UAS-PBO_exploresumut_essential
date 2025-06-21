package com.example.uas.controller;

//package controller;

import com.example.uas.model.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import com.example.uas.database.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

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
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                if (BCrypt.checkpw(password, storedHash)) {
                    int userId = rs.getInt("id");
                    String role = rs.getString("role");
                    UserSession.createSession(username, userId, role);
                    helper.moveTo(usernameField, "/com/example/uas/hello-view.fxml");
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/uas/view/dashboard.fxml"));
//                Scene scene = new Scene(loader.load());
//                Stage stage = (Stage) usernameField.getScene().getWindow();
//                stage.setScene(scene);
                }else showAlert("Login gagal", "Password salah");
            }else {
                showAlert("Login gagal", "Username tidak ditemukan");
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

