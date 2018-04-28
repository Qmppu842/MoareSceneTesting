package moaretestingtogetscenesworking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Qmppu842
 */
public class SceneManager {

    private Stage primeStage;
    private Application app;
    private StartScene startScene;
    private FastTableScene secondScene;
    private SlowTableScene thirdScene;

    public SceneManager(Stage primeStage, Application app) {
        this.primeStage = primeStage;
        this.app = app;
        init();
    }

    public void setScene(int tokako) {
        if (tokako == 2) {
            primeStage.setScene(getSecondScene());
        } else if (tokako == 1) {
            primeStage.setScene(getStartScene());
        } 
        switch (tokako){
        case 1: 
            primeStage.setScene(getStartScene());
            break;
        case 2:
            primeStage.setScene(getSecondScene());
            break;
        case 3:
            primeStage.setScene(getSlowScene());
        
        }

    }

    public void startUp() {
        primeStage.setScene(getStartScene());
        primeStage.show();
    }

    public void init() {
        startScene = new StartScene(this, "Start here!");
        secondScene = new FastTableScene(this, "FastOne");
        thirdScene = new SlowTableScene(this, "MADLAD!");
    }

    public Scene getStartScene() {
        return startScene.getScene();
    }

    public Scene getSecondScene() {
        return secondScene.getScene();
    }
    
        public Scene getSlowScene() {
        return thirdScene.getScene();
    }

}
