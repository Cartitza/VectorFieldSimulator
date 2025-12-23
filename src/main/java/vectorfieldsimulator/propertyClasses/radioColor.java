package vectorfieldsimulator.propertyClasses;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class radioColor {
    private final ObjectProperty<Color> selectedColor = new SimpleObjectProperty<>(Color.BLACK);

    public ObjectProperty<Color> selectedColorProperty() {
        return selectedColor;
    }

    public Color getSelectedColor() {
        return selectedColor.get();
    }

    public void setSelectedColor(Color color) {
        selectedColor.set(color);
    }
}
