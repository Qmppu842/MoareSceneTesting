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
    private FastTableScene fastScene;
    private SlowTableScene slowScene;
    private NonTableScene nonScene;

    public SceneManager(Stage primeStage, Application app) {
        this.primeStage = primeStage;
        this.app = app;
        init();
    }

    public void setScene(int tokako) {
//        if (tokako == 2) {
//            primeStage.setScene(getFastTableScene());
//        } else if (tokako == 1) {
//            primeStage.setScene(getStartScene());
//        } 
        switch (tokako) {
            case 1:
                primeStage.setScene(getStartScene());
                break;
            case 2:
                primeStage.setScene(getFastTableScene());
                break;
            case 3:
                primeStage.setScene(getSlowTableScene());
                break;
            case 4:
                primeStage.setScene(getNonTableScene());

        }

    }

    public void startUp() {
        primeStage.setScene(getStartScene());
        primeStage.show();
    }

    public void init() {
        startScene = new StartScene(this, "Start here!");
        fastScene = new FastTableScene(this, "FastOne");
        slowScene = new SlowTableScene(this, "MADLAD!");
        nonScene = new NonTableScene(this, "nonTable");
    }

    public Scene getStartScene() {
        return startScene.getScene();
    }

    public Scene getFastTableScene() {
        return fastScene.getScene();
    }

    public Scene getSlowTableScene() {
        return slowScene.getScene();
    }

    public Scene getNonTableScene() {
        return nonScene.getScene();
    }
}
