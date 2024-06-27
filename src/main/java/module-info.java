module advanced.programming.project.group {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;
    exports views;
    opens views to javafx.fxml;
    exports views.ViewController;
    opens views.ViewController to javafx.fxml;
    opens models to com.google.gson,com.fasterxml.jackson.databind;

}