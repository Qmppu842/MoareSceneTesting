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
        int size = -1;
        int attackTime = -1;
        int attackDamage = -1;
        int range = -1;
        int attackReadyLimit = -1;
        Color outside = Color.OLIVE;
        Color inside = Color.INDIGO;
        switch (moi) {
            case "Beam":
                attackTime = 50;
                attackDamage = 5;
                range = 200;
                attackReadyLimit = 100;
                inside = Color.BROWN;
                break;
            case "Sniper":
                attackTime = 10;
                attackDamage = 15;
                range = 450;
                attackReadyLimit = 100;
                inside = Color.ALICEBLUE;
                break;
            case "SniperBeam":
                attackTime = 50;
                attackDamage = 7;
                range = 400;
                attackReadyLimit = 120;
                inside = Color.BROWN.interpolate(Color.ALICEBLUE, 0.5);
                break;
            case "Cannon":
                attackTime = 5;
                attackDamage = 55;
                range = 150;
                attackReadyLimit = 100;
                inside = Color.DARKRED;
                break;
            case "Low":
                attackTime = 10;
                attackDamage = 15;
                range = 150;
                attackReadyLimit = 100;

                inside = Color.CORNSILK;
                break;
            case "High":
                attackTime = 10;
                attackDamage = 15;
                range = 150;
                attackReadyLimit = 100;
                inside = Color.AZURE;
                break;

            case "Basic":
            default:
                attackTime = 10;
                attackDamage = 15;
                range = 200;
                attackReadyLimit = 100;
                break;
        }

        tower = new FirstTestTower(size, outside, inside, range, attackDamage, attackTime, attackReadyLimit);
    }

    public FirstTestTower getTower() {
        return tower;
    }

}
