package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Qmppu842
 */
public class Walker implements Updatable, Comparable<Walker> {

    private double speed;
    private double speedCollector;
    private int x;
    private int y;
    private Paint color;
    private int maxHP;
    private int size;
    private final ArrayList<Point> ROUTE;
    private int currentHP;
    private int nextIndex;
    private final int ID;
    private Point currPoint = null;

    /**
     *
     * If you want to use default value for any parameter put -1. <br>
     * For color use null instead of -1
     *
     * @param speed default = 5
     * @param color default = Black
     * @param maxHP default = 100
     * @param size default = 30
     * @param ROUTE Must give ROUTE!
     * @param id Unique identifier
     */
    public Walker(double speed, Paint color, int maxHP, int size, ArrayList<Point> ROUTE, String id) {
        if (speed <= 0) {
            this.speed = 5;
        } else {
            this.speed = speed;
        }
        speedCollector = 0;
        if (color == null) {
            this.color = Color.BLACK;
        } else {
            this.color = color;
        }
        if (maxHP == -1) {
            this.maxHP = 100;
        } else {
            this.maxHP = maxHP;
        }
        if (size == -1) {
            this.size = 30;
        } else {
            this.size = size;
        }
        currentHP = this.maxHP;
        this.ROUTE = ROUTE;
        this.ID = id.hashCode();
        scaleInitor();
    }

    public int getID() {
        return ID;
    }

    @Override
    public void update(GraphicsContext gc) {
        if (speedScaleWithHP) {
            scaleSpeedToCurrentHP();
        }
        if (currPoint == null) {
            currPoint = new Point(ROUTE.get(0));
            nextIndex = 1;
        }
        if (nextIndex >= ROUTE.size()) {
        } else {
            Point next = new Point(ROUTE.get(nextIndex));
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
                if (next.x != currPoint.x) {
                    currPoint.x += speedCollector * xMult;
                }
                if (next.y != currPoint.y) {
                    currPoint.y += speedCollector * yMult;
                }
            }
            if (speedCollector > 1) {
                speedCollector %= 1;
            }
            if (currPoint == next) {
                nextIndex++;
            }
            gc.setFill(color);
            gc.fillOval(currPoint.x - (size / 2), currPoint.y - (size / 2), size, size);
            if (currentHP != maxHP) {
                drawHPBar(gc);
            }
            x = currPoint.x;
            y = currPoint.y;
        }

    }

    /**
     *
     * @param amount
     * @return true if alive, false if dead
     */
    protected boolean dealDamageToThis(int amount) {
        currentHP -= amount;
        return currentHP >= 0;
    }

    //if currentPoint causes problems just wrap it with Point holder = new Point(currentPoint);
    private void drawHPBar(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(currPoint.x - (size / 2) - 1, currPoint.y - (size / 2) - 10 - 1, size + 2, 7);
        gc.setFill(Color.CRIMSON);
        double prossentHP = (currentHP / (maxHP / 100.0)) / 100;
        double scaledSize = Math.max(size * prossentHP, 1);
        if (currentHP == 0) {
            scaledSize = 0;
        }
        gc.fillRect(currPoint.x - (size / 2), currPoint.y - (size / 2) - 10, scaledSize, 5);

    }

    public Point getCurrPoint() {
        return currPoint;
    }

    public int getSize() {
        return size;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    @Override
    public int compareTo(Walker t) {
        return this.ID - t.ID;
    }

    private boolean scaleSizeWithHp;
    private boolean speedScaleWithHP;
    private double speedOriginal;

    private void scaleInitor() {
        scaleSizeWithHp = false;
        speedScaleWithHP = false;
        speedOriginal = speed;
    }

    public void setScaleSizeWithHp(boolean scaleSizeWithHp) {
        this.scaleSizeWithHp = scaleSizeWithHp;
        if (scaleSizeWithHp) {
            double scale = maxHP / 100;
            size = (int) Math.min(150, (size * scale));
        }
    }

    public void setSpeedScaleWithHP(boolean speedScaleWithHP) {
        this.speedScaleWithHP = speedScaleWithHP;
    }

    private void scaleSpeedToCurrentHP() {
        if (currentHP < maxHP * 0.15) {
            speed = speedOriginal * 2.5;
        } else if (currentHP < maxHP * 0.25) {
            speed = speedOriginal * 1.75;
        } else if (currentHP < maxHP * 0.5) {
            speed = speedOriginal * 1.5;
        } else if (currentHP < maxHP * 0.75) {
            speed = speedOriginal * 1.25;
        }
    }

//    private boolean isCurrHPLessThanProsMaxHP(){
//    
//    }
}
