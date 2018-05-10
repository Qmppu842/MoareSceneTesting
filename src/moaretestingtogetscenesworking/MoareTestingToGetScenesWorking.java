package moaretestingtogetscenesworking;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Qmppu842
 */
public class MoareTestingToGetScenesWorking extends Application {

    private SceneManager manager;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcoming menu!");
//        
        manager = new SceneManager(primaryStage/*, this*/);
        manager.startUp();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}