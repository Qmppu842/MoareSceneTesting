/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Qmppu842
 */
public class FirstTestTower extends BaseTower {

    private double[] xPoints;
    private double[] yPoints;

    public FirstTestTower(Point position, int size, Color outside, Color inside, int range, double attackTime, int attackDamage) {
        super(position, size, outside, inside, range, attackTime, attackDamage);
        initShape();

    }

//    public FirstTestTower(int size, Color outside, Color inside, int range, int attackDamage, double attackTime2, double collectedAttack) {
//        super(size, outside, inside, range, attackDamage, attackTime2, collectedAttack);
////        initShape();
//        shaped = false;
//    }
    public FirstTestTower(int size, Color outside, Color inside, int range, int attackDamage, double attackTime2, double attackReadyLimit) {
        super(size, outside, inside, range, attackDamage, attackTime2, attackReadyLimit);
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        initShape();
//        shaped = true;
    }
//    private boolean shaped;

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
//        if (shaped) {

        if (isClicked != -1) {
            gc.setFill(insideRange);
            gc.fillOval(position.x - (range / 1), position.y - (range / 1), range * 2, range * 2);
            isClicked++;
            if (isClicked > 150) {
                isClicked = -1;
            }
        }
        gc.setFill(inside);
        gc.fillPolygon(xPoints, yPoints, 16);
    }
//    }

}
