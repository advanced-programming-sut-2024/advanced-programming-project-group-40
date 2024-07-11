module advanced.programming.project.group {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    requires javafx.base;
    requires java.logging;
    requires java.desktop;
    requires java.mail;
    requires org.mockito;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    exports views;
    exports enums.cards to com.google.gson;
    opens views to javafx.fxml;
    opens enums to com.google.gson, com.fasterxml.jackson.databind;
    opens controllers to com.google.gson, com.fasterxml.jackson.databind;
    exports views.ViewController;
    opens views.ViewController to javafx.fxml;
    opens Server.Models to com.google.gson;
    opens models to com.google.gson, com.fasterxml.jackson.databind, org.mockito;
    opens Server to com.google.gson, com.fasterxml.jackson.databind;
    opens Server.Messages to com.google.gson, com.fasterxml.jackson.databind;
    opens Server.Messages.Client to com.google.gson, com.fasterxml.jackson.databind;
    opens models.cards to com.google.gson, com.fasterxml.jackson.databind, org.mockito;

}