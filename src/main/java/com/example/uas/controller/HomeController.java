package com.example.uas.controller;

import com.example.uas.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = DBConnection.connect();
        if (conn != null) {
            System.out.println("Koneksi berhasil");
        } else {
            System.out.println("Koneksi gagal");
        }
    }

    public void goToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/uas/view/login.fxml"));

            // Ambil stage dari tombol yang ditekan
            Stage stage;
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Ganti root scene dengan tampilan register
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goToRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/uas/view/register.fxml"));

            // Ambil stage dari tombol yang ditekan
            Stage stage;
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Ganti root scene dengan tampilan register
            stage.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
