package moaretestingtogetscenesworking;

import javafx.scene.paint.Paint;

/**
 *
 * @author Qmppu842
 */
public class Walker implements Updatable {
    
    private double speed;
    private double speedCollecter;
    private int x;
    private int y;
    private Paint color;
    private int maxHP;

    @Override
    public void update() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    

}
