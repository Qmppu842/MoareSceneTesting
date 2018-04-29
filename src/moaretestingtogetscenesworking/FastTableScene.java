package moaretestingtogetscenesworking;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

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

//    @Override
//    protected Button backButton() {
//        EventHandler<ActionEvent> backAction = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//               mgr.setScene(1);
//            }
//        };
//
//        return coreButtonMaker("Back to menu.", backAction);
//    }
}
