package io.github.catchyaintit.network;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;

public class Stats {
    private int corruption;
    private int gold;

    public Stats(int corruption, int gold) {
        this.corruption = corruption;
        this.gold = gold;
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
        int[] list = {corruption, gold};
        return list;
    }
}
