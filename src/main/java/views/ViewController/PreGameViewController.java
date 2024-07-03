package views.ViewController;


import controllers.DataSaver;
import controllers.MenuController.PreGameMenuController;
import enums.Factions;
import enums.cards.LeaderInfo;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.AlertMaker;
import models.Game;
import models.User;
import models.cards.*;
import views.GameView;
import models.cards.Hero;
import models.cards.SpecialCard;
import models.cards.UnitCard;

import java.util.ArrayList;
import java.util.HashMap;
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
    public ImageView factionIcon;
    public Label username;
    public Button startGame;
    public TextField competitorUsername;
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
    private ArrayList<String> leaderNames = LeaderInfo.getLeaderNameByFaction(Game.getLoggedInUser().getFaction());
    private ArrayList<String> leaderAddresses = new ArrayList<String>();
    private boolean changeFactionClicked = false;
    private boolean changeLeaderClicked = false;
    public FlowPane selectedCardFlowPane;
    private User loggedInUser = Game.getLoggedInUser();


    public static void loadDeck(ArrayList<String> deckCards) {
    }

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


        setUpFactionImages();
        setUpLeadersImages();

        username.setText(loggedInUser.getUsername());
        leaderImage.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
        factionIcon.setImage(new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource(Game.getLoggedInUser().getFaction().iconAddress)).toExternalForm())).getImage());
        setUpLabels();

        selectCardFlowPane.setHgap(8);
        selectCardFlowPane.setVgap(8);
        selectedCardFlowPane.setHgap(8);
        selectedCardFlowPane.setVgap(8);
        setUpCards();
    }

    private void setUpCards() {
        Factions factions = loggedInUser.getFaction();
        for (Card card : Game.getAllCards()) {
            if (card.getFaction().equals(factions) || card.getFaction().equals(Factions.NEUTRAL)) {
                CreateNewCard(card, false);
            }
        }
    }

    private void setUpLabels() {
        count.setText(Integer.toString(loggedInUser.getNumberOfHeroCards() + loggedInUser.getNumberOfSpecialCards() + loggedInUser.getNumberOfUnitCards()));
        unit.setText(Integer.toString(loggedInUser.getNumberOfUnitCards()));
        hero.setText(Integer.toString(loggedInUser.getNumberOfHeroCards()));
        special.setText(Integer.toString(loggedInUser.getNumberOfSpecialCards()));
        strength.setText(Integer.toString(loggedInUser.getTotalUnitCardsStrength()));

    }

    private void setUpFactionImages() {
        for (String cardName : factionName) {
            factions.put(cardName, new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource("/Assets/Factions/faction_" + cardName + ".jpg")).toExternalForm())));
        }

        int counter = -1;
        for (ImageView imageFaction : factionImages) {
            counter++;
            if (imageFaction.equals(factionImage3)) {
                imageFaction.setImage(factions.get(loggedInUser.getFaction().name).getImage());
                factionDescription.setText(loggedInUser.getFaction().name);
            } else if (!factionName.get(counter).equals(loggedInUser.getFaction().name)) {
                imageFaction.setImage(factions.get(factionName.get(counter)).getImage());
            }
        }


    }

    private void setUpLeadersImages() {
        leaderAddresses = LeaderInfo.getLeaderAddressesByFaction(Game.getLoggedInUser().getFaction());
        int count = 0;
        for (String cardName : leaderNames) {
            System.out.println(leaderAddresses.get(count));
            leaders.put(cardName, new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource(leaderAddresses.get(count))).toExternalForm())));
            count++;
        }

        int counter = -1;
        for (ImageView imageFaction : leaderImages) {
            counter++;
            if (imageFaction.equals(leaderImage3)) {
                imageFaction.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
                leaderDescription.setText(loggedInUser.getLeader().getName());
            } else if (!leaderNames.get(counter).equals(loggedInUser.getFaction().name)) {
                imageFaction.setImage(leaders.get(leaderNames.get(counter)).getImage());
            }
        }
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

    private void moveLeftMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description) {
        int cnt = tmp[0];
        for (int i = 0; i < 4; i++) {
            tmp[i] = tmp[i + 1];
        }
        tmp[4] = cnt;

        move(tmp, images, cards, cardsName, description);
    }

    private void addToSelectedCards(Card card) {
        card.setWidth(120);
        card.setHeight(195);
        Pane pane = new Pane();
        pane.getChildren().add(card);
        pane.setOnMouseClicked(e -> {
            if (card instanceof UnitCard) {
                unit.setText(Integer.toString(Integer.parseInt(unit.getText()) - 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText()) - ((UnitCard) card).getConstantPower()));
            }
            if (card instanceof SpecialCard) {
                special.setText(Integer.toString(Integer.parseInt(special.getText()) - 1));
            }
            if (card instanceof Hero) {
                hero.setText(Integer.toString(Integer.parseInt(hero.getText()) - 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText()) - ((Hero) card).getConstantPower()));
            }
            loggedInUser.removeCardFromDeck(card);
            card.addToSelected();
            selectedCardFlowPane.getChildren().remove(pane);
            addToSelectCards(card);
            count.setText(Integer.toString(Integer.parseInt(unit.getText()) + Integer.parseInt(special.getText()) + Integer.parseInt(hero.getText())));
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
        if (changeFactionClicked) {
            Game.getLoggedInUser().setFaction(Factions.toFaction(cardsName.get(tmp[2])));
            factionIcon.setImage(new ImageView(new Image(Objects.requireNonNull(GameView.class.getResource(loggedInUser.getFaction().iconAddress)).toExternalForm())).getImage());
            leaderImage.setImage(new ImageView(new Image(Objects.requireNonNull(Objects.requireNonNull(GameView.class.getResource(LeaderInfo.getDefaultLeaderInfoByFaction(loggedInUser.getFaction()).cardImage)).toExternalForm()))).getImage());
            setUpLeadersImages();
        }
        if (changeLeaderClicked)
            Game.getLoggedInUser().setLeader(new Leader(Objects.requireNonNull(LeaderInfo.toLeaderInfo(cardsName.get(tmp[2])))));
    }

    public void openFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(true);
        changeFactionClicked = true;
    }

    public void closeFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(false);
        changeFactionClicked = false;
    }

    public void openLeaderPane(MouseEvent mouseEvent) {
        changeLeaderPane.setVisible(true);
        changeLeaderClicked = true;
    }

    public void closeLeaderPane(MouseEvent mouseEvent) {
        changeLeaderPane.setVisible(false);
        changeLeaderClicked = false;
        leaderImage.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
    }

    public void goToLoginMenu(MouseEvent mouseEvent) {
        loggedInUser.setNumberOfHeroCards(Integer.parseInt(hero.getText()));
        loggedInUser.setNumberOfSpecialCards(Integer.parseInt(special.getText()));
        loggedInUser.setNumberOfUnitCards(Integer.parseInt(unit.getText()));
        loggedInUser.setTotalUnitCardsStrength(Integer.parseInt(strength.getText()));
        try {
            new MainMenu().start(PreGameMenu.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void CreateNewCard(Card newCard, boolean isCardSelected) {
        Pane pane = new Pane();
        HBox hBox = new HBox();
        newCard.setWidth(120);
        newCard.setHeight(195);
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
            if (newCard instanceof UnitCard) {
                unit.setText(Integer.toString(Integer.parseInt(unit.getText()) + 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText()) + ((UnitCard) newCard).getConstantPower()));
            }
            if (newCard instanceof SpecialCard) {
                special.setText(Integer.toString(Integer.parseInt(special.getText()) + 1));
            }
            if (newCard instanceof Hero) {
                hero.setText(Integer.toString(Integer.parseInt(hero.getText()) + 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText()) + ((Hero) newCard).getConstantPower()));
            }
            loggedInUser.getDeckCards().add(Card.getCardByName(newCard.getName()));
            newCard.addToSelected();
            addToSelectedCards(Objects.requireNonNull(Card.getCardByName(newCard.getName())));
            Game.addToSelectedCards(newCard);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
            if (newCard.getMaxCapacity() == newCard.getSelectedCards()) {
                selectCardFlowPane.getChildren().remove(pane);
            }
            setUpLabels();
        });
        selectCardFlowPane.getChildren().add(pane);
    }

    public void downloadDeck(MouseEvent mouseEvent) {
        ArrayList<String> deckCards = new ArrayList<>();
        for (Card card : Game.getLoggedInUser().getDeckCards()) {
            deckCards.add(card.getName());
        }
        DataSaver.saveDeckCards(deckCards);
    }

    public void uploadDeck(MouseEvent mouseEvent) {
        DataSaver.loadDeckCards();
    }

    public void startGame(MouseEvent mouseEvent) {
        AlertMaker alertMaker = PreGameMenuController.checkCompetitorData(competitorUsername.getText());
        alertMaker.showAlert();
        if (alertMaker.getAlertType().equals(Alert.AlertType.INFORMATION)) {
            // todo game can start here
        }
    }
}
