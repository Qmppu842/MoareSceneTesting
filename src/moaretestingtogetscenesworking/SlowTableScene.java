package moaretestingtogetscenesworking;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Qmppu842
 */
public class SlowTableScene extends BaseScene {

    public SlowTableScene(SceneManager mgr) {
        super(mgr);
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
//
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
