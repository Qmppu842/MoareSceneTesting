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
public class Walker implements Updatable {

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
     */
    public Walker(double speed, Paint color, int maxHP, int size, ArrayList<Point> ROUTE) {
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
    }
    private Point currPoint = null;

    @Override
    public void update(GraphicsContext gc) {

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
            x = currPoint.x;
            y = currPoint.y;
        }

    }
}
