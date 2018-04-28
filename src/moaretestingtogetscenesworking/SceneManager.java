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

    public SceneManager(Stage primeStage, Application app) {
        this.primeStage = primeStage;
        this.app = app;
        init();
    }

    public void startUp() {
        primeStage.setScene(getStartScene());
        primeStage.show();
    }

    public void init() {
        startScene = new StartScene(this, "Start here!");
        secondScene = new FastTableScene(this, "FastOne");
    }

    public Scene getStartScene() {
        return startScene.getScene();
    }

    public Scene getSecondScene() {
        return secondScene.getScene();
    }

}
