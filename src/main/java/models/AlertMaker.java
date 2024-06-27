package models;

import javafx.scene.control.ButtonType;

public class AlertMaker extends javafx.scene.control.Alert {
    private final String header;
    private final String message;
    private boolean OK;

    public AlertMaker(AlertType alertType, String header, String message) {
        super(alertType);
        this.header = header;
        this.message = message;
        this.OK = false;
    }

    public void showAlert() {
        this.setTitle(getAlertType().toString() + "!");
        this.setHeaderText(header);
        this.setContentText(message);
        this.showAndWait().ifPresent(response -> {
            if (this.getAlertType().equals(AlertType.CONFIRMATION))
                if (response == ButtonType.OK) {
                    this.OK = true;
                }
        });
    }


    public boolean isOK() {
        return OK;
    }
}
