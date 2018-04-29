/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moaretestingtogetscenesworking;

import java.util.Random;

/**
 * 1000px x 1000px area to work so... fast should be max 100x100 tiles slow
 * could be either 1k x 1k tiles or something like 10k x 10k or 100k x 100k
 * tiles nonTable should be at least as big as slow one maybe it should be on
 * 1mil x 1mil "numberspaces"
 *
 * @author Qmppu842
 */
public class LogicLoader {

    private boolean[][] fastTable;
    private boolean[][] slowTable;
    private Random fuusio;

    public LogicLoader() {
        fuusio = new Random();
        fastTable = new boolean[90][90];
        slowTable = new boolean[1500][900];
    }

    private boolean generateTile() {
        return fuusio.nextBoolean();
    }

    public void makeNewFast() {
        for (int i = 0; i < fastTable.length; i++) {
            for (int j = 0; j < fastTable[i].length; j++) {
                fastTable[i][j] = generateTile();
            }
        }
    }

    public void makeNewSlow() {
        for (int i = 0; i < slowTable.length; i++) {
            for (int j = 0; j < slowTable[i].length; j++) {
                slowTable[i][j] = generateTile();
            }
        }
    }

    public boolean[][] getFastTable() {
        return fastTable;
    }

    public boolean[][] getSlowTable() {
        return slowTable;
    }

    
    
}
