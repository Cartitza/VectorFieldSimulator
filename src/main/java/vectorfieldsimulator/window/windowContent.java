package vectorfieldsimulator.window;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class windowContent {
    private List<Arrow> arrows = new ArrayList<>();
    private double mouseX;
    private double mouseY;
    private Circle circle;

    private Scene scene;

    public windowContent() {
        scene = new Scene(createContent());
        scene.setOnMouseMoved(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });
        menuSample.addMenus(scene);
    }

    public Scene getScene() {
        return this.scene;
    }

    public Parent createContent() {
        // scene is a VBox with a Pane inside
        VBox root = new VBox();
        root.setPrefSize(1280, 720);
        HBox contentBox = new HBox();
        Pane visualizerPane = new Pane();
        visualizerPane.setPrefSize(1280, 720);

        // populate the window with arrows
        for (int y = 0; y < 720 / 24; y++) {
            for (int x = 0; x < 1280 / 50; x++) {

                var a = new Arrow(2.5);
                a.setTranslateX(x * 50);
                a.setTranslateY(y * 24);

                arrows.add(a);
                visualizerPane.getChildren().add(a);
            }
        }

        circle = new Circle(10, Color.RED);
        visualizerPane.getChildren().add(circle);
        // animation for live updates
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        contentBox.getChildren().add(visualizerPane);
        root.getChildren().add(contentBox);
        return root;
    }

    // real time update function
    private void onUpdate() {
        circle.setCenterX(mouseX);
        circle.setCenterY(mouseY);

        // calculate the rotation angle for each arrow
        arrows.forEach(a -> {
            var vx = mouseX - a.getTranslateX();
            var vy = mouseY - a.getTranslateY();

            var angle = Math.toDegrees(Math.atan2(vy, vx));

            a.setRotate(angle);
        });
    }

    // class that draws a line
    private static class Arrow extends Parent {

        Arrow(double scale) {

            var lineTop = new Line(15 * scale, 5 * scale, 12.5 * scale, 2.5 * scale);
            var lineMid = new Line(0 * scale, 5 * scale, 15 * scale, 5 * scale);
            var lineBot = new Line(15 * scale, 5 * scale, 12.5 * scale, 7.5 * scale);

            getChildren().addAll(lineTop, lineMid, lineBot);
        }
    }


}
