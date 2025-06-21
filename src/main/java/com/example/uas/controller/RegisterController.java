package com.example.uas.controller;

//package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import com.example.uas.database.DBConnection;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class RegisterController {
    Function helper = new Function();
    @FXML private TextField firstnameField;
    @FXML private TextField lastnameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField CpasswordField;

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&     // ada huruf besar
                password.matches(".*[a-z].*") &&     // ada huruf kecil
                password.matches(".*\\d.*") &&       // ada angka
                password.matches(".*[^a-zA-Z0-9].*"); // ada simbol
    }

    @FXML
    private void handleRegister() {
        String firstname = firstnameField.getText();
        String lastname = lastnameField.getText();
        String username = firstname + lastname;
        String password = passwordField.getText();
        String Cpassword = CpasswordField.getText();

        if (!isValidPassword(password)) {
            showAlert("Gagal", "Password harus minimal 8 karakter dan mengandung huruf besar, huruf kecil, angka, dan simbol.");
            return;
        }
        if(!password.equals(Cpassword)){
            showAlert("Gagal", "Konfirmasi Password salah.");
            return;
        }

        try (Connection conn = DBConnection.connect();) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            String query = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.executeUpdate();

            showAlert("Sukses", "Registrasi berhasil!");
            helper.moveTo(firstnameField, "/com/example/uas/view/login.fxml");

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
            helper.moveTo(firstnameField, "/com/example/uas/view/login.fxml");
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

