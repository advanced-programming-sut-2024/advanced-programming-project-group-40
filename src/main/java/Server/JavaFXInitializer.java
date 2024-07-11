package Server;

import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXInitializer extends Application {
    @Override
    public void start(Stage primaryStage) {
        // No need to implement anything here
    }

    public static void initJavaFX() {
        new Thread(() -> Application.launch(JavaFXInitializer.class)).start();
        try {
            // Wait for JavaFX to be initialized
            synchronized (JavaFXInitializer.class) {
                JavaFXInitializer.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void notifyJavaFXInitialized() {
        synchronized (JavaFXInitializer.class) {
            JavaFXInitializer.class.notify();
        }
    }
}
