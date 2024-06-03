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
    exports models;
    opens models to javafx.fxml;

//    opens Model to com.google.gson,com.fasterxml.jackson.databind;
//    opens Model.GameComponent.Missile.Cluster to com.fasterxml.jackson.databind, com.google.gson;
//    exports Controller;
//    opens Controller to javafx.fxml;
//    exports Controller.GameController;
//    opens Controller.GameController to javafx.fxml;
//    opens Model.GameComponent.Missile to com.fasterxml.jackson.databind, com.google.gson;
//    opens Model.GameComponent to com.fasterxml.jackson.databind, com.google.gson;
//    opens Model.GameComponent.Tank to com.fasterxml.jackson.databind, com.google.gson;
//    opens Model.GameComponent.Mig to com.fasterxml.jackson.databind, com.google.gson;
//    exports View.Game;
//    opens View.Game to javafx.fxml;
}