package io.github.profefenol.elderland.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SaveRunner {
    private final AtomicBoolean isRunning = new AtomicBoolean();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final PlayerDatabase database;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public SaveRunner(PlayerDatabase database) {
        this.database = database;
    }

    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            scheduler.scheduleWithFixedDelay(this::save, 1, 1, TimeUnit.MINUTES);
            log.info("SaveRunner started");
        }
    }

    public void stop() {
        if (isRunning.compareAndSet(true, false)) {
            log.info("SaveRunner stopping...");
            scheduler.shutdown();
            try {
                if (scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.info("SaveRunner stopped");
                } else {
                    log.warn("SaveRunner failed to stop, timeout");
                }
            } catch (InterruptedException e) {
                log.warn("SaveRunner failed to stop. AwaitTermination interrupted");
                e.printStackTrace();
            } finally {
                save();
            }
        }
    }


    private void save() {
        log.info("SaveRunner saving...");
        try {
            database.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("SaveRunner saved");
    }

}
