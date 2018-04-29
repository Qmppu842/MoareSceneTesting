package moaretestingtogetscenesworking;

import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 *
 * @author Qmppu842
 */
public class StartScene extends BaseScene {

    public StartScene(SceneManager mgr) {
        super(mgr);
    }

    public StartScene(SceneManager mgr, String buttonText) {
        super(mgr, buttonText);
        setNameOfScene(this.getClass().toString());
        moim = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("moiimmmmmm");
                mgr.setScene(2);
            }
        };
//        ads = new ActionListener() {
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent ae) {
//                System.out.println("moimmm");
//            }
//        };
    }

    private Button goToSlowTable() {
         EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();//TODO make it not exit
            }
        };
        return coreButtonMaker("Go to Slow Table Demo.", action);
    }

    private Button goToFastTable() {
         EventHandler<ActionEvent> exitter = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();//TODO make it not exit
            }
        };

        return coreButtonMaker("Go to Fast Table Demo.", exitter);
    }

    private Button goToNonTable() {
         EventHandler<ActionEvent> exitter = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();//TODO make it not exit
            }
        };

        return coreButtonMaker("Go to Non-Table Demo.", exitter);
    }

    @Override
    protected Button backButton() {
        EventHandler<ActionEvent> exitter = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        };

        return coreButtonMaker("Exit", exitter);
    }

}
