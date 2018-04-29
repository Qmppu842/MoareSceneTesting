/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Qmppu842
 */
public class StartEndPointArrayList {

    private Point alku;
    private Point loppu;
    private ArrayList<Point> route;
    private Random fissio;
    private int inHowManySteps;

    public StartEndPointArrayList(Point alku, Point loppu) {
        this.alku = alku;
        this.loppu = loppu;
        route = new ArrayList<>();
        fissio = new Random();
        inHowManySteps = fissio.nextInt(1000);
    }

    public void generateNewRoute() {
        route = new ArrayList<>();
        route.add(alku);
//        if (alku.x > loppu.x) {
//            generateDecXRoute();
//        } else if (alku.x < loppu.x) {
        generateIncXRoute();
//        } else if (alku.y > loppu.y) {
//            generateDecYRoute();
//        } else {
//            generateIncYRoute();
//        }
    }

    private void generateIncXRoute() {
        for (int i = 0; i < inHowManySteps; i++) {
            Point currStep = route.get(i);
            int x = 0;
            int y = 0;
            if (fissio.nextDouble() > 0.6) {
                x = 10;
                y = fissio.nextInt(25)-12;
            } else {
                y = 10; // (int) alku.distance(loppu) / inHowManySteps;
                x = fissio.nextInt(25)-12;
            }
            Point next = new Point((currStep.x + x)%1500, (currStep.y + y)%900);
            route.add(next);
        }
        route.add(loppu);
    }

    private void generateDecXRoute() {
    }

    private void generateIncYRoute() {
    }

    private void generateDecYRoute() {
    }

    public ArrayList<Point> getRoute() {
        return route;
    }

}
