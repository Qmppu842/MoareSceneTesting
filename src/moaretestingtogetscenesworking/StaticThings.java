/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Qmppu842
 */
public class StaticThings {

    public static ArrayList<Point> generateFirstTestRoute() {
        ArrayList<Point> polku = new ArrayList<>();
        polku.add(new Point(0, 50));
        polku.add(new Point(50, 50));
        polku.add(new Point(50, 850));
        polku.add(new Point(1050, 850));
        polku.add(new Point(1050, 150));
//        5
        polku.add(new Point(250, 150));
        polku.add(new Point(250, 650));
        polku.add(new Point(850, 650));
        polku.add(new Point(850, 350));
        polku.add(new Point(450, 350));
//        10
        polku.add(new Point(450, 450));
        polku.add(new Point(750, 450));
        polku.add(new Point(750, 550));
        polku.add(new Point(350, 550));
        polku.add(new Point(350, 250));
//        15
        polku.add(new Point(950, 250));
        polku.add(new Point(950, 750));
        polku.add(new Point(150, 750));
        polku.add(new Point(150, 50));
        polku.add(new Point(1450, 50));
//        20
        polku.add(new Point(1450, 350));
        polku.add(new Point(1150, 350));
        polku.add(new Point(1150, 150));
        polku.add(new Point(1350, 150));
        polku.add(new Point(1350, 850));
//        25
        polku.add(new Point(1150, 850));
        polku.add(new Point(1150, 450));
        polku.add(new Point(1450, 450));
        polku.add(new Point(1450, 550));
        polku.add(new Point(1250, 550));
//        30
        polku.add(new Point(1250, 750));
        polku.add(new Point(1450, 750));
        polku.add(new Point(1450, 850));
        polku.add(new Point(1500, 850));

        return polku;
    }
}
