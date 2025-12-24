package vectorfieldsimulator.propertyClasses;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class mousePosition {
    private final DoubleProperty mouseX = new SimpleDoubleProperty(0);
    private final DoubleProperty mouseY = new SimpleDoubleProperty(0);

    public DoubleProperty mouseXProperty() {
        return mouseX;
    }

    public DoubleProperty mouseYProperty() {
        return mouseY;
    }

    public double getMouseX() {
        return mouseX.get();
    }

    public double getMouseY() {
        return mouseY.get();
    }

    public void setMouseX(double x) {
        mouseX.set(x);
    }

    public void setMouseY(double y) {
        mouseY.set(y);
    }

    public void setPosition(double x, double y) {
        mouseX.set(x);
        mouseY.set(y);
    }
}
