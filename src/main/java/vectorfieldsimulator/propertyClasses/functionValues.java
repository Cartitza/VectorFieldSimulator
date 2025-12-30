package vectorfieldsimulator.propertyClasses;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class functionValues {
    private final StringProperty fx = new SimpleStringProperty();
    private final StringProperty fy = new SimpleStringProperty();

    public StringProperty fxProperty() {
        return fx;
    }

    public StringProperty fyProperty() {
        return fy;
    }

    public String getFx() {
        return fx.get();
    }

    public String getFy() {
        return fy.get();
    }

    public void setFx(String function) {
        fx.set(function);
    }

    public void setFy(String function) {
        fy.set(function);
    }
}
