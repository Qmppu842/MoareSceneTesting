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
        inHowManySteps = fissio.nextInt(500);
    }

    public void generateNewRoute() {
        route = new ArrayList<>();
        route.add(alku);
        generateIncXRoute();
    }

    private void generateIncXRoute() {
        for (int i = 0; i < inHowManySteps; i++) {
            Point currStep = route.get(i);
            int x = 0;
            int y = 0;
            if (fissio.nextDouble() > 0.7) {
                x = fissio.nextInt(10) + 1;
                y = fissio.nextInt(25) - 12;
            } else {
                y = fissio.nextInt(10) + 1;
                x = fissio.nextInt(25) - 12;
            }
            Point next = new Point((currStep.x + x) % 1500, (currStep.y + y) % 900);
            route.add(next);
        }
        route.add(loppu);
    }

    public ArrayList<Point> getRoute() {
        return route;
    }

}
