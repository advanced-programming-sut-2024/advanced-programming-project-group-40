package views.ViewController;


import Server.ClientHandler;
import Server.Messages.Client.AddRemoveCardMessage;
import Server.Messages.Client.UpdateMessage;
import Server.Messages.MessageSubType;
import controllers.DataSaver;
import controllers.MenuController.PreGameMenuController;
import enums.AlertInfo.AlertHeader;
import enums.AlertInfo.messages.PreGameMenuMessages;
import enums.Factions;
import enums.cards.LeaderInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.AlertMaker;
import models.Game;
import models.User;
import models.cards.*;
import views.GameView;
import views.MainMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PreGameViewController {
    @FXML
    private Label factionNameHeader;
    @FXML
    private ImageView factionImage2;
    @FXML
    private ImageView factionImage3;
    @FXML
    private ImageView factionImage1;
    @FXML
    private ImageView factionImage4;
    @FXML
    private ImageView factionImage5;
    @FXML
    private Label factionNameLabel;
    @FXML
    private Pane changeLeaderPane;
    @FXML
    private ImageView leaderImage2;
    @FXML
    private ImageView leaderImage1;
    @FXML
    private ImageView leaderImage4;
    @FXML
    private ImageView leaderImage3;
    @FXML
    private ImageView leaderImage5;
    @FXML
    private Label leaderDescription;
    @FXML
    private ImageView leaderImage;
    @FXML
    private Label count;
    @FXML
    private Label unit;
    @FXML
    private Label special;
    @FXML
    private Label strength;
    @FXML
    private Label hero;
    @FXML
    private ImageView factionIcon;
    @FXML
    private Label username;
    @FXML
    private TextField competitorUsername;
    @FXML
    private Label leaderNameLabel;
    @FXML
    private Label unit2;
    @FXML
    private Label special2;
    @FXML
    private Pane changeFactionPane;
    @FXML
    private FlowPane selectCardFlowPane;
    @FXML
    private FlowPane selectedCardFlowPane;
    private int[] tmp = new int[]{0, 1, 2, 3, 4};
    private ArrayList<ImageView> factionImages = new ArrayList<ImageView>();
    private HashMap<String, ImageView> factions = new HashMap<String, ImageView>();
    private ArrayList<String> factionName = Factions.getFactionsNames();
    private ArrayList<ImageView> leaderImages = new ArrayList<ImageView>();
    private HashMap<String, ImageView> leaders = new HashMap<String, ImageView>();
    private ArrayList<String> leaderNames = new ArrayList<String>();
    private ArrayList<String> leaderAddresses = new ArrayList<String>();
    private boolean changeFactionClicked = false;
    private boolean changeLeaderClicked = false;
    private User loggedInUser = Game.getLoggedInUser();
    public static String startGameStatus = "";
    private boolean publicGame;

    @FXML
    private static void loadDeck(ArrayList<String> deckCards) {
        // todo ask ----------------------------------------------------------------
    }

    @FXML
    private void initialize() {
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


        if (!PreGameMenuController.getSpecificUser().isEmpty()) {
            competitorUsername.setText(PreGameMenuController.getSpecificUser());
            competitorUsername.setDisable(true);
            PreGameMenuController.setSpecificUser("");
        }

        setUpFactionImages();
        setUpLeadersImages();

        username.setText(loggedInUser.getUsername());
        leaderImage.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
        factionNameHeader.setText(loggedInUser.getFaction().name.toUpperCase());
        factionIcon.setImage(new ImageView(new Image(Objects.requireNonNull
                        (GameView.class.getResource(Game.getLoggedInUser().getFaction().iconAddress))
                .toExternalForm())).getImage());
        setUpLabels();
        setUpCards();
        setUpSelectedCards();
        unit.textProperty().addListener((observable, oldValue, newValue) -> {
            setUnitCardColor();
        });

        special.textProperty().addListener((observable, oldValue, newValue) -> {
            setSpecialCardColor();
        });


        selectCardFlowPane.setHgap(8);
        selectCardFlowPane.setVgap(8);
        selectedCardFlowPane.setHgap(8);
        selectedCardFlowPane.setVgap(8);
        setUpCards();
        System.out.println(Game.getLoggedInUser().getUsername());
        ClientHandler.client.update(new UpdateMessage(Game.getLoggedInUser().getUsername(), MessageSubType.PREGAME_UPDATE));
    }

    private void setUpCards() {
        Factions factions = loggedInUser.getFaction();
        for (Card card : Game.getAllCards()) {
            if (card.getFaction().equals(factions) || card.getFaction().equals(Factions.NEUTRAL)) {
                CreateNewCard(Objects.requireNonNull(Card.getCardByName(card.getName())), false);
            }
        }
    }

    private void setUpLabels() {
        count.setText(Integer.toString(loggedInUser.getNumberOfHeroCards() + loggedInUser.getNumberOfSpecialCards()
                + loggedInUser.getNumberOfUnitCards()));
        unit.setText(Integer.toString(loggedInUser.getNumberOfUnitCards()));
        hero.setText(Integer.toString(loggedInUser.getNumberOfHeroCards()));
        special.setText(Integer.toString(loggedInUser.getNumberOfSpecialCards()));
        strength.setText(Integer.toString(loggedInUser.getTotalUnitCardsStrength()));
        setSpecialCardColor();
        setUnitCardColor();
    }

    private void setUnitCardColor() {
        if (Integer.parseInt(unit.getText()) < 22) {
            unit.setStyle("-fx-text-fill: red;");
            unit2.setVisible(true);
            unit2.setStyle("-fx-text-fill: red;");
        } else {
            unit.setStyle("-fx-text-fill: #C2E7E3;");
            unit2.setVisible(false);
        }
    }

    private void setSpecialCardColor() {
        if (Integer.parseInt(special.getText()) > 10) {
            special.setStyle("-fx-text-fill: red;");
            special2.setVisible(true);
            special2.setStyle("-fx-text-fill: red;");
        } else {
            special.setStyle("-fx-text-fill: #C2E7E3;");
            special2.setVisible(false);
        }
    }

    private void setUpFactionImages() {
        for (String cardName : factionName) {
            factions.put(cardName, new ImageView(new Image(Objects.requireNonNull
                            (GameView.class.getResource(STR."/Assets/Factions/faction_\{cardName}.jpg"))
                    .toExternalForm())));
        }

        int counter = -1;
        for (ImageView imageFaction : factionImages) {
            counter++;
            if (imageFaction.equals(factionImage3)) {
                imageFaction.setImage(factions.get(loggedInUser.getFaction().name).getImage());
                factionNameLabel.setText(loggedInUser.getFaction().name);
            } else if (!factionName.get(counter).equals(loggedInUser.getFaction().name)) {
                imageFaction.setImage(factions.get(factionName.get(counter)).getImage());
            }
        }


    }

    private void setUpLeadersImages() {
        leaderNames = LeaderInfo.getLeaderNameByFaction(Game.getLoggedInUser().getFaction());
        leaderAddresses = LeaderInfo.getLeaderAddressesByFaction(Game.getLoggedInUser().getFaction());
        int count = 0;
        for (String cardName : leaderNames) {
            leaders.put(cardName, new ImageView(new Image(Objects.requireNonNull
                    (GameView.class.getResource(leaderAddresses.get(count))).toExternalForm())));
            count++;
        }

        int counter = -1;
        for (ImageView imageFaction : leaderImages) {
            counter++;
            if (imageFaction.equals(leaderImage3)) {
                imageFaction.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
                leaderDescription.setText(Objects.requireNonNull(LeaderInfo.toLeaderInfo
                        (loggedInUser.getLeader().getName())).description);
                leaderNameLabel.setText(loggedInUser.getLeader().getName());
            } else if (!leaderNames.get(counter).equals(loggedInUser.getFaction().name)) {
                imageFaction.setImage(leaders.get(leaderNames.get(counter)).getImage());
            }
        }
    }

    @FXML
    private void moveRight(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveRightMethod(factionImages, factions, factionName, null, factionNameLabel);
        if (changeLeaderClicked)
            moveRightMethod(leaderImages, leaders, leaderNames, leaderDescription, leaderNameLabel);
    }

    @FXML
    private void moveLeft(MouseEvent mouseEvent) {
        if (changeFactionClicked)
            moveLeftMethod(factionImages, factions, factionName, null, factionNameLabel);
        if (changeLeaderClicked)
            moveLeftMethod(leaderImages, leaders, leaderNames, leaderDescription, leaderNameLabel);
    }

    private void moveRightMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description, Label name) {
        int cnt = tmp[4];
        for (int i = 4; i > 0; i--) {
            tmp[i] = tmp[i - 1];
        }
        tmp[0] = cnt;

        move(tmp, images, cards, cardsName, description, name);
    }

    private void moveLeftMethod(ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description, Label name) {
        int cnt = tmp[0];
        for (int i = 0; i < 4; i++) {
            tmp[i] = tmp[i + 1];
        }
        tmp[4] = cnt;

        move(tmp, images, cards, cardsName, description, name);
    }

    private void addToSelectedCards(Card card) {
        card.setWidth(120);
        card.setHeight(195);
        Pane pane = new Pane();
        pane.getChildren().add(card);
        pane.setOnMouseClicked(e -> {
            int power = 0;
            int cardType = 1;
            if (card instanceof UnitCard) {
                unit.setText(Integer.toString(Integer.parseInt(unit.getText()) - 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText())
                        - ((UnitCard) card).getConstantPower()));
                power = ((UnitCard) card).getConstantPower();
            }
            if (card instanceof SpecialCard) {
                special.setText(Integer.toString(Integer.parseInt(special.getText()) - 1));
                cardType = 2;
            }
            if (card instanceof Hero) {
                hero.setText(Integer.toString(Integer.parseInt(hero.getText()) - 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText())
                        - ((Hero) card).getConstantPower()));
                power = ((Hero) card).getConstantPower();
                cardType = 3;
            }
            loggedInUser.removeCardFromDeck(card);
            card.addToSelected();
            selectedCardFlowPane.getChildren().remove(pane);
            addToSelectCards(card);
            count.setText(Integer.toString(Integer.parseInt(unit.getText())
                    + Integer.parseInt(special.getText()) + Integer.parseInt(hero.getText())));
            saveData();
            AddRemoveCardMessage addRemoveCardMessage = new AddRemoveCardMessage(card.getName(), cardType, power, false);
            ClientHandler.client.removeCard(addRemoveCardMessage);
        });
        selectedCardFlowPane.getChildren().add(pane);
    }

    private void setUpSelectedCards() {
        for (Card card : loggedInUser.getDeckCards()) {
            addToSelectedCards(card);
        }
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

    private void move(int[] tmp, ArrayList<ImageView> images, HashMap<String, ImageView> cards, ArrayList<String> cardsName, Label description, Label name) {
        int counter = 0;
        for (ImageView imageFaction : images) {
            imageFaction.setImage(cards.get(cardsName.get(tmp[counter])).getImage());
            counter++;
        }

        name.setText(cardsName.get(tmp[2]));
        if (changeFactionClicked) {
            updateFaction(cardsName.get(tmp[2]));
        }
        if (changeLeaderClicked) {
            loggedInUser.setLeader(new Leader(Objects.requireNonNull(LeaderInfo.toLeaderInfo(cardsName.get(tmp[2])))));
            loggedInUser.setLeaderName(loggedInUser.getLeader().getName());
            description.setText(Objects.requireNonNull(LeaderInfo.toLeaderInfo(cardsName.get(tmp[2]))).description);

        }

    }

    @FXML
    private void openFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(true);
        changeFactionClicked = true;
    }

    @FXML
    private void closeFactionPane(MouseEvent mouseEvent) {
        changeFactionPane.setVisible(false);
        changeFactionClicked = false;
    }

    @FXML
    private void openLeaderPane(MouseEvent mouseEvent) {
        changeLeaderPane.setVisible(true);
        changeLeaderClicked = true;
    }

    @FXML
    private void closeLeaderPane(MouseEvent mouseEvent) {
        changeLeaderPane.setVisible(false);
        changeLeaderClicked = false;
        leaderImage.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
    }

    @FXML
    private void goToLoginMenu(MouseEvent mouseEvent) {
        saveData();
        DataSaver.saveUsers();
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveData() {
        loggedInUser.setNumberOfHeroCards(Integer.parseInt(hero.getText()));
        loggedInUser.setNumberOfSpecialCards(Integer.parseInt(special.getText()));
        loggedInUser.setNumberOfUnitCards(Integer.parseInt(unit.getText()));
        loggedInUser.setTotalUnitCardsStrength(Integer.parseInt(strength.getText()));
    }

    private void CreateNewCard(Card newCard, boolean isCardSelected) {
        User loggedInUser = Game.getLoggedInUser();
        Pane pane = new Pane();
        HBox hBox = new HBox();
        newCard.setWidth(120);
        newCard.setHeight(195);
        pane.getChildren().add(newCard);
        ImageView imageView = new ImageView(new Image(Objects.requireNonNull
                (GameView.class.getResource("/Assets/Cards/counter.png")).toExternalForm()));
        imageView.setFitWidth(16);
        imageView.setFitHeight(12);
        hBox.getChildren().add(imageView);
        Label label = new Label(Integer.toString(newCard.getMaxCapacity()
                - loggedInUser.cardsInDeckFromCardName(newCard.getName())));
        if (label.getText().equals("0")) {
            return;
        }
        if (isCardSelected) {
            newCard.setSelectedCards(newCard.getMaxCapacity() - 1);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
        }
        label.setLayoutX(16);
        label.setLayoutY(12);
        label.setStyle("-fx-text-fill: #522e11;");
        hBox.getChildren().add(label);
        pane.getChildren().add(hBox);
        hBox.setLayoutX(newCard.getLayoutX() + newCard.getWidth() - 30);
        hBox.setLayoutY(newCard.getLayoutY() + newCard.getHeight() - 32);
        pane.setOnMouseClicked(e -> {
            int power = 0;
            int cardType = 1;
            if (newCard instanceof UnitCard) {
                unit.setText(Integer.toString(Integer.parseInt(unit.getText()) + 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText())
                        + ((UnitCard) newCard).getConstantPower()));
                power = ((UnitCard) newCard).getConstantPower();
            }
            if (newCard instanceof SpecialCard) {
                special.setText(Integer.toString(Integer.parseInt(special.getText()) + 1));
                cardType = 2;
            }
            if (newCard instanceof Hero) {
                hero.setText(Integer.toString(Integer.parseInt(hero.getText()) + 1));
                strength.setText(Integer.toString(Integer.parseInt(strength.getText())
                        + ((Hero) newCard).getConstantPower()));
                power = ((Hero) newCard).getConstantPower();
                cardType = 3;
            }
            loggedInUser.getDeckCards().add(Card.getCardByName(newCard.getName()));
            loggedInUser.getDeckCardsName().add(newCard.getName());
            newCard.addToSelected();
            addToSelectedCards(Objects.requireNonNull(Card.getCardByName(newCard.getName())));
            Game.addToSelectedCards(newCard);
            label.setText(Integer.toString(newCard.getMaxCapacity() - newCard.getSelectedCards()));
            if (newCard.getMaxCapacity() == newCard.getSelectedCards()) {
                selectCardFlowPane.getChildren().remove(pane);
            }
            count.setText(Integer.toString(Integer.parseInt(unit.getText())
                    + Integer.parseInt(special.getText()) + Integer.parseInt(hero.getText())));
            saveData();
            AddRemoveCardMessage addRemoveCardMessage = new AddRemoveCardMessage(newCard.getName(), cardType, power, true);
            ClientHandler.client.addCard(addRemoveCardMessage);
        });
        selectCardFlowPane.getChildren().add(pane);
    }

    @FXML
    private void downloadDeck(MouseEvent mouseEvent) {
        ArrayList<String> deckCards = loggedInUser.getDeckCardsName();
        DataSaver.saveDeckCards(deckCards, loggedInUser.getLeader());
        AlertMaker alertMaker = new AlertMaker(Alert.AlertType.INFORMATION, "Download Deck"
                , PreGameMenuMessages.DOWNLOAD_DECK.toString());
        alertMaker.showAlert();
    }

    @FXML
    private void uploadDeck(MouseEvent mouseEvent) {
        DataSaver.loadDeckCards();
        selectedCardFlowPane.getChildren().clear();
        selectCardFlowPane.getChildren().clear();
        setUpSelectedCards();
        leaderImage.setImage(leaders.get(loggedInUser.getLeader().getName()).getImage());
        setUpCards();
        setUpLabels();
        factionNameHeader.setText(loggedInUser.getFaction().name.toUpperCase());
        factionIcon.setImage(new ImageView(new Image(Objects.requireNonNull
                (GameView.class.getResource(loggedInUser.getFaction().iconAddress)).toExternalForm())).getImage());
        AlertMaker alertMaker = new AlertMaker(Alert.AlertType.INFORMATION, "Upload Deck"
                , PreGameMenuMessages.UPLOAD_DECK.toString());
        alertMaker.showAlert();
    }

    @FXML
    private void startGame(MouseEvent mouseEvent) {
        RequestMessage requestMessage = new RequestMessage(loggedInUser.getUsername(), competitorUsername.getText(), MessageSubType.CHECK_ONLINE);
        boolean isOnline = ClientHandler.client.request(requestMessage).wasSuccessfull();
        if (!isOnline) {
            AlertMaker alertMaker = new AlertMaker(Alert.AlertType.ERROR, "Game Request", PreGameMenuMessages.USER_NOT_ONLINE.toString());
            alertMaker.showAlert();
            return;
        }
        RequestMessage requestMessage2 = new RequestMessage(loggedInUser.getUsername(), loggedInUser.getUsername(), MessageSubType.CHECK_IN_GAME);
        boolean isInGame = ClientHandler.client.request(requestMessage2).wasSuccessfull();
        if (isInGame) {
            AlertMaker alertMaker = new AlertMaker(Alert.AlertType.ERROR, "Game Request", PreGameMenuMessages.USER_IN_GAME.toString());
            alertMaker.showAlert();
            return;
        }
        AlertMaker alert = new AlertMaker(Alert.AlertType.CONFIRMATION, AlertHeader.PRE_GAME.toString(), PreGameMenuMessages.PUBLIC_GAME.toString());
        alert.showAlert();
        if (alert.isOK())
            publicGame = true;
        else
            publicGame = false;
        saveData();
        AlertMaker alertMaker = PreGameMenuController.checkCompetitorData(competitorUsername.getText());
        if (alertMaker.getAlertType().equals(Alert.AlertType.INFORMATION)) {
            saveData();
            try {
                PreGameMenuController.startGame(competitorUsername.getText());
                startGameStatus = "Waiting for response";
                Thread thread = new Thread(() -> {
                    while (true) {
                        if (!startGameStatus.equals("Waiting for response")) {
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    if (startGameStatus.equals("Game Started")) {
                        Platform.runLater(() -> {
                            try {
                                alertMaker.showAlert();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        RequestMessage requestMessage1 = new RequestMessage(loggedInUser.getUsername(), loggedInUser.getUsername(), MessageSubType.ADD_TO_USERS_IN_GAME);
                        ClientHandler.client.request(requestMessage1);
                        System.out.println("YOOOOHOOOOOOOOO");
                        //TODO : Start the game
                    } else {
                        Platform.runLater(() -> {
                            AlertMaker alertMaker1 = new AlertMaker(Alert.AlertType.ERROR, "Game Request", PreGameMenuMessages.GAME_REQUEST_REJECTED.toString());
                            alertMaker1.showAlert();
                        });
                    }
                });
                thread.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            alertMaker.showAlert();
        }
    }

    public void updateFaction(String name) {
        loggedInUser.setFaction(Factions.toFaction(name));
        loggedInUser.setLeader(new Leader(LeaderInfo.getDefaultLeaderInfoByFaction(loggedInUser.getFaction())));
        loggedInUser.setLeaderName(LeaderInfo.getDefaultLeaderInfoByFaction(loggedInUser.getFaction()).name);
        factionIcon.setImage(new ImageView(new Image(Objects.requireNonNull
                (GameView.class.getResource(loggedInUser.getFaction().iconAddress)).toExternalForm())).getImage());

        leaderImage.setImage(new ImageView(new Image(Objects.requireNonNull
                (Objects.requireNonNull(GameView.class.getResource(LeaderInfo.getDefaultLeaderInfoByFaction
                        (loggedInUser.getFaction()).cardImage)).toExternalForm()))).getImage());
        factionNameHeader.setText(loggedInUser.getFaction().name.toUpperCase());
        setUpLeadersImages();
        selectCardFlowPane.getChildren().clear();
        selectedCardFlowPane.getChildren().clear();
        loggedInUser.getDeckCards().clear();
        setUpCards();
        setUpSelectedCards();
        setUpLabels();
        setUpLeadersImages();
    }

    public void startRandomGame(MouseEvent mouseEvent) {
    }
}