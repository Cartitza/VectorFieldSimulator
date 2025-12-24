package vectorfieldsimulator.window;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import vectorfieldsimulator.propertyClasses.*;
import vectorfieldsimulator.graph.graphPane2D;


public class windowContent {

    private double mouseX;
    private double mouseY;
    private Circle circle;
    private radioColor colorModel;
    private mousePosition mouseMovement;

    private double H = 720;
    private double W = 1280;

    private Scene scene;

    public windowContent() {
        // pass it around to have the same value in multiple places
        colorModel = new radioColor();
        mouseMovement = new mousePosition();
        scene = new Scene(createContent());

        scene.setOnMouseMoved(e -> {
            mouseMovement.setPosition(e.getSceneX(), e.getSceneY());
        });

        menuSample.addMenus(scene, colorModel);
    }

    public Scene getScene() {
        return this.scene;
    }

    public Parent createContent() {
        // scene is a VBox with a HBox inside with a Pane
        VBox root = new VBox();
        root.setPrefSize(W, H);
        HBox contentBox = new HBox();
        Pane visualizerPane = new graphPane2D(W, H, mouseMovement, colorModel).getPane();

        /*
            TODO:

            - Canvas class: added inside graphPane2D.java (might need separate class)
            - change onUpdate for animation
            - configPanel.java -> will need to add handling for Functions as input

         */

        // whole Pane definition is now another class
        contentBox.getChildren().add(visualizerPane);
        root.getChildren().add(contentBox);
        return root;
    }
}
