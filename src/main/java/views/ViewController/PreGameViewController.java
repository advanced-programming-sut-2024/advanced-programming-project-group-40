package views.ViewController;


import enums.Factions;
import enums.cards.LeaderInfo;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.Game;
import models.User;
import models.cards.*;
import views.GameView;
import models.cards.Hero;
import models.cards.SpecialCard;
import models.cards.UnitCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import views.MainMenu;
import views.PreGameMenu;

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
    public Label count;
    public Label unit;
    public Label special;
    public Label strength;
    public Label hero;
    @FXML
    private Pane changeFactionPane;
    public Pane mainPane;
    public VBox box3;
    public VBox box2;
    public VBox box1;
    public FlowPane selectCardFlowPane;
    private int[] tmp = new int[]{0, 1, 2, 3, 4};
    private ArrayList<ImageView> factionImages = new ArrayList<ImageView>();
    private HashMap<String, ImageView> factions = new HashMap<String, ImageView>();
    private ArrayList<String> factionName = Factions.getFactionsNames();
    private ArrayList<ImageView> leaderImages = new ArrayList<ImageView>();
    private HashMap<String, ImageView> leaders = new HashMap<String, ImageView>();
    private ArrayList<String> leaderNames = LeaderInfo.getLeaderNameByFaction(Game.getLoggedInUser().getFaction());
    private ArrayList<String> leaderAddresses = LeaderInfo.getLeaderAddressesByFaction(Game.getLoggedInUser().getFaction());
    private boolean changeFactionClicked = false;
    private boolean changeLeaderClicked = false;
    public FlowPane selectedCardFlowPane;
    private int numberOfUnitCards = 0;
    private int numberOfSpecialCards = 0;
    private int numberOfHeroCards = 0;
    private int totalUnitCardsStrength = 0;

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
                imageFaction.setImage(factions.get(Game.getLoggedInUser().getFaction().name).getImage());
                factionDescription.setText(Game.getLoggedInUser().getFaction().name);
            } else if (!factionName.get(counter).equals(Game.getLoggedInUser().getFaction().name)) {
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
        selectCardFlowPane.setVgap(10);
        selectedCardFlowPane.setHgap(10);
        selectedCardFlowPane.setVgap(10);
        setUpCards();
    }
    public void setUpCards(){
        User user = Game.getLoggedInUser();
        Factions factions = user.getFaction();
        for (Card card : Game.getAllCards()) {
            if (card.getFaction().equals(factions) || card.getFaction().equals(Factions.NEUTRAL)) {
                CreateNewCard(card, false);
            }
        }
    }

    public void ChangeFaction(MouseEvent mouseEvent) {
        mainPane.setDisable(true);
        changeFactionPane.setVisible(true);

    }


    public void moveRight(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveRightMethod(factionImages, factions, factionName, factionDescription);
        if (changeLeaderClicked)
            moveRightMethod(leaderImages, leaders, leaderNames, leaderDescription);
    }

    public void moveLeft(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveLeftMethod(factionImages, factions, factionName, factionDescription);
        if (changeLeaderClicked)
            moveLeftMethod(leaderImages, leaders, leaderNames, leaderDescription);
    }

    private void moveRightMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description) {
        int cnt = tmp[4];
        for (int i = 4; i > 0; i--) {
            tmp[i] = tmp[i - 1];
        }
        tmp[0] = cnt;

        move(tmp, images, cards, cardsName, description);
    }

    private void moveLeftMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description){
        int cnt = tmp[0];
        for (int i = 0; i < 4; i++) {
            tmp[i] = tmp[i + 1];
        }
        tmp[4] = cnt;

        move(tmp, images, cards, cardsName, description);
    }

    private void addToSelectedCards(Card card) {
        card.setWidth(113);
        card.setHeight(169.5);
        Pane pane = new Pane();
        pane.getChildren().add(card);
        pane.setOnMouseClicked(e -> {
            if (card instanceof UnitCard){
                numberOfUnitCards--;
                totalUnitCardsStrength -= ((UnitCard) card).getConstantPower();
            }
            if (card instanceof SpecialCard){
                numberOfSpecialCards--;
            }
            if (card instanceof Hero){
                numberOfHeroCards--;
                totalUnitCardsStrength -= ((Hero) card).getConstantPower();
            }
            User user = Game.getLoggedInUser();
            user.removeCardFromDeck(card);
            card.addToSelected();
            selectedCardFlowPane.getChildren().remove(pane);
            addToSelectCards(card);
        });
        selectedCardFlowPane.getChildren().add(pane);
    }

    private void addToSelectCards(Card card) {
        for (Node pane1 : selectCardFlowPane.getChildren()) {
            pane1 = (Pane) pane1;
            Card card1 = (Card) ((Pane) pane1).getChildren().get(0);
            if (card1.getName().equals(card.getName())) {
                card1.removeFromSelected();
                Label label = (Label) ((HBox) ((Pane) pane1).getChildren().get(1)).getChildren().get(1);
                label.setText(String.valueOf(Integer.parseInt(label.getText()) + 1));
                return;
            }
        }
        Card newCard = Card.getCardByName(card.getName());
        assert newCard != null;
        CreateNewCard(newCard, true);
    }
    public void move(int[] tmp, ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description) {
        int counter = 0;
        for (ImageView imageFaction : images) {
            imageFaction.setImage(cards.get(cardsName.get(tmp[counter])).getImage());
            counter++;
        }

        description.setText(cardsName.get(tmp[2]));
        if (changeFactionClicked)
            Game.getLoggedInUser().setFaction(Factions.toFaction(cardsName.get(tmp[2])));
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

    public void goToLoginMenu(MouseEvent mouseEvent) {
        try {
            new MainMenu().start(PreGameMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void CreateNewCard(Card newCard, boolean isCardSelected) {
        Pane pane = new Pane();
        HBox hBox = new HBox();
        newCard.setWidth(113);
        newCard.setHeight(169.5);
        pane.getChildren().add(newCard);
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Cards/counter.png")).toExternalForm()));
        imageView.setFitWidth(16);
        imageView.setFitHeight(12);
        hBox.getChildren().add(imageView);
        Label label = new Label(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
        if (isCardSelected) {
            newCard.setSelectedCards(newCard.getMaxCapacity() - 1);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
        }
        label.setLayoutX(16);
        label.setLayoutY(12);
        hBox.getChildren().add(label);
        pane.getChildren().add(hBox);
        hBox.setLayoutX(newCard.getLayoutX() + newCard.getWidth() - 30);
        hBox.setLayoutY(newCard.getLayoutY() + newCard.getHeight() - 32);
        pane.setOnMouseClicked(e -> {
            if (newCard instanceof UnitCard){
                numberOfUnitCards++;
                totalUnitCardsStrength += ((UnitCard) newCard).getConstantPower();
            }
            if (newCard instanceof SpecialCard){
                numberOfSpecialCards++;
            }
            if (newCard instanceof Hero){
                numberOfHeroCards++;
                totalUnitCardsStrength += ((Hero) newCard).getConstantPower();
            }
            User user = Game.getLoggedInUser();
            user.getDeckCards().add(Card.getCardByName(newCard.getName()));
            newCard.addToSelected();
            addToSelectedCards(Objects.requireNonNull(Card.getCardByName(newCard.getName())));
            Game.addToSelectedCards(newCard);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
            if (newCard.getMaxCapacity() == newCard.getSelectedCards()) {
                selectCardFlowPane.getChildren().remove(pane);
            }
        });
        selectCardFlowPane.getChildren().add(pane);
    }
}
