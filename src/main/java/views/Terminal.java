//package views;
//
//import javafx.scene.Scene;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.stage.Stage;
//
//import java.awt.*;
//import java.util.HashSet;
//import java.util.Set;
//
//public class Terminal {
//
//    private final Set<KeyCode> pressedKeys = new HashSet<>();
//
//    public void terminal(Scene scene) {
//        TextArea textArea = new TextArea();
//        textArea.setPrefHeight(366.0);
//        textArea.setPrefWidth(286.0);
//        textArea.getStyleClass().add("terminal");
//
////        ScrollPane scrollPane = new ScrollPane();
////        scrollPane.setLayoutX(13.0);
////        scrollPane.setLayoutY(400.0);
////        scrollPane.setPrefHeight(200.0);
////        scrollPane.setPrefWidth(287.0);
////        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
////        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
////        scrollPane.getStyleClass().add("menu-scroll-pane");
////        scrollPane.setContent(textArea);
////        scrollPane.setVisible(false);
//
//        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            pressedKeys.add(event.getCode());
//            if (pressedKeys.contains(KeyCode.A) && pressedKeys.contains(KeyCode.T)) {
//                scrollPane.setVisible(!scrollPane.isVisible());
//                if (scrollPane.isVisible()) {
//                    scrollPane.requestFocus();
//                }
//                event.consume();
//            }
//        });
//        scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
//            pressedKeys.remove(event.getCode());
//        });
//    }
//}
