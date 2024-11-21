package gov.usgs.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.UUID;
/**
 * To generate a 32-character GUID (Globally Unique Identifier) in Java,
 * you can utilize the UUID class and then format the UUID as a 32-character string by removing the hyphens.
 * UUID.randomUUID() generates a random UUID.
 * uuid.toString().replace("-", "") converts the UUID to a string and removes the hyphens to ensure it is 32 characters long.
 * This will give you a 32-character alphanumeric string that can be used as a GUID.
 * @see "https://copilot.microsoft.com/chats/8YGmxzxARe5hJPwfnc1pz"
 */
public class GUIDUtils {
    static final Logger logger = LogManager.getLogger(GUIDUtils.class);
    /**
     * Test the unit for MySQL and Aquarius GUID compatibility.
     * @param args the args string array object
     */
    public static void main(String[] args) {
        logger.info("main: starting ...");

        // Generate a UUID
        UUID uuid = UUID.randomUUID();

        // Format the UUID as a 32-character string (remove hyphens)
        String guidString = uuid.toString().replace("-", "");

        // Ensure the GUID is 32 characters long

        logger.info("Converted GUID: {}", guidString);

        System.out.println("Converted GUID: " + guidString);

        logger.info("main: finished");

    }

}
