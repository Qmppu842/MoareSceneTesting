package moaretestingtogetscenesworking;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Qmppu842
 */
public class EmptyTowerPlace extends BaseTower {

//    private Point position;
//    private int size;
//    private Color outside;
//    private Color inside;
////    private Circle clickArea;
//    private boolean isClicked;

    public EmptyTowerPlace(Point position, int size, Color outside, Color inside) {
        super(position, size, outside, inside);
//        this.position = position;
//        this.size = size;
//        this.outside = outside;
//        this.inside = inside;
//
//        isClicked = false;
//        clickArea = new Circle(size);
//        clickArea.setOnMouseClicked((event) -> {
//            isClicked = true;
//        });

    }

//    @Override
//    protected boolean isClickHere(Point click) {
//        double aa = click.distance(position);
//        boolean returnValue = false;
//        if (aa < (size / 2) + 5) {
//            returnValue = true;
//            isClicked = 1;
//        }
//        return returnValue;
//    }

//    protected boolean isIsClicked() {
//        return isClicked;
//    }

    @Override
    public void update(GraphicsContext gc) {
        if (isClicked != -1) {
            inside = Color.CHARTREUSE;
//            System.out.println("APUAA!");
//            this = new FirstTestTower(position, 40, Color.OLIVE, Color.INDIGO, 150, 5, 25);
        }
        gc.setFill(inside);
        gc.fillOval(position.x - (size / 2), position.y - (size / 2), size, size);
//        Circle circle = new Circle(position.x, position.y, size/2, inside);
//        gc.
    }

}
