package moaretestingtogetscenesworking;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Qmppu842
 */
public class RouteTile {

    private ArrayList<Point> route;
    private ArrayList<Point> towerPlaces;

    public RouteTile(ArrayList<Point> route, ArrayList<Point> towerPlaces) {
        this.route = route;
        this.towerPlaces = towerPlaces;
    }

    public ArrayList<Point> getRoute() {
        return route;
    }

    public ArrayList<Point> getTowerPlaces() {
        return towerPlaces;
    }
    
    

}
