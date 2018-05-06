/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.TreeSet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Qmppu842
 */
public class FirstTestTower extends BaseTower {

    private double[] xPoints;
    private double[] yPoints;
    private double numm;

    public FirstTestTower(Point position, int size, Color outside, Color inside, int range, double attackTime, int attackDamage) {
        super(position, size, outside, inside, range, attackTime, attackDamage);
        initShape();
//        numm = super.attackCollector;
        numm = 0;
        midInit();

    }

//    public FirstTestTower(Point position, int size, Color outside, Color inside) {
//        super(position, size, outside, inside);
//        initShape();
//    }
    private void initShape() {
        double half = size / 2.0;
        double quater = size / 4.0;
        double eigth = size / 8.0;
        int x = position.x;
        int y = position.y;
        xPoints = new double[16];
        yPoints = new double[16];

        xPoints[0] = x - half;
        xPoints[1] = x - quater;
        xPoints[2] = x - half;
        xPoints[3] = x - half;
        xPoints[4] = x - quater;
        xPoints[5] = x - quater;
        xPoints[6] = x - eigth;
        xPoints[7] = x - eigth;
        xPoints[8] = x + eigth;
        xPoints[9] = x + eigth;
        xPoints[10] = x + quater;
        xPoints[11] = x + quater;
        xPoints[12] = x + half;
        xPoints[13] = x + half;
        xPoints[14] = x + quater;
        xPoints[15] = x + half;

        yPoints[0] = y + half;
        yPoints[1] = y - quater;
        yPoints[2] = y - quater;
        yPoints[3] = y - half;
        yPoints[4] = y - half;
        yPoints[5] = y - quater - eigth;
        yPoints[6] = y - quater - eigth;
        yPoints[7] = y - half;
        yPoints[8] = y - half;
        yPoints[9] = y - quater - eigth;
        yPoints[10] = y - quater - eigth;
        yPoints[11] = y - half;
        yPoints[12] = y - half;
        yPoints[13] = y - quater;
        yPoints[14] = y - quater;
        yPoints[15] = y + half;
    }

    @Override
    public void update(GraphicsContext gc) {
        gc.setFill(insideRange);
        gc.fillOval(position.x - (range / 1), position.y - (range / 1), range * 2, range * 2);
        gc.setFill(inside);
        gc.fillPolygon(xPoints, yPoints, 16);
//        150
//        doAttackTime();
//        doAttackTimeStupid();
        System.out.println("WTF?");
        super.update(gc);
//        gc.strokeOval(size, size, size, size);
    }

//    @Override
//     protected void doAttackTime() {
////        double holder = attackCollector;
////        holder += attackTime;
//        attackCollector += attackTime;
////        System.out.println("Here?");
//        System.out.println("attCol:" + attackCollector);
//        if (attackCollector >= 5) {
////            System.out.println("Nope");
//            if (targetsOnRange.size() > 0) {
////                System.out.println("Will you ");
//                boolean isAlive = targetsOnRange.peek().dealDamageToThis(attackDamage);
//                if (!isAlive) {
//                    targetsOnRange.remove();
////                    System.out.println("DIE!");
//                }
////                attackCollector %= attackCollectorLimit;
//
//            } else {
////                System.out.println("Bombs!");
////                attackCollector = attackCollectorLimit - attackTime;
//            }
//        }
//    }
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
        collectedAttack += attackTime2;
        if (collectedAttack > attackReadyLimit) {
//            collectedAttack %= attackReadyLimit;
            return true;
        }
        return false;
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
            System.out.println("No targets avaible.");
        }
        return null;
    }

//    public Walker getCurrentTarget(){
//        return targetList2.first();
//    }
//    
    public boolean isTargetOnRange2(Walker target) {
        double dist = position.distance(target.getCurrPoint()) + target.getSize() + 2;
        return dist <= range;
    }
}
