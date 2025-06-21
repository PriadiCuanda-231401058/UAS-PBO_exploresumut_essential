package com.example.uas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/uas/view/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ExploreSumut");
        stage.setScene(scene);
        // Cara 1: Fullscreen (tanpa border, taskbar hilang)
//        stage.setFullScreen(true);
        stage.setMaximized(true);

        // Cara 2: Maximized (seperti mode biasa tapi layar penuh)
//        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//        stage.setX(0);
//        stage.setY(0);
//        stage.setWidth(screenBounds.getWidth());
//        stage.setHeight(screenBounds.getHeight());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

//public class MainApp {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://mysql-exploresumut-exploresumut.c.aivencloud.com:12954/exploresumut" +
//                "?useSSL=true" +
//                "&requireSSL=true" +
//                "&verifyServerCertificate=true" +
//                "&trustCertificateKeyStoreUrl=file:D:\\keystore.jks" +
//                "&trustCertificateKeyStorePassword=qwerty"; // ganti dengan nama database kamu
//        String user = "avnadmin"; // ganti jika bukan root
//        String password = "AVNS_LXE4QR_L36QEuXPf9J_"; // isi jika ada password MySQL-mu
//
//        try {
//            // Load driver MySQL (kadang bisa dilewati tergantung JDK)
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            // Coba koneksi
//            Connection conn = DriverManager.getConnection(url, user, password);
//            System.out.println("✅ Koneksi ke database BERHASIL!");
//            conn.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("❌ Driver tidak ditemukan: " + e.getMessage());
//        } catch (SQLException e) {
//            System.out.println("❌ Gagal koneksi: " + e.getMessage());
//        }
//    }
//}




