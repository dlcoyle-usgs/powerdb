package gov.usgs.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.time.Instant;

/**
 * Provides a custom-built StopWatch state machine based implementation.
 * Similar to the Apache Commons Lang API.
 */
public class StopWatch {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
    Instant start = null;
    Instant end   = null;
    Duration duration;

    /**
     * Start the watch.
     */
    public void start() {
        start = Instant.now();
    }

    /**
     * Stop the watch.
     */
    public void end() {
        end = Instant.now();
        duration = Duration.between(start, end);
    }

    /**
     * Answers the elapsed time value as a duration.
     */
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Logs the elapsed time value as a duration.
     */
    public void logDuration() {
        logger.debug("duration: {}", this.duration);
    }

    /**
     * Logs the elapsed time value as a duration.
     */
    public void logDuration(String tag) {
        logger.debug("tag: {}, duration: {}", tag, this.duration);
    }

    /**
     * Test the unit.
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // time passes
        Thread.sleep(3000);
        stopWatch.end();
        System.out.printf("duration: %s\n", stopWatch.getDuration());

    }

}
