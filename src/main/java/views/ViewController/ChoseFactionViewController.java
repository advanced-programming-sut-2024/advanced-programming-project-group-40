package views.ViewController;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ChoseFactionViewController {
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    private ImageView currentlyEnlarged = null;
    @FXML
    public void initialize() {
        image1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> toggleEnlarged(image1));
        image2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> toggleEnlarged(image2));
        image3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> toggleEnlarged(image3));
        image4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> toggleEnlarged(image4));
        image5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> toggleEnlarged(image5));

    }

    private void toggleEnlarged(ImageView imageView) {
        if (currentlyEnlarged != null) {
            currentlyEnlarged.getStyleClass().remove("enlarged");
        }
        if (imageView.equals(currentlyEnlarged)) {
            currentlyEnlarged = null;
        } else {
            imageView.getStyleClass().add("enlarged");
            currentlyEnlarged = imageView;
        }
    }
}
