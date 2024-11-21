package gov.usgs.utils;

import java.time.*;

import java.time.Instant;

import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import java.time.ZoneId;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import gov.usgs.vrm.history.HistoryModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

///import gov.usgs.powerdb.*;

public class TimeUtils {
    static final Logger logger = LogManager.getLogger(TimeUtils.class);
    Instant start = null;
    Instant end   = null;

    /**
     * Applies the epochSecond method of the Java 8 Instant class.
     * @see "https://www.geeksforgeeks.org/instant-ofepochsecond-method-in-java-with-examples/"
     */
    public static Instant convertEpochSecond(long epochSecond) {

        Instant instant = Instant.ofEpochSecond(epochSecond);

        return instant;

    }

    //public static Instant ofEpochSecond(long epochSecond)



    /**
     * Test the unit.
     * @param args
     */
    public static void mainv1(String[] args) {
            //Duration d = Duration.between(LocalTime.NOON,LocalTime.MAX);
            //System.out.println(d.get(ChronoUnit.SECONDS));
            long epochSecond = 1200000l;
            epochSecond = 1713734826;
            Instant instant = TimeUtils.convertEpochSecond(epochSecond);
            System.out.println(instant);

    }

    /**
     * Test the unit for MySQL compatibility.
     * @param args
     */
    public static void main(String[] args) {
        logger.info("main: starting");
        //Duration d = Duration.between(LocalTime.NOON,LocalTime.MAX);
        //System.out.println(d.get(ChronoUnit.SECONDS));
        long epochSecond = 1200000l;

        Instant timeStamp= Instant.now();

        epochSecond = 1713734826;

        Instant convertedInstant = TimeUtils.convertEpochSecond(epochSecond);

        //LocalDateTime localDateTime = new LocalDateTime(instant);

        //java.time.ZoneId zoneId = new

        //ZonedDateTime zonedDateTime = new ZonedDateTime(instant);

        //logger.info("instant: {}", instant.toString() );

        //ZonedDateTime LAZone= timeStamp.atZone(ZoneId.of("America/Los_Angeles"));

        //logger.info("instant: {}", instant.toString() );

        String EasternIANATZString = "America/New_York";
        String UTCIANATZString = "America/New_York";

        ZonedDateTime timeInLAZone= timeStamp.atZone(ZoneId.of("America/Los_Angeles"));

        ZonedDateTime timeInETZone= timeStamp.atZone(ZoneId.of(EasternIANATZString));

        ZonedDateTime timeInUTCZone= convertedInstant.atZone(ZoneId.of("Z"));


        logger.info("Machine Time Now: {}", timeStamp);
        logger.info("In {} Time Zone: {}", "UTC", timeInUTCZone);
        logger.info("In Los Angeles(America) Time Zone: {}", timeInLAZone);
        logger.info("In {} Time Zone: {}", EasternIANATZString, timeInETZone);


        logger.info("Converted Instant: {}", convertedInstant);

        LocalDateTime localDateTime = timeInETZone.toLocalDateTime();

        logger.info("Converted localDateTime: {}", localDateTime);

        logger.info("main: finished");

    }

}
