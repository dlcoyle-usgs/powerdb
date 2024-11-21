package gov.usgs.vrm.installations;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.vrm.authorization.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Provides methods to parse the Victron VRM API installations JSON web response document.
 * Optionally stores the installations object in memory.
 */
public class InstallationsParser {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");

    /**
     * The Victron VRM Installations object can optionally be stored in memory for the duration of the program run.
     * The plan going forward it to add this to persistent storage.
     * The authorization is reused for each VRM web service call.
     * Also, the authorization is eventually revoked so a handler will be needed to implement
     * the capability to respond to an authorization error and "log in" again to obtain a
     * new JWT authorization.
     */
    public static Installations Installations = null;

    /**
     * Parses and unmarshals the supplied JWT token into a VRM authorization object.
     * The authorization is currently stored in memory locally.
     * This will be changed soon to return the object and a persistent storage layer will be used.
     * @param jsonString the JWT token
     * @throws IOException
     */
    public static Installations parseInstallations(String jsonString) throws IOException {
        logger.info("parseInstallations: starting");
        //logger.info("unmarshalJSON: ***");
        //logger.info("unmarshalJSON: jsonString=[" + jsonString + "]");
        //logger.info("unmarshalJSON: ***");
        ObjectMapper objectMapper = new ObjectMapper();

        Installations installations = objectMapper.readValue(jsonString, Installations.class);

        //logger.info("Object: {}", authorization.toString());
        //logger.info("Token: {}", authorization.getToken());
        //logger.info("IdUser: {}", authorization.getIdUser());
        //logger.info("VMode: {}", authorization.getVerificationMode());
        //logger.info("VSent: {}", Boolean.toString(authorization.getVerificationSent()));

        logger.info("parseInstallations: finished");

        return installations;

    }
}
