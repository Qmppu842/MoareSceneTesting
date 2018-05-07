package moaretestingtogetscenesworking;

import javafx.scene.paint.Color;

/**
 *
 * @author Qmppu842
 */
public enum DifferentTowers {
    BASIC("Basic"), BEAM("Beam"), SNIPER("Sniper"), SNIPERBEAM("SniperBeam"),
    CANNON("Cannon"), LOW_HP_BONUS_DMG("Low"), HIGH_HP_BONUS_DMGE("High");

    private FirstTestTower tower;

    private DifferentTowers(String moi) {
        FirstTestTower builder = null;
        switch (moi) {
            case "Basic":
                break;
            case "Beam":
                break;
            case "Sniper":
                break;
            case "SniperBeam":
                break;
            case "Cannon":
                break;
            case "Low":
                break;
            case "High":
                break;
                
            default:
                builder = new FirstTestTower(null, 0, Color.OLIVE, Color.INDIGO, 0, 0, 0);
                break;
        }

        tower = builder;
    }

    public FirstTestTower getTower() {
        return tower;
    }

}
