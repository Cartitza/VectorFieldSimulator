package vectorfieldsimulator.window;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuSample {

    public static MenuBar menuBar;
    public static Menu menuFile;
    public static Menu menuEdit;
    public static Menu menuView;

    public static void addMenu(Scene scene) {
        menuBar = new MenuBar();

        menuFile = new Menu("File");
        menuEdit = new Menu("Edit");
        menuView = new Menu("View");

        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        ((VBox) scene.getRoot()).getChildren().add(0, menuBar);
    }
}
