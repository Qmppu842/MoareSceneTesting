package moaretestingtogetscenesworking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 *
 * @author Qmppu842
 */
public class FastTableScene extends BaseScene {

    private LogicLoader logic;
    private Canvas wall;
    private GraphicsContext gc;

    public FastTableScene(SceneManager mgr, LogicLoader logic) {
        super(mgr);
        this.logic = logic;
        wall = new Canvas(900, 900);
        gc = wall.getGraphicsContext2D();
        allThings.setCenter(wall);
        footer.getChildren().add(generateNewRoute());
    }

    private void drawRoute() {
        gc.clearRect(0, 0, wall.getWidth(), wall.getHeight());
        Paint p;
        Paint road = new Color(0.5, 0, 0.5, 0.7);
        Paint woods = new Color(0, 1, 0, 0);
        boolean[][] route = logic.getFastTable();
        for (int i = 0; i < route.length; i++) {
            for (int j = 0; j < route[i].length; j++) {
                if (route[i][j]) {
                    p = road;
                } else {
                    p = woods;
                }
                gc.setFill(p);
                gc.fillRect(10 * i, 10 * j, 10, 10);
            }
        }
    }

    public FastTableScene(SceneManager mgr, String buttonText) {
        super(mgr, buttonText);
        setNameOfScene(this.getClass().toString());
        moim = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("kalkiii");
                mgr.setScene(3);
            }
        };
    }

    protected Button generateNewRoute() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logic.makeNewFast();
                drawRoute();
            }
        };

        return coreButtonMaker("Generate new Route.", action);
    }

}
