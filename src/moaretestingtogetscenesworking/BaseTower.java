package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Qmppu842
 */
public abstract class BaseTower implements Updatable {

    protected Point position;
    protected int size;
    protected Color outside;
    protected Color inside;
    protected boolean isClicked;

    protected int range;
    protected double attackTime;
    protected double attackCollector = 0;
    protected int attackDamage;
    protected Color insideRange;
    protected Color outsideRange;
    protected PriorityQueue<Walker> targetsOnRange;

    public BaseTower(Point position, int size, Color outside, Color inside) {
        this.position = position;
        this.size = size;
        this.outside = outside;
        this.inside = inside;
        this.isClicked = false;
    }

    public BaseTower(Point position, int size, Color outside, Color inside, int range, double attackTime, int attackDamage) {
        this.position = position;
        this.size = size;
        this.outside = outside;
        this.inside = inside;
        this.isClicked = false;
        this.range = range;
        this.attackTime = attackTime;
//        this.attackCollector = 0;
        this.attackDamage = attackDamage;
        this.insideRange = Color.RED.deriveColor(1, 1, 1, 0.2);
        this.outsideRange = Color.BLACK;
        this.targetsOnRange = new PriorityQueue<>();
    }

    @Override
    public abstract void update(GraphicsContext gc);

    protected boolean isClickHere(Point click) {
        double aa = click.distance(position);
        boolean returnValue = false;
        if (aa < (size / 2) + 5) {
            returnValue = true;
            isClicked = true;
        }
        return returnValue;
    }

    protected boolean isIsClicked() {
        return isClicked;
    }

    /**
     * TODO: adding targets in front of queue (somehow)
     *
     * @param target
     * @return is target on range? Yes|No
     */
    protected boolean isTargetOnRange(Walker target) {
        double dist = position.distance(target.getCurrPoint()) - target.getSize() - 2;
        boolean isTargetOnRange = false;
        if (dist <= range) {
            isTargetOnRange = true;
            if (!targetsOnRange.contains(target)) {
                targetsOnRange.add(target);
            }
//            System.out.println("Enemy is on range!");
//            doAttackTime();
        }
        return isTargetOnRange;
    }

//    protected void removeTargetFromQueue(Walker target) {
//        targetsOnRange.remove(target);
//    }
    private double attackCollectorLimit = 5;
    private ArrayList<Integer> deadOnStart = new ArrayList<>();

    /**
     * TODO: rename it to something more sensible
     *
     */
    protected void doAttackTime() {
//        double holder = attackCollector;
//        holder += attackTime;
        attackCollector += attackTime;
//        System.out.println("Here?");
        System.out.println("attCol:" + attackCollector);
        if (attackCollector >= 5) {
//            System.out.println("Nope");
            if (targetsOnRange.size() > 0) {
//                System.out.println("Will you ");
                boolean isAlive = targetsOnRange.peek().dealDamageToThis(attackDamage);
                if (!isAlive) {
                    targetsOnRange.remove();
//                    System.out.println("DIE!");
                }
//                attackCollector %= attackCollectorLimit;

            } else {
//                System.out.println("Bombs!");
//                attackCollector = attackCollectorLimit - attackTime;
            }
        }
    }

    protected void doAttackTimeStupid() {
//        double holder = attackCollector;
//        holder += attackTime;
//        attackCollector += attackTime;
        deadOnStart.add(1);
//        System.out.println("Here?");
//        System.out.println("attCol:" + attackCollector);
        System.out.println("WTF?");
        if (deadOnStart.size() > 5) {
//            System.out.println("Nope");
            if (targetsOnRange.size() > 0) {
//                System.out.println("Will you ");
                boolean isAlive = targetsOnRange.peek().dealDamageToThis(attackDamage);
                if (!isAlive) {
                    targetsOnRange.remove();
//                    System.out.println("DIE!");
                }
//                attackCollector %= attackCollectorLimit;
                deadOnStart = new ArrayList<>();

            } else {
                while (deadOnStart.size() >= 5) {
                    deadOnStart.remove(deadOnStart.size() - 1);
                }
//                System.out.println("Bombs!");
//                attackCollector = attackCollectorLimit - attackTime;
            }
        }
    }
}
