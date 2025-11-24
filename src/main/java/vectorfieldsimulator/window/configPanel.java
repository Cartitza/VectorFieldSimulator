package vectorfieldsimulator.window;

import javafx.scene.control.TextArea;

public class configPanel {

    public TextArea controlsPane;

    public configPanel() {
        controlsPane = new TextArea();
    }

    public TextArea getControls() {
        return this.controlsPane;
    }
}
