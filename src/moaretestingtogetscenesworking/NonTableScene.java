package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Qmppu842
 */
public class NonTableScene extends BaseScene {

    private LogicLoader logic;

    private Canvas wall;
    private GraphicsContext gc;

    public NonTableScene(SceneManager mgr, LogicLoader logic) {
        super(mgr);

        this.logic = logic;
        wall = new Canvas(1500, 900);
        gc = wall.getGraphicsContext2D();
        allThings.setCenter(wall);
        footer.getChildren().add(generateNewRoute());
        AnimationTimer animator = generateAnimator();
        footer.getChildren().add(startWalkingBalls(animator));
        footer.getChildren().add(resetWalker());

    }

    public NonTableScene(SceneManager mgr, String buttonText) {
        super(mgr, buttonText);
        setNameOfScene(this.getClass().toString());
        moim = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRUE MADNESS");
                mgr.setScene(1);
            }
        };
    }

    protected Button generateNewRoute() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logic.makeNewNonTable();
                route = logic.getNonTableRoute();
                drawRoute();
            }
        };

        return coreButtonMaker("Generate new Route.", action);
    }

    private void drawRoute() {
        gc.clearRect(0, 0, wall.getWidth(), wall.getHeight());
        Paint woods = new Color(0, 1, 0, 0.2);
        gc.setFill(woods);
        gc.fillRect(0, 0, 1500, 900);
        Paint road = new Color(0.5, 0, 0.5, 0.7);
        gc.setStroke(road);
        ArrayList<Point> polku = route;
        for (int i = 1; i < polku.size(); i++) {
            gc.strokeLine(polku.get(i - 1).x, polku.get(i - 1).y, polku.get(i).x, polku.get(i).y);
        }
    }

    protected Button startWalkingBalls(AnimationTimer animator) {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                generateAnimator().start();
            }
        };

        return coreButtonMaker("Start walking.", action);
    }

    protected Button resetWalker() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currPoint = null;
                nextIndex = 1;

            }
        };

        return coreButtonMaker("Reset walker.", action);
    }

    private Point currPoint;
    private ArrayList<Point> route;
    private int speed = 5;
    private int nextIndex;

    private AnimationTimer generateAnimator() {
        try {
            currPoint = route.get(0);

        } catch (Exception e) {
            logic.makeNewNonTable();
            route = logic.getNonTableRoute();
            currPoint = route.get(0);
        }

        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (currPoint == null) {
                    try {
                        currPoint = route.get(0);

                    } catch (Exception e) {
                        logic.makeNewNonTable();
                        route = logic.getNonTableRoute();
                        currPoint = route.get(0);
                    }
                    nextIndex = 1;
                }
                if (nextIndex >= route.size()) {
                    this.stop();
                } else {
                    Point next = route.get(nextIndex);
                    double asd = currPoint.distance(next);
                    int xMult = 1;
                    int yMult = 1;
                    if (currPoint.x > next.x) {
                        xMult = -1;
                    }
                    if (currPoint.y > next.y) {
                        yMult = -1;
                    }

                    if (asd <= speed) {
                        currPoint = next;
                    } else {
                        currPoint.x += speed * xMult;
                        currPoint.y += speed * yMult;
                    }
                    if (currPoint == next) {
                        nextIndex++;
                    }
                    drawRoute();
                    gc.fillOval(currPoint.x - 10, currPoint.y - 10, 20, 20);
                }
            }
        };

        return animator;
    }

}
