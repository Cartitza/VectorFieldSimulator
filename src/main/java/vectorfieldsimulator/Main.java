package vectorfieldsimulator;

import javafx.scene.image.Image;
import vectorfieldsimulator.window.windowContent;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        windowContent window = new windowContent();

        stage.setTitle("Matlab Hmmm");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
        stage.setScene(window.scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}