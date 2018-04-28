package moaretestingtogetscenesworking;

import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

}
