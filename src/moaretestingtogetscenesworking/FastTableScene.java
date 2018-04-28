package moaretestingtogetscenesworking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Qmppu842
 */
public class FastTableScene extends BaseScene {

    public FastTableScene(SceneManager mgr) {
        super(mgr);
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

}
