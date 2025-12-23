package vectorfieldsimulator.window;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import vectorfieldsimulator.propertyClasses.radioColor;


public class configPanel extends VBox {

    private radioColor colorModel;

    public configPanel(radioColor colorModel) {
        this.colorModel = colorModel;

        ToggleGroup group = new ToggleGroup();

        RadioButton redBtn = new RadioButton("Red");
        RadioButton blueBtn = new RadioButton("Blue");
        RadioButton greenBtn = new RadioButton("Green");

        redBtn.setToggleGroup(group);
        blueBtn.setToggleGroup(group);
        greenBtn.setToggleGroup(group);

        redBtn.setSelected(true); // Default selection

        // Set color when radio button is selected
        redBtn.setOnAction(e -> colorModel.setSelectedColor(Color.RED));
        blueBtn.setOnAction(e -> colorModel.setSelectedColor(Color.BLUE));
        greenBtn.setOnAction(e -> colorModel.setSelectedColor(Color.GREEN));

        this.setPrefWidth(250); // Fixed width
        this.setMinWidth(250);  // Prevents it from shrinking
        this.setMaxWidth(250);  // Prevents it from growing
        this.setStyle("-fx-background-color: #f0f0f0;"); // Light gray background
        this.setPadding(new Insets(15)); // Padding around content
        this.getChildren().addAll(redBtn, blueBtn, greenBtn);
        this.getChildren().add(new TextArea());
        this.setSpacing(10);
    }
}
