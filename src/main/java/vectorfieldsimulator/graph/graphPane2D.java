package vectorfieldsimulator.graph;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import vectorfieldsimulator.propertyClasses.*;
import vectorfieldsimulator.window.windowContent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class graphPane2D {
    private List<Arrow> arrows = new ArrayList<>();
    private mousePosition mouseMovement;
    private Circle circle;
    private Axis axes;
    private Pane visualizerPane = new Pane();
    private GraphicsContext g;
    private functionData testFunc = new functionData(Color.RED, x -> x * x);

    public static double W, H;
    public static final double MENU_H = 10;
    public static final double PIXELS_PER_UNIT = 40;

    public graphPane2D(double W, double H, mousePosition mouseMovement, radioColor colorModel, functionValues vectorFunctions) {
        this.W = W;
        this.H = H;
        visualizerPane.setPrefSize(this.W, this.H);

        // set the GraphicContext
        Canvas canvas = new Canvas(this.W, this.H);
        g = canvas.getGraphicsContext2D();
        // offset to have the graph centered
        g.translate(-2*MENU_H, -2*MENU_H);
        g.setLineWidth(2.0);

        // populate the window with arrows
        for (int y = 0; y < H / 24; y++) {
            for (int x = 0; x < W / 50; x++) {

                var a = new Arrow(2.5, colorModel);
                a.setTranslateX(x * 50);
                a.setTranslateY(y * 24);

                arrows.add(a);
                visualizerPane.getChildren().add(a);
            }
        }

        circle = new Circle(10, Color.RED);
        axes = new Axis();
        visualizerPane.getChildren().add(circle);
        visualizerPane.getChildren().add(axes);
        visualizerPane.getChildren().add(canvas);

        // animation for live updates
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // here I will need to display the functions
                onUpdate(mouseMovement);
            }
        };
        timer.start();
    }

    public Pane getPane() {
        return this.visualizerPane;
    }

    private void onUpdate(mousePosition mouseMovement) {
        double mouseX = mouseMovement.getMouseX();
        double mouseY = mouseMovement.getMouseY();

        circle.setCenterX(mouseX);
        circle.setCenterY(mouseY);

        // draw functions
        g.clearRect(0, 0, W, H);
        for (int drawX = 0; drawX < W; drawX++) {
            g.setStroke(testFunc.color);

            // math in order to differentiate pixel values from math values: scaling and descaling
            double x = (drawX - W/2) / PIXELS_PER_UNIT;
            double y = testFunc.f.apply(x);
            double drawY = H - (y * PIXELS_PER_UNIT + H/2);

            if (!(testFunc.oldX == 0.0 && testFunc.oldY == 0.0))
                g.strokeLine(testFunc.oldX, testFunc.oldY, drawX, drawY);

            testFunc.oldX = drawX;
            testFunc.oldY = drawY;
        }
        testFunc.oldX = 0.0;
        testFunc.oldY = 0.0;

        // calculate the rotation angle for each arrow
        arrows.forEach(a -> {
            var vx = mouseX - a.getTranslateX();
            var vy = mouseY - a.getTranslateY();

            var angle = Math.toDegrees(Math.atan2(vy, vx));

            a.setRotate(angle);
        });
    }

    // class with information about the function
    private static class functionData {

        private Color color;
        private Function<Double, Double> f;
        // private String fString;

        private double oldX = 0.0;
        private double oldY = 0.0;

        functionData(Color color, Function<Double, Double> f) {
            this.color = color;
            this.f = f;
        }
    }

    // class that draws a line
    private static class Arrow extends Parent {

        Arrow(double scale, radioColor colorModel) {

            var lineTop = new Line(15 * scale, 5 * scale, 12.5 * scale, 2.5 * scale);
            var lineMid = new Line(0 * scale, 5 * scale, 15 * scale, 5 * scale);
            var lineBot = new Line(15 * scale, 5 * scale, 12.5 * scale, 7.5 * scale);

            // uses what it listens from menu
            colorModel.selectedColorProperty().addListener((obs, oldColor, newColor) -> {
                lineTop.setStroke(newColor);
                lineMid.setStroke(newColor);
                lineBot.setStroke(newColor);
            });

            // Set initial color
            lineTop.setStroke(colorModel.getSelectedColor());
            lineMid.setStroke(colorModel.getSelectedColor());
            lineBot.setStroke(colorModel.getSelectedColor());

            getChildren().addAll(lineTop, lineMid, lineBot);
        }
    }

    // class that creates X and Y axis
    private static class Axis extends Parent {

        Axis() {
            var lineX = new Line(0, H/2 - 2*MENU_H, W, H/2 - 2*MENU_H);
            var lineY = new Line(W/2 - 2*MENU_H, 0, W/2 - 2*MENU_H, H);

            getChildren().addAll(lineX, lineY);
        }
    }
}
