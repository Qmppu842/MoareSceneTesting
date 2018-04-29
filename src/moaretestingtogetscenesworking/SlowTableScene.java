package moaretestingtogetscenesworking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 *
 * @author Qmppu842
 */
public class SlowTableScene extends BaseScene {

    private LogicLoader logic;
    private Canvas wall;
    private GraphicsContext gc;

    public SlowTableScene(SceneManager mgr, LogicLoader logic) {
        super(mgr);

        this.logic = logic;
        wall = new Canvas(1500, 900);
        gc = wall.getGraphicsContext2D();
        allThings.setCenter(wall);
        footer.getChildren().add(generateNewRoute());
    }

    public SlowTableScene(SceneManager mgr, String buttonText) {
        super(mgr, buttonText);
        setNameOfScene(this.getClass().toString());
        moim = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRUE MADNESS");
                mgr.setScene(1);
            }
        };
    }

    protected Button generateNewRoute() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logic.makeNewSlow();
                drawRoute();
            }
        };

        return coreButtonMaker("Generate new Route.", action);
    }

    private void drawRoute() {
        gc.clearRect(0, 0, wall.getWidth(), wall.getHeight());
        Paint p;
        Paint road = new Color(0.5, 0, 0.5, 0.7);
        Paint woods = new Color(0, 1, 0, 0);
        boolean[][] route = logic.getSlowTable();
        for (int i = 0; i < route.length; i++) {
            for (int j = 0; j < route[i].length; j++) {
                if (route[i][j]) {
                    p = road;
                } else {
                    p = woods;
                }
                gc.setFill(p);
                gc.fillRect(1 * i, 1 * j, 1, 1);
            }
        }
    }

}
