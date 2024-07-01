package views.ViewController;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Game;
import models.User;
import models.cards.Card;
import views.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PreGameViewController {
    public ImageView factionImage2;
    public ImageView factionImage3;
    public ImageView factionImage1;
    public ImageView factionImage4;
    public ImageView factionImage5;
    public Label factionDescription;
    @FXML
    private Pane changeFactionPane;
    public Pane mainPane;
    public FlowPane selectCardFlowPane;
    private ArrayList<ImageView> factionImages = new ArrayList<ImageView>();
    private int[] tmp = new int[]{0, 1, 2, 3, 4};
    private HashMap<String, ImageView> factions = new HashMap<String, ImageView>();
    private ArrayList<String> factionNames = Game.getAllFactions();
    private boolean changeFactionClicked = false;
    private boolean changeLeadeClicked = false;

    public void initialize() {
        changeFactionPane.setVisible(false);

        factionImages.add(factionImage1);
        factionImages.add(factionImage2);
        factionImages.add(factionImage3);
        factionImages.add(factionImage4);
        factionImages.add(factionImage5);

        for (String cardName : factionNames) {
            factions.put(cardName, new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Factions/faction_" + cardName + ".jpg")).toExternalForm())));
        }

        int counter = -1;
        for (ImageView imageFaction : factionImages) {
            counter++;
            if (imageFaction.equals(factionImage3)) {
                imageFaction.setImage(factions.get(Game.getLoggedInUser().getFaction()).getImage());
                factionDescription.setText(Game.getLoggedInUser().getFaction());
            } else if (!factionNames.equals(Game.getLoggedInUser().getFaction())) {
                imageFaction.setImage(factions.get(factionNames.get(counter)).getImage());
            }
        }

        selectCardFlowPane.setHgap(10);
        for (Card card : Game.getAllCards()) {
            Pane pane = new Pane();
            HBox hBox = new HBox();
            card.setWidth(113);
            card.setHeight(169.5);
            pane.getChildren().add(card);
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Cards/counter.png")).toExternalForm()));
            imageView.setFitWidth(16);
            imageView.setFitHeight(12);
            hBox.getChildren().add(imageView);
            Label label = new Label(Integer.toString(card.getMaxCapacity() - card.getSelectedCards()));
            label.setLayoutX(16);
            label.setLayoutY(12);
            hBox.getChildren().add(label);
            pane.getChildren().add(hBox);
            hBox.setLayoutX(card.getLayoutX() + card.getWidth() - 30);
            hBox.setLayoutY(card.getLayoutY() + card.getHeight() - 32);
            pane.setOnMouseClicked(e -> {
                User user = Game.getLoggedInUser();
                user.getDeckCards().add(Card.getCardByName(card.getName()));
                card.addToSelected();
                Game.addToSelectedCards(card);
                label.setText(Integer.toString(card.getMaxCapacity() - card.getSelectedCards()));
                if (card.getMaxCapacity() == card.getSelectedCards()) {
                    selectCardFlowPane.getChildren().remove(pane);
                }
            });
            selectCardFlowPane.getChildren().add(pane);
        }
    }

    public void ChangeFaction(MouseEvent mouseEvent) {
//        mainPane.setDisable(true);
        changeFactionPane.setVisible(true);
        changeFactionClicked = true;
        ;
    }

    @FXML
    private void handleBoxClick(MouseEvent event) {
        VBox selectedBox = (VBox) event.getSource();
    }


    public void changeFaction() {

    }

    public void moveRight(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveMethod(factionImages,factions,factionNames,factionDescription,0,4);
    }

    public void moveLeft(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveMethod(factionImages,factions,factionNames,factionDescription,4,0);
    }

    private void moveMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description,int begin,int end) {
        int cnt = tmp[end];
        for (int i = end; i > begin; i--) {
            tmp[i] = tmp[i - 1];
        }
        tmp[begin] = cnt;

        int counter = -1;
        for (ImageView imageFaction : images) {
            counter++;
            imageFaction.setImage(cards.get(cardsName.get(tmp[counter])).getImage());
        }

        description.setText(cardsName.get(tmp[2]));
        if(changeFactionClicked)
            Game.getLoggedInUser().setFaction(factionNames.get(tmp[2]));

    }

    public void closeFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(false);
        changeFactionClicked = false;
//        mainPane.setDisable(false);
    }
}
