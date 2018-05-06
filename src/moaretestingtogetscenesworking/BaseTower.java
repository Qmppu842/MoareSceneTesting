package moaretestingtogetscenesworking;

import java.awt.Point;
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

    public BaseTower(Point position, int size, Color outside, Color inside) {
        this.position = position;
        this.size = size;
        this.outside = outside;
        this.inside = inside;
        this.isClicked = false;
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
}
