package views.ViewController;

import controllers.MenuController.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.AlertMaker;
import models.Game;
import models.User;
import views.ForgetPasswordMenu;
import views.MainMenu;
import views.SignUpMenu;
import views.TerminalView;

public class LoginViewController {
    public TextField authorizationCode;
    public VBox userPassVbox;
    public VBox authorizationVbox;
    public ScrollPane terminal;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void initialize() {
        authorizationVbox.setVisible(false);

    }

    public void signInClicked() {
        if (username.getText().equals("a") && password.getText().equals("a")) {
            Game.setLoggedInUser(new User("a", "a", "ahay@ahay.com", "ahay"));
        } else if (username.getText().equals("kian") && password.getText().equals("k")) {
            Game.setLoggedInUser(new User("kian", "k", "ahay@ahay.com", "ahay"));
        } else {
            AlertMaker alert = LoginMenuController.signIn(username.getText(), password.getText());
            if (alert.getAlertType().equals(Alert.AlertType.ERROR)) {
                alert.showAlert();
                return;
            }
        }
        // todo goToMainMenu should be deleted
        goToMainMenu();
//        userPassVbox.setVisible(false);
//        authorizationVbox.setVisible(true);
//        CodeAuthorization.sendCode(Objects.requireNonNull(Game.getUserByName(username.getText())).getEmail());
    }

    public void goToForgetPassword() {
        try {
            new ForgetPasswordMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToSignUpMenu() {
        try {
            new SignUpMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void goToMainMenu() {
        try {
            new MainMenu().start(Game.stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void verifyClicked(MouseEvent mouseEvent) {
        AlertMaker alert = LoginMenuController.checkCode(authorizationCode.getText(), username.getText());
        alert.showAlert();
        if (alert.getAlertType().equals(Alert.AlertType.CONFIRMATION)) {
            if (alert.isOK())
                LoginMenuController.stayLoggedInSelected();
            goToMainMenu();
        } else {
            authorizationVbox.setVisible(false);
            userPassVbox.setVisible(true);
        }
    }

    public void openTerminal(MouseEvent mouseEvent) {
        try {
            new TerminalView().start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
