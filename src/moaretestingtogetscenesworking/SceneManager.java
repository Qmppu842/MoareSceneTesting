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
    private LogicLoader logic;
    private WalkingSimScene walkingSimScene;

    public SceneManager(Stage primeStage, Application app) {
        this.primeStage = primeStage;
        this.app = app;
        this.primeStage.setMaximized(true);
        init();
    }

    /**
     * TODO refactor int to Enums that describe what they are.
     *
     * @param tokako <br>
     * 1 = StartScene, <br>
     * 2 = FastTableScene, <br>
     * 3 = SlowTableScene, <br>
     * 4 = NonTableScene
     */
    public void setScene(int tokako) {
        switch (tokako) {
            case 1:
                primeStage.setScene(getStartScene());
                break;
            case 2:
                primeStage.setScene(getFastTableScene());
//                primeStage.setFullScreen(true);
                break;
            case 3:
                primeStage.setScene(getSlowTableScene());
//                primeStage.setFullScreen(true);
                break;
            case 4:
                primeStage.setScene(getNonTableScene());
                primeStage.setFullScreenExitHint("Press powerbutton to escape me!");
//                primeStage.setFullScreen(true);
                this.primeStage.setMaximized(true);
                break;
            case 5:
                this.primeStage.setMaximized(true);
                primeStage.setScene(getWalkingSimScene());
                primeStage.setFullScreen(true);
                primeStage.setAlwaysOnTop(true);
                break;

        }
        
        this.primeStage.setMaximized(true);

    }

    public void startUp() {
        primeStage.setScene(getStartScene());
        primeStage.show();
    }

    public void init() {
        logic = new LogicLoader();
        startScene = new StartScene(this);
        fastScene = new FastTableScene(this, logic);
        slowScene = new SlowTableScene(this, logic);
        nonScene = new NonTableScene(this, logic);
        walkingSimScene = new WalkingSimScene(this, logic);
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

    public Scene getWalkingSimScene() {
        return walkingSimScene.getScene();
    }
}
