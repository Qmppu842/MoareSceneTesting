package moaretestingtogetscenesworking;

/**
 *
 * @author Qmppu842
 */
public class FastTableScene extends BaseScene {

    public FastTableScene(SceneManager mgr) {
        super(mgr);
    }

    public FastTableScene(SceneManager mgr, String buttonText) {
        super(mgr, buttonText);
        setNameOfScene(this.getClass().toString());
    }

}
