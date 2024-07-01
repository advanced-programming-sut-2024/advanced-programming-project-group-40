package views.ViewController;

import enums.Factions;
import enums.cards.LeaderInfo;
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
import models.cards.Leader;
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
    public Pane changeLeaderPane;
    public ImageView leaderImage2;
    public ImageView leaderImage1;
    public ImageView leaderImage4;
    public ImageView leaderImage3;
    public ImageView leaderImage5;
    public Label leaderDescription;
    public ImageView leaderImage;
    @FXML
    private Pane changeFactionPane;
    public Pane mainPane;
    public FlowPane selectCardFlowPane;
    private int[] tmp = new int[]{0, 1, 2, 3, 4};
    private ArrayList<ImageView> factionImages = new ArrayList<ImageView>();
    private HashMap<String, ImageView> factions = new HashMap<String, ImageView>();
    private ArrayList<String> factionName = Factions.getFactionsNames();
    private ArrayList<ImageView> leaderImages = new ArrayList<ImageView>();
    private HashMap<String, ImageView> leaders = new HashMap<String, ImageView>();
    private ArrayList<String> leaderNames = LeaderInfo.getLeaderNameByFaction(Factions.toFaction(Game.getLoggedInUser().getFaction()));
    private ArrayList<String> leaderAddresses = LeaderInfo.getLeaderAddressesByFaction(Factions.toFaction(Game.getLoggedInUser().getFaction()));
    private boolean changeFactionClicked = false;
    private boolean changeLeaderClicked = false;

    public void initialize() {
        changeFactionPane.setVisible(false);
        changeLeaderPane.setVisible(false);


        factionImages.add(factionImage1);
        factionImages.add(factionImage2);
        factionImages.add(factionImage3);
        factionImages.add(factionImage4);
        factionImages.add(factionImage5);

        leaderImages.add(leaderImage1);
        leaderImages.add(leaderImage2);
        leaderImages.add(leaderImage3);
        leaderImages.add(leaderImage4);
        leaderImages.add(leaderImage5);


        for (String cardName : factionName) {
            factions.put(cardName, new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Factions/faction_" + cardName + ".jpg")).toExternalForm())));
        }

        int counter = -1;
        for (ImageView imageFaction : factionImages) {
            counter++;
            if (imageFaction.equals(factionImage3)) {
                imageFaction.setImage(factions.get(Game.getLoggedInUser().getFaction()).getImage());
                factionDescription.setText(Game.getLoggedInUser().getFaction());
            } else if (!factionName.get(counter).equals(Game.getLoggedInUser().getFaction())) {
                imageFaction.setImage(factions.get(factionName.get(counter)).getImage());
            }
        }

        int count = 0;
        for (String cardName : leaderNames) {
            System.out.println(leaderAddresses.get(count));
            leaders.put(cardName, new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource(leaderAddresses.get(count))).toExternalForm())));
            count++;
        }

        int counter2 = -1;
        for (ImageView imageFaction : leaderImages) {
            counter2++;
            if (imageFaction.equals(leaderImage3)) {
                imageFaction.setImage(leaders.get(Game.getLoggedInUser().getLeader().getName()).getImage());
                leaderDescription.setText(Game.getLoggedInUser().getLeader().getName());
            } else if (!leaderNames.equals(Game.getLoggedInUser().getFaction())) {
                imageFaction.setImage(leaders.get(leaderNames.get(counter2)).getImage());
            }
        }

        leaderImage.setImage(leaders.get(Game.getLoggedInUser().getLeader().getName()).getImage());




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


    @FXML
    private void handleBoxClick(MouseEvent event) {
        VBox selectedBox = (VBox) event.getSource();
    }

    public void moveRight(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveMethod(factionImages, factions, factionName, factionDescription, 0, 4);
        if (changeLeaderClicked)
            moveMethod(leaderImages, leaders, leaderNames, leaderDescription, 0, 4);
    }

    public void moveLeft(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveMethod(factionImages, factions, factionName, factionDescription, 4, 0);
        if (changeLeaderClicked)
            moveMethod(leaderImages, leaders, leaderNames, leaderDescription, 4, 0);
    }

    private void moveMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description, int begin, int end) {
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
        if (changeFactionClicked)
            Game.getLoggedInUser().setFaction(cardsName.get(tmp[2]));
        if (changeLeaderClicked)
            Game.getLoggedInUser().setLeader(new Leader(Objects.requireNonNull(LeaderInfo.toLeaderInfo(cardsName.get(tmp[2])))));

    }

    public void changeFaction(MouseEvent mouseEvent) {
//        mainPane.setDisable(true);
        changeFactionPane.setVisible(true);
        changeFactionClicked = true;
    }

    public void closeFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(false);
        changeFactionClicked = false;
//        mainPane.setDisable(false);
    }

    public void changeLeader(MouseEvent mouseEvent) {
//        mainPane.setDisable(true);
        changeLeaderPane.setVisible(true);
        changeLeaderClicked = true;
    }

    public void closeLeaderPane(MouseEvent mouseEvent) {
        changeLeaderPane.setVisible(false);
        changeLeaderClicked = false;
//        mainPane.setDisable(false);

        leaderImage.setImage(leaders.get(Game.getLoggedInUser().getLeader().getName()).getImage());
    }
}
