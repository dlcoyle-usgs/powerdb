package gov.usgs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    static final Logger logger = LoggerFactory.getLogger(LogUtils.class);
    static final String STARS = "***************************************************************************************************";
    static final String STARS_PREFIX = "*** ";

    /**
     * Writes a log header message to draw attention to the event in the log file.
     * @param message the log header message to write
     */
    public static void header(String message) {

        logger.info(STARS);
        logger.info(STARS_PREFIX + message);
        logger.info(STARS);

    }
}
