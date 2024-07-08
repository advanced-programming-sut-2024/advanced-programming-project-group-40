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
    requires org.junit.jupiter.api;
    exports views;
    exports enums.cards to com.google.gson;
    opens views to javafx.fxml;
    opens Tests to junit;
    opens enums to com.google.gson,com.fasterxml.jackson.databind;
    opens controllers to com.google.gson,com.fasterxml.jackson.databind;
    exports views.ViewController;
    opens views.ViewController to javafx.fxml;
    opens Server.Models to com.google.gson;
    opens models to com.google.gson,com.fasterxml.jackson.databind;
    opens models.cards to com.google.gson,com.fasterxml.jackson.databind;
}