package models;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;

public class Alert extends javafx.scene.control.Alert {
    private javafx.scene.control.Alert errorAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
    private javafx.scene.control.Alert confirmationAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
    private javafx.scene.control.Alert informationAlert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
    private String header;
    private String message;

    public Alert(AlertType alertType, String header, String message) {
        super(alertType);
        this.header = header;
        this.message = message;
    }

    @FXML
    private void initialize() {
        //customize the buttons
        confirmationAlert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
    }


    public void showAlert() {
        switch (getAlertType()) {
            case AlertType.ERROR: {
                errorAlert.setTitle("Error!");
                errorAlert.setHeaderText(header);
                errorAlert.setContentText(message);
                break;
            }
            case AlertType.CONFIRMATION: {
                confirmationAlert.setTitle("Confirmation!");
                confirmationAlert.setHeaderText(header);
                confirmationAlert.setContentText(message);
                break;
            }
            case AlertType.INFORMATION: {
                informationAlert.setTitle("Information!");
                informationAlert.setHeaderText(header);
                informationAlert.setContentText(message);
                break;
            }
        }
    }
}
