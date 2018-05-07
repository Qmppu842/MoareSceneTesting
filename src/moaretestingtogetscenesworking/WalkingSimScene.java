package moaretestingtogetscenesworking;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
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
    private ArrayList<Walker> walkers;

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
        goldInit();
        wall = new Canvas(1500, 900);
        gc = wall.getGraphicsContext2D();
        allThings.setCenter(wall);

        makeTowerList(); // this probably should be in first few lines of generateAnimator.
        AnimationTimer animator = generateAnimator();

        footer.getChildren().add(toggleWalkingBalls(animator));
        footer.getChildren().add(newWalker());
//        footer.getChildren().add(dealDamageToCertain());
        footer.getChildren().add(onlyBuildBasics());
        footer.getChildren().add(onlyBuildBeams());
        footer.getChildren().add(onlyBuildCannons());
        footer.getChildren().add(onlyBuildSnipers());
        footer.getChildren().add(onlyBuildSniperBeams());
        footer.getChildren().add(onlyBuildRandoms());

        /*
         * TODO: Yes implement this because now it is total insanity to try to
         * get hovering and other normal mouse events to even barely work. <br>
         * This seems already super demanding... if it ends bottle necking, it
         * is probably better implement layout that overlaps canvas and
         * then add clickable-shapes to it and position then correctly
         * somehow...
         */
//        clickedTowerIndex = -1;
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
            isThereEmptyTowerUnderClick(moi);

//            System.out.println("location of mouse: " + loc);
//            System.out.println("corrected x: " + (loc.x - wallX));
//            System.out.println("corrected y: " + (loc.y - wallY));
//            System.out.println("wallX: " + wallX);
//            System.out.println("wallY: " + wallY);
        });
        initTowerTypes();

    }
//    private int clickedTowerIndex;

    private void isThereTowerUnderClick(Point click) {
        for (int i = 0; i < towers.size(); i++) {
            boolean asdd = towers.get(i).isClickHere(click);
            if (asdd) {
//                clickedTowerIndex = i;
//tower.
                break;
            }
        }
    }

    private void isThereEmptyTowerUnderClick(Point click) {
        for (int i = 0; i < towerPlaces.size(); i++) {
            boolean asdd = towerPlaces.get(i).isClickHere(click);
            if (asdd) {
//                clickedTowerIndex = i;
//tower.
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
                Walker walk = new Walker(1, null, 1000, -1, ROUTE, "" + walkers.size());
                walkers.add(walk);
                Random ran = new Random();
                if (ran.nextDouble() > 0.8) {
                    walk.setSpeedScaleWithHP(true);
                }
//                footer.getChildren().add(dealDamageToCertain(walkers.size() - 1));
            }
        };

        return coreButtonMaker("New walker.", action);
    }

    private Button onlyBuildSnipers() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                towerMode = "Sniper";
            }
        };

        return coreButtonMaker("Build Snipers.", action);
    }

    private Button onlyBuildBasics() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                towerMode = "Basic";
            }
        };
        return coreButtonMaker("Build Basics.", action);
    }

    private Button onlyBuildCannons() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                towerMode = "Cannon";
            }
        };

        return coreButtonMaker("Build Cannons.", action);
    }

    private Button onlyBuildBeams() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                towerMode = "Beam";
            }
        };

        return coreButtonMaker("Build Beams.", action);
    }

    private Button onlyBuildSniperBeams() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                towerMode = "SniperBeam";
            }
        };

        return coreButtonMaker("Build SniperBeams.", action);
    }

    private Button onlyBuildRandoms() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                towerMode = "Random";
            }
        };

        return coreButtonMaker("Build Random towers?.", action);
    }

    @Deprecated
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
        ArrayList<Walker> toBeRemoved = new ArrayList<>();
        ArrayList<FirstTestTower> towersReadyToAttack = new ArrayList<>();
        ArrayList<FirstTestTower> notReadyToAttackAnyMore = new ArrayList<>();
        ArrayList<EmptyTowerPlace> toBeRemovedTowerPlaces = new ArrayList<>();
        Random ran = new Random();
        AnimationTimer animator = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mineGold();
                drawRoute();
                for (Walker walker : walkers) {
                    walker.update(gc);
                    if (!walker.isAlive()) {
                        toBeRemoved.add(walker);
                    }

                }
                for (EmptyTowerPlace towerPlace : towerPlaces) {
                    if (towerPlace.isClicked != -1 && wannabeGold >= 25) {

//                        towers.add(new FirstTestTower(towerPlace.position, 40, Color.OLIVE, Color.INDIGO, 150, 1.3, 2));
                        FirstTestTower tow = null;
                        switch (towerMode) {
                            case "Random":
                                tow = thingMaybe(towerTypes.get(ran.nextInt(towerTypes.size())));
                                break;
                            default:
                                tow = thingMaybe(towerMode);
                                break;
                        }

                        tow.setPosition(towerPlace.position);
                        towers.add(tow);
                        toBeRemovedTowerPlaces.add(towerPlace);
                        wannabeGold -= 25;
                    } else if (towerPlace.isClicked != -1 && wannabeGold < 25) {
                        System.out.println("Not enough gold!!");
                        towerPlace.isClicked = -1;
                    }
                    towerPlace.update(gc);

                }

                for (EmptyTowerPlace toBeRemovedTowerPlace : toBeRemovedTowerPlaces) {
                    towerPlaces.remove(toBeRemovedTowerPlace);
                }
                for (FirstTestTower tower : towers) {
                    boolean attackReadyness = tower.isReadyToAttack();
                    if (attackReadyness) {
                        towersReadyToAttack.add(tower);
                    }
                    for (Walker walker : walkers) {
                        boolean asd = tower.isTargetOnRange2(walker);
                        if (asd) {
                            tower.addTarget(walker);
                        } else {
                            tower.removeTarget(walker);
                        }
                    }

                    tower.update(gc);
                }

                for (FirstTestTower baseTower : towersReadyToAttack) {
                    Walker target = baseTower.attack();
                    notReadyToAttackAnyMore.add(baseTower);
                    if (target != null) {
                        toBeRemoved.add(target);
                    }
                }
                for (FirstTestTower firstTestTower : notReadyToAttackAnyMore) {
                    towersReadyToAttack.remove(firstTestTower);
                }

                for (Walker walker : toBeRemoved) {
                    walkers.remove(walker);
                    if (!walkerBountyBoard.contains(walker)) {
                        walkerBountyBoard.add(walker);
                        wannabeGold += 5;
                    }
                }

            }
        };

        return animator;
    }

    private ArrayList<Walker> walkerBountyBoard;

    private ArrayList<FirstTestTower> towers;
    private RouteTile road;
    private ArrayList<EmptyTowerPlace> towerPlaces;

    private void makeTowerList() {
        towers = new ArrayList<>();
        towerPlaces = new ArrayList<>();
        walkerBountyBoard = new ArrayList<>();
        road = new RouteTile(ROUTE, StaticThings.generateFirstTowerPlaces());
        for (Point towerPlace : road.getTowerPlaces()) {
            towerPlaces.add(new EmptyTowerPlace(towerPlace, 40, Color.OLIVE, Color.INDIGO));
//            towers.add(new FirstTestTower(towerPlace, 40, Color.OLIVE, Color.INDIGO, 150, 5, 25));
        }
    }

    private int wannabeGold;
    private double goldCollector;
    private double goldFindLimit;
    private double goldRate;

    private void goldInit() {
        wannabeGold = 25;
        goldCollector = 0;
        goldFindLimit = 1000;
        goldRate = 10;
    }

    private void mineGold() {
        goldCollector += goldRate;
        if (goldCollector > goldFindLimit) {
            wannabeGold++;
            goldCollector -= goldFindLimit;
            System.out.println("Goldd!!! " + wannabeGold);
        }
    }

    private ArrayList<String> towerTypes;
    private String towerMode;

    private void initTowerTypes() {
        towerMode = "Random";
        towerTypes = new ArrayList<>();
//        DifferentTowers basic = DifferentTowers.BASIC;
//         DifferentTowers beam = DifferentTowers.BEAM;
//          DifferentTowers cannon = DifferentTowers.CANNON;
//           DifferentTowers sniper = DifferentTowers.SNIPER;
//            DifferentTowers sb = DifferentTowers.SNIPERBEAM;
//        towerTypes.add(basic.getTower());
//        towerTypes.add(beam.getTower());
//        towerTypes.add(cannon.getTower());
//        towerTypes.add(sniper.getTower());
//        towerTypes.add(sb.getTower());
//        towerTypes.add(thingMaybe("Basic"));
//        towerTypes.add(thingMaybe("Beam"));
//        towerTypes.add(thingMaybe("Cannon"));
//        towerTypes.add(thingMaybe("Sniper"));
//        towerTypes.add(thingMaybe("SniperBeam"));

        towerTypes.add("Basic");
        towerTypes.add("Beam");
        towerTypes.add("Cannon");
        towerTypes.add("Sniper");
        towerTypes.add("SniperBeam");
    }

    private FirstTestTower thingMaybe(String moi) {
//        FirstTestTower builder = null;
        int size = 40;
        int attackTime = -1;
        int attackDamage = -1;
        int range = -1;
        int collectedAttack = -1;
        Color outside = Color.OLIVE;
        Color inside = Color.INDIGO;
        switch (moi) {
            case "Beam":
                attackTime = 65;
                attackDamage = 5;
                range = 200;
                collectedAttack = 100;
                inside = Color.BROWN;
                break;
            case "Sniper":
                attackTime = 10;
                attackDamage = 300;
                range = 465;
                collectedAttack = 1500;
                inside = Color.ALICEBLUE;
                break;
            case "SniperBeam":
                attackTime = 50;
                attackDamage = 7;
                range = 400;
                collectedAttack = 110;
                inside = Color.BROWN.interpolate(Color.ALICEBLUE, 0.5);
                break;
            case "Cannon":
                attackTime = 1;
                attackDamage = 700;
                range = 160;
                collectedAttack = 200;
                inside = Color.DEEPPINK;
                break;
            case "Low":
                attackTime = 10;
                attackDamage = 15;
                range = 150;
                collectedAttack = 100;

                inside = Color.CORNSILK;
                break;
            case "High":
                attackTime = 5;
                attackDamage = 55;
                range = 175;
                collectedAttack = 100;
                inside = Color.AZURE;
                break;

            case "Basic":
            default:
                attackTime = 5;
                attackDamage = 90;
                range = 175;
                collectedAttack = 150;
                break;
        }

        return new FirstTestTower(size, outside, inside, range, attackDamage, attackTime, collectedAttack);
    }
}
