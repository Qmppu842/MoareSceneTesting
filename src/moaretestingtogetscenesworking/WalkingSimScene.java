package moaretestingtogetscenesworking;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
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
    private final ArrayList<Point> ROUTE;
    private ArrayList<Updatable> walkers;

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

        ROUTE = StaticThings.generateFirstTestRoute();
    }

//    private Robot robo;
    private int x;
    private int y;
    private PointerInfo info;

    public WalkingSimScene(SceneManager mgr, LogicLoader logic) {
        super(mgr);

        this.logic = logic;
        ROUTE = StaticThings.generateFirstTestRoute();
        walkers = new ArrayList<>();
        wall = new Canvas(1500, 900);
        gc = wall.getGraphicsContext2D();
        allThings.setCenter(wall);

        makeTowerList(); // this probably should be in first few lines of generateAnimator.
        AnimationTimer animator = generateAnimator();

        footer.getChildren().add(toggleWalkingBalls(animator));
        footer.getChildren().add(newWalker());
        footer.getChildren().add(dealDamageToCertain());

        clickedTowerIndex = -1;
        /*
         * This seems already super demanding... if it ends bottle necking, it
         * is probably then better implement layout that overlaps canvas and
         * then add clickable-shapes to it and position then correctly somehow...
         */
        wall.setOnMouseClicked((event) -> {
            System.out.println("moi?");
            info = MouseInfo.getPointerInfo();
            Point loc = info.getLocation();

            Bounds boundsInScreen = wall.localToScreen(wall.getBoundsInLocal());
            double wallX = 0;
            double wallY = 0;
            try {
                wallX = boundsInScreen.getMinX();
                wallY = boundsInScreen.getMinY();
            } catch (Exception e) {
            }
            Point moi = new Point((int) (loc.x - wallX), (int) (loc.y - wallY));
            isThereTowerUnderClick(moi);

//            System.out.println("location of mouse: " + loc);
//            System.out.println("corrected x: " + (loc.x - wallX));
//            System.out.println("corrected y: " + (loc.y - wallY));
//            System.out.println("wallX: " + wallX);
//            System.out.println("wallY: " + wallY);
        });

    }
    private int clickedTowerIndex;

    private void isThereTowerUnderClick(Point click) {
        for (int i = 0; i < towers.size(); i++) {
            boolean asdd = towers.get(i).isClickHere(click);
            if (asdd) {
                clickedTowerIndex = i;
                break;
            }
        }
    }

    private void drawRoute() {
        gc.clearRect(0, 0, wall.getWidth(), wall.getHeight());
        Paint woods = new Color(0, 1, 0, 0.2);
        gc.setFill(woods);
        gc.fillRect(0, 0, 1500, 900);
        Paint road = new Color(0.5, 0, 0.5, 0.7);
        gc.setStroke(road);
        ArrayList<Point> polku = ROUTE;
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
    private boolean isAnimationTimerRunning;

    protected Button toggleWalkingBalls(AnimationTimer animator) {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (isAnimationTimerRunning) {
                    animator.stop();
                    isAnimationTimerRunning = false;
                } else {
                    isAnimationTimerRunning = true;
                    animator.start();
                }
            }
        };

        return coreButtonMaker("Start walking.", action);
    }

    private Button newWalker() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                walkers.add(new Walker(-1, null, -1, -1, ROUTE, "" + walkers.size()));

//                footer.getChildren().add(dealDamageToCertain(walkers.size() - 1));
            }
        };

        return coreButtonMaker("New walker.", action);
    }

    private Button dealDamageToCertain() {
//        boolean isAlive = true;
//        Button btn = new Button();
//        btn.setText("Deal 10 dmge to walker#" + index);

        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
//            private boolean isAlive = true;

            @Override
            public void handle(ActionEvent event) {
                Random ran = new Random();
//                isAlive = 
                try {
                    dealDamageToCertainUnit(ran.nextInt(walkers.size()), 10);
                } catch (Exception e) {
                    System.out.println("No more walkers to damage. Great job! /s");
                }
//                String id = btn.getId();
//                if (!isAlive) {
////                    footer.getChildren().remove(btn);
////                    btn.setDisable(true);
////                    btn.setVisible(false);
////                    btn.set
//                }
            }
        };
//        btn.setOnAction(action);
        return coreButtonMaker("Deal 10 dmge to random walker", action);
    }

    private boolean dealDamageToCertainUnit(int index, int amount) {
        Walker toDeal = (Walker) walkers.get(index);
        boolean isAlive = toDeal.dealDamageToThis(amount);
        if (!isAlive) {
            walkers.remove(index);
        }
        return isAlive;
    }

    private AnimationTimer generateAnimator() {

        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                drawRoute();
                for (Updatable walker : walkers) {
                    walker.update(gc);
                }
                for (EmptyTowerPlace tower : towers) {
                    tower.update(gc);
                }
//                if (clickedTowerIndex != -1) {
//                    towers.
//                }
            }
        };

        return animator;
    }

    private ArrayList<EmptyTowerPlace> towers;
    private RouteTile road;

    private void makeTowerList() {
        towers = new ArrayList<>();
        road = new RouteTile(ROUTE, StaticThings.generateFirstTowerPlaces());
        for (Point towerPlace : road.getTowerPlaces()) {
            towers.add(new EmptyTowerPlace(towerPlace, 40, Color.OLIVE, Color.INDIGO));
        }

    }
}
