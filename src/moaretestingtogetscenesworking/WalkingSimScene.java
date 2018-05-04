/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class WalkingSimScene extends BaseScene {

    private LogicLoader logic;

    private Canvas wall;
    private GraphicsContext gc;

    public WalkingSimScene(SceneManager mgr, String buttonText) {
        super(mgr, buttonText);
        setNameOfScene(this.getClass().toString());
        moim = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("$25, and  that's  my last offer");
                mgr.setScene(1);
            }
        };
    }

    public WalkingSimScene(SceneManager mgr, LogicLoader logic) {
        super(mgr);

        this.logic = logic;
        route=StaticThings.generateFirstTestRoute();
        wall = new Canvas(1500, 900);
        gc = wall.getGraphicsContext2D();
        allThings.setCenter(wall);
//        footer.getChildren().add(generateNewRoute());
        AnimationTimer animator = generateAnimator();
        footer.getChildren().add(startWalkingBalls(animator));
        footer.getChildren().add(resetWalker());

    }

//    protected Button generateNewRoute() {
//        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                logic.makeNewNonTable();
//                route = logic.getNonTableRoute();
//                drawRoute();
//            }
//        };
//
//        return coreButtonMaker("Generate new Route.", action);
//    }

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
//                logic.makeNewNonTable();
//                drawRoute();
                generateAnimator().start();
//                animator.start();
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
                speedCollector = 0;
            }
        };

        return coreButtonMaker("Reset walker.", action);
    }

//    private Circle generateCircle() {
//                return circle;
//    }
    private Point currPoint;
    private ArrayList<Point> route;
    private double speed = 20.333;
    private double speedCollector = 0;
    private int nextIndex;

    private AnimationTimer generateAnimator() {
        try {
            currPoint = route.get(0);

        } catch (Exception e) {
            route = StaticThings.generateFirstTestRoute();
            currPoint = route.get(0);
        }
//        Circle circle = new Circle();
//        circle.setCenterX(currPoint.x);
//        circle.setCenterY(currPoint.y);
//        circle.setRadius(40);
//        circle.setFill(Color.BLUE);
//        wall.getChildren().add(circle);
//
//        wall.    

        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (currPoint == null) {
                    try {
                        currPoint = route.get(0);

                    } catch (Exception e) {
                        route = StaticThings.generateFirstTestRoute();
                        currPoint = route.get(0);
                    }
                    nextIndex = 1;
                }
                if (nextIndex >= route.size()) {
                    System.out.println("speedCollector: " + speedCollector);
                    this.stop();
                } else {
                    Point next = route.get(nextIndex);
                    double asd = currPoint.distance(next);
//                int deltaX = next.x - currPoint.x;
//                int deltaY = next.y - currPoint.y;
//                int deltaXSquare = deltaX * deltaX;
//                int deltaYSquare = deltaY * deltaY;
//                int sqSum = deltaXSquare + deltaYSquare;
//                double dist = Math.sqrt(sqSum * 1.0);
//                double ratio = deltaX / (deltaY * 1.0);
//                Point middle = new Point();
                    double xMult = 1;
                    double yMult = 1;
                    if (currPoint.x > next.x) {
                        xMult = -1;
                    }
                    if (currPoint.y > next.y) {
                        yMult = -1;
                    }
                    speedCollector += speed;
                    if (asd <= speedCollector) {
                        currPoint = next;
                        speedCollector += speedCollector - asd;
                    } else {
                        currPoint.x += speedCollector * xMult;
                        currPoint.y += speedCollector * yMult;
                    }
                    if (speedCollector > 1) {
                        speedCollector %= 1;
                    }
                    if (currPoint == next) {
                        nextIndex++;
                    }
//                    System.out.println("currrent point: " + currPoint);
//                    System.out.println("next point:" + next);

//                    circle.setCenterX(currPoint.x);
//                    circle.setCenterY(currPoint.y);
                    drawRoute();
                    gc.setFill(Color.BLACK);
                    gc.fillOval(currPoint.x - 10, currPoint.y - 10, 20, 20);
                }
            }
        };

        return animator;
    }
}
