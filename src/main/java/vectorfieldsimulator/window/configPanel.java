package vectorfieldsimulator.window;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.textfield.CustomTextField;
import vectorfieldsimulator.propertyClasses.*;


public class configPanel extends VBox {

    private radioColor colorModel;
    private functionValues vectorFunctions;

    public configPanel(radioColor colorModel, functionValues vectorFunctions) {
        this.colorModel = colorModel;
        this.vectorFunctions = vectorFunctions;

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

        this.setPrefWidth(300); // Fixed width
        this.setMinWidth(300);  // Prevents it from shrinking
        this.setMaxWidth(300);  // Prevents it from growing
        this.setStyle("-fx-background-color: #f0f0f0;"); // Light gray background
        this.setPadding(new Insets(15)); // Padding around content
        this.getChildren().addAll(redBtn, blueBtn, greenBtn);

        // TextFlow for labels to simulate subscript
        var labelX =  new TextFlow();
        var labelY =  new TextFlow();
        var start = new Text("F");
        var smallY = new Text("Y");
        smallY.setTranslateY(start.getFont().getSize() * 0.3);
        var end = new Text("<x, y>: ");

        labelY.getChildren().addAll(start, smallY, end);

        start = new Text("F");
        var smallX = new Text("X");
        smallX.setTranslateY(start.getFont().getSize() * 0.3);
        end = new Text("<x, y>: ");

        labelX.getChildren().addAll(start, smallX, end);

        var XFunction = new TextField();
        var YFunction = new TextField();

        // set action to get the inputed functions
        String fx, fy;
        XFunction.setOnAction(e -> vectorFunctions.setFx(XFunction.getText()));
        YFunction.setOnAction(e -> vectorFunctions.setFy(YFunction.getText()));

        this.getChildren().addAll(labelX, XFunction, labelY, YFunction);
        this.setSpacing(10);
    }
}
