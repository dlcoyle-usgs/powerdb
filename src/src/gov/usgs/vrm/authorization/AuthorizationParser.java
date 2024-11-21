package gov.usgs.vrm.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Provides methods to parse the JWT VRM authorization token.
 * Currently stores the authorization object in memory.
 */
public class AuthorizationParser {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");

    /**
     * The VrmAuthorization is currently stored in memory for the duration of the program run.
     * The plan going forward it to add this to persistent storage.
     * The authorization is reused for each VRM web service call.
     * Also, the authorization is eventually revoked so a handler will be needed to implement
     * the capability to respond to an authorization error and "log in" again to obtain a
     * new JWT authorization.
     */
    public static Authorization authorization = null;

    /**
     * Parses and unmarshals the supplied JWT token into a VRM authorization object.
     * The authorization is currently stored in memory locally.
     * This will be changed soon to return the object and a persistent storage layer will be used.
     * @param jsonString the JWT token
     * @throws IOException
     */
    public static Authorization parseAuthorization(String jsonString) throws IOException {
        logger.info("parseAuthorization: starting");
        //logger.info("unmarshalJSON: ***");
        //logger.info("unmarshalJSON: jsonString=[" + jsonString + "]");
        //logger.info("unmarshalJSON: ***");
        ObjectMapper objectMapper = new ObjectMapper();

        Authorization authorization = objectMapper.readValue(jsonString, Authorization.class);

        //logger.info("Object: {}", authorization.toString());
        //logger.info("Token: {}", authorization.getToken());
        //logger.info("IdUser: {}", authorization.getIdUser());
        //logger.info("VMode: {}", authorization.getVerificationMode());
        //logger.info("VSent: {}", Boolean.toString(authorization.getVerificationSent()));

        logger.info("parseAuthorization: finished");

        return authorization;

    }
}
