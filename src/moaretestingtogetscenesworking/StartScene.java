package moaretestingtogetscenesworking;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author Qmppu842
 */
public class StartScene extends BaseScene {

    public StartScene(SceneManager mgr) {
        super(mgr);
        VBox napit = new VBox();
        napit.getChildren().addAll(goToFastTable(), goToSlowTable(), goToNonTable(), goToWalkingSimTable(), backButton());

        allThings.setCenter(napit);
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
    }

    private Button goToSlowTable() {
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mgr.setScene(3);
            }
        };
        return coreButtonMaker("Go to Slow Table Demo.", action);
    }

    private Button goToFastTable() {
        EventHandler<ActionEvent> exitter = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mgr.setScene(2);
            }
        };

        return coreButtonMaker("Go to Fast Table Demo.", exitter);
    }

    private Button goToNonTable() {
        EventHandler<ActionEvent> exitter = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mgr.setScene(4);
            }
        };

        return coreButtonMaker("Go to Non-Table Demo.", exitter);
    }

    private Button goToWalkingSimTable() {
        EventHandler<ActionEvent> entterer = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mgr.setScene(5);
            }
        };

        return coreButtonMaker("Go to walking sim Demo.", entterer);
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
