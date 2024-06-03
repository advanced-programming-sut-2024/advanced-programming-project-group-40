package views;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

public class Alert {
    private static javafx.scene.control.Alert errorAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
    private static javafx.scene.control.Alert confirmationAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
    private static javafx.scene.control.Alert informationAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);

    @FXML
    private void initialize() {
        //customize the buttons
        confirmationAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
    }

    public static void makeAlert(String type, String header, String context) {
        switch (type) {
            case "error": {
                errorAlert.setTitle("Error!");
                errorAlert.setHeaderText(header);
                errorAlert.setContentText(context);
                errorAlert.showAndWait();
                break;
            }
            case "confirmation": {
                confirmationAlert.setTitle("Confirmation!");
                confirmationAlert.setHeaderText(header);
                confirmationAlert.setContentText(context);
                confirmationAlert.showAndWait();
                break;
            }
            case "information": {
                informationAlert.setTitle("Information!");
                informationAlert.setHeaderText(header);
                informationAlert.setContentText(context);
                informationAlert.showAndWait();
                break;
            }
        }
    }
}
