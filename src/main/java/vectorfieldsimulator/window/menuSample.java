package vectorfieldsimulator.window;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class menuSample {

    public static MenuBar menuBar;
    public static Menu menuFile;
    public static Menu menuEdit;
    public static Menu menuView;
    private static int toggleConfigPanel = 0;

    public static void addMenus(Scene scene) {
        // Create menu bar with 3 fields
        menuBar = new MenuBar();

        menuFile = new Menu("File");
        menuEdit = new Menu("Edit");
        setEditOptions(menuEdit, scene);
        menuView = new Menu("View");


        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        ((VBox) scene.getRoot()).getChildren().add(0, menuBar);
    }

    // function for Edit Menu option (might need a whole entire dedicated class)
    private static void setEditOptions(Menu menuEdit, Scene scene) {
        MenuItem funcConfigPanel = new MenuItem("Open Function Configuration Panel");

        TextArea controlsPane = new configPanel().getControls();
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
