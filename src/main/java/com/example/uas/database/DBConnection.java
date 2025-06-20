package com.example.uas.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://mysql-exploresumut-exploresumut.c.aivencloud.com:12954/exploresumut" +
                                        "?useSSL=true" +
                                        "&requireSSL=true" +
                                        "&verifyServerCertificate=true" +
                                        "&trustCertificateKeyStoreUrl=file:D:\\keystore.jks" +
                                        "&trustCertificateKeyStorePassword=qwerty";
    private static final String USER = "avnadmin";
    private static final String PASS = "AVNS_LXE4QR_L36QEuXPf9J_";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
