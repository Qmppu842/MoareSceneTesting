package moaretestingtogetscenesworking;

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
    }

 

}
