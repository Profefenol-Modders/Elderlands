package io.github.catchyaintit.network;

import io.github.catchyaintit.ElderLand;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;

public class Stats {
    private int corruption;
    private int corruptionSmall = 0;
    private int gold;
    private int lastTickTime = 0;

    public Stats(int corruption, int gold) {
        this.corruption = corruption;
        this.gold = gold;
    }

    public int getLastTickTime() {
        return lastTickTime;
    }

    public void setLastTickTime(int time) {
        lastTickTime = time;

    }
    public void incrementCorruptionSmall(int amount) {
        corruptionSmall += amount;
    }

    public boolean incrementCorruption() {
        if (increaseCorruption()) {
            corruptionSmall = 0;
            if (corruption > 5 || corruption == 4)
                corruption++;
            return true;
        }
        return false;
    }
    private boolean increaseCorruption() {
        if (corruptionSmall == corruption * 100) {
            return true;
        }
        return false;
    }

    public void setCorruption(int corruption) {
        this.corruption = corruption;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCorruption() {
        return corruption;
    }

    public int getGold() {
        return gold;
    }

    public int[] getStatArray() {
        int[] list = {corruption, gold, corruptionSmall};
        return list;
    }
}
