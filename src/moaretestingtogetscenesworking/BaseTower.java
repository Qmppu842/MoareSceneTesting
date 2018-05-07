package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;
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
    protected int isClicked;

    protected int range;
    private double attackTime;
    private double attackCollector = 0;
    protected int attackDamage;
    protected Color insideRange;
    protected Color outsideRange;
    protected ArrayList<Walker> targetsOnRange;

    public BaseTower(Point position, int size, Color outside, Color inside) {
        this.position = position;
        this.size = size;
        this.outside = outside;
        this.inside = inside;
        this.isClicked = -1;
    }

    public BaseTower(Point position, int size, Color outside, Color inside, int range, double attackTime, int attackDamage) {
        this.position = position;
        this.size = size;
        this.outside = outside;
        this.inside = inside;
        this.isClicked = -1;
        this.range = range;
        this.attackTime = attackTime;
//        this.attackCollector = 0;
        this.attackDamage = attackDamage;
        this.insideRange = Color.RED.deriveColor(1, 1, 1, 0.2);
        this.outsideRange = Color.BLACK;
        this.targetsOnRange = new ArrayList<>();
        midInit();
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public abstract void update(GraphicsContext gc);

    protected boolean isClickHere(Point click) {
        double aa = click.distance(position);
        boolean returnValue = false;
        if (aa < (size / 2) + 5) {
            returnValue = true;
            isClicked = 1;
        }
        return returnValue;
    }

    protected int isIsClicked() {
        return isClicked;
    }

    //TODO: add these to constructor
    private TreeSet<Walker> targetList2;
    private double attackReadyLimit;
    private double attackTime2;
    private double collectedAttack;

    private void midInit() {
        targetList2 = new TreeSet<>();
        attackReadyLimit = 5;
        attackTime2 = 1;
        collectedAttack = 0;
    }

    public boolean isReadyToAttack() {
        if (collectedAttack <= attackReadyLimit) {
            collectedAttack += attackTime2;
        }
        return collectedAttack > attackReadyLimit;
    }

    public void addTarget(Walker walker) {
        targetList2.add(walker);
    }

    public void removeTarget(Walker walker) {
        try {
            targetList2.remove(walker);
        } catch (Exception e) {
        }
    }

    public Walker attack() {
        try {
            Walker target = targetList2.first();
            target.dealDamageToThis(attackDamage);
            collectedAttack -= attackReadyLimit;
            if (!target.isAlive()) {
                targetList2.remove(target);
                return target;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public boolean isTargetOnRange2(Walker target) {
        double dist = position.distance(target.getCurrPoint()) + target.getSize() + 2;
        return dist <= range;
    }
}
