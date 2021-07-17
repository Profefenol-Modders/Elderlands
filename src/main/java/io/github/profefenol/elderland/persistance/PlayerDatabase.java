package io.github.profefenol.elderland.persistance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDatabase {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ObjectWriter writer = new ObjectMapper().writerFor(new TypeReference<Map<String, PlayerData>>() {
    });
    private final Map<String, PlayerData> players;
    private final File file;

    public PlayerDatabase(File file) throws IOException {
        this.file = file;
        log.info("Player Database created {}", file);
        if (!file.exists()) {
            players = new ConcurrentHashMap<>();
            save();
        } else {
            final ObjectReader reader = new ObjectMapper().readerFor(new TypeReference<Map<String, PlayerData>>() {
            });
            this.players = new ConcurrentHashMap<>(reader.readValue(file));
        }
    }

    public void update(UUID uuid, PlayerData data) {
        players.put(uuid.toString(), data);
    }

    public PlayerData get(UUID uuid) {
        log.info("Player Database retrieved for player {}", uuid);
        return players.get(uuid.toString());
    }

    public PlayerData getOrCreate(UUID uuid) {
        log.info("Player Database get or created data for player {}", uuid);
        players.putIfAbsent(uuid.toString(), new PlayerData(0));
        return players.get(uuid.toString());
    }

    public void save() throws IOException {
        log.info("Player Database saved to file {}", file);
        final HashMap<String, PlayerData> map = new HashMap<>(players);
        writer.writeValue(file, map);
    }

}
