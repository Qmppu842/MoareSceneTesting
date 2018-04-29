package moaretestingtogetscenesworking;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Qmppu842
 */
public class NonTableScene extends BaseScene {

    public NonTableScene(SceneManager mgr) {
        super(mgr);
    }

    public NonTableScene(SceneManager mgr, String buttonText) {
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
}
