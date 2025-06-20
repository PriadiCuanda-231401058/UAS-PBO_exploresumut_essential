module com.example.uas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

//    requires mysql.connector.java;

    opens com.example.uas.controller to javafx.fxml;
    opens com.example.uas.model to javafx.fxml;
    opens com.example.uas to javafx.fxml;
    exports com.example.uas;
}