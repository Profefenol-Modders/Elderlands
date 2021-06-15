package io.github.catchyaintit.network.server;

import io.github.catchyaintit.network.Stats;

import java.util.HashMap;
import java.util.UUID;

public class ServerClientStatsManager {
    private HashMap<UUID, Stats> stats = new HashMap<>();

    public ServerClientStatsManager( HashMap<UUID, Stats> stats) {
        this.stats = stats;
    }


    public Stats getStat(UUID id) throws Exception {
        Stats stat = stats.get(id);
        if (stat == null) {
            throw new Exception("Missing stat" + id);
        }
        return stats.get(id);
    }

    public boolean hasStat(UUID id) {
        if (stats.containsKey(id)) {
            return true;
        }
        return false;
    }

    public void createEmptyStat(UUID id) {
        stats.put(id, new Stats(0,0));
    }

    //public Stats loadStat() {

    //}

   // public Stats unloadStat() {

    //}


    public static ServerClientStatsManager createEmpty() {
        return new ServerClientStatsManager(new HashMap<UUID, Stats>());
    }


}
