package vectorfieldsimulator.window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vectorfieldsimulator.propertyClasses.functionValues;
import vectorfieldsimulator.propertyClasses.radioColor;

public class menuSample {

    public static MenuBar menuBar;
    public static Menu menuFile;
    public static Menu menuEdit;
    public static Menu menuView;
    private static int toggleConfigPanel = 0;
    private final static double menuH = 10;

    public static void addMenus(Scene scene, radioColor colorModel, functionValues vectorFunctions) {
        // Create menu bar with 3 fields
        menuBar = new MenuBar();
        menuBar.setPrefHeight(menuH);

        menuFile = new Menu("File");
        menuEdit = new Menu("Edit");
        setEditOptions(menuEdit, scene, colorModel, vectorFunctions);
        menuView = new Menu("View");


        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        ((VBox) scene.getRoot()).getChildren().add(0, menuBar);
    }

    // function for Edit Menu option (might need a whole entire dedicated class)
    private static void setEditOptions(Menu menuEdit, Scene scene, radioColor colorModel, functionValues vectorFunctions) {
        MenuItem funcConfigPanel = new MenuItem("Open Function Configuration Panel");

        VBox controlsPane = new configPanel(colorModel, vectorFunctions);
        funcConfigPanel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                // get the Hbox
                HBox contentBox = (HBox) ((VBox) scene.getRoot()).getChildren().get(1);
                if (toggleConfigPanel == 0) {
                    contentBox.getChildren().add(controlsPane);
                    toggleConfigPanel = 1;
                } else if (toggleConfigPanel == 1) {
                    controlsPane.setVisible(false);
                    toggleConfigPanel = 2;
                } else if (toggleConfigPanel == 2) {
                    controlsPane.setVisible(true);
                    toggleConfigPanel = 1;
                }


            }
        });

        menuEdit.getItems().add(funcConfigPanel);
    }
}
