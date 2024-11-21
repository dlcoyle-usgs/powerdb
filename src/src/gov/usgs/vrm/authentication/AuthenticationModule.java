package gov.usgs.vrm.authentication;

import gov.usgs.utils.LogUtils;
import gov.usgs.utils.StopWatch;
import gov.usgs.vrm.authorization.Authorization;
import gov.usgs.vrm.authorization.AuthorizationParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Provides the VRM retrieval implementation.
 * @author dlcoyle@usgs.gov
 * @see "https://www.baeldung.com/java-authentication-authorization-service"
 * @see "https://docs.oracle.com/en/java/javase/11/security/java-authentication-and-authorization-service-jaas-reference-guide.html"
 */
public class AuthenticationModule {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
    static String HOST_URL = "https://vrmapi.victronenergy.com/v2/auth/login";
    static String LoginString = "{\"username\":\"dlcoyle@usgs.gov\",\"password\":\"NPS1201!!\"}";

    /**
     * Provides a stop watch for measurement of the elapsed time operations within this single-threaded application.
     */
    static StopWatch stopWatch = new StopWatch();

    /**
     * The Victron VRM API currently uses a userid and password to authenticate into the system.
     * This is the same userid and password that is used to log into the VRM cloud dashboard.
     * There is a token based API. This can be used in a future revision of this application if preferred.
     * The login authentication process result returns JWT token, which we are here calling an Authorization.
     * The same Authorization is reused for each VRM web service call.
     * However, the Authorization is eventually revoked after a period of time.
     * Therefore, a handler will be needed to implement the capability to respond to an authorization
     * error and to "log in" and thus authenticate again to obtain a new Authorization (JWT Token).
     */
    protected static Authorization authorization = null;

    /**
     * The main entry point for the initial authentication test.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String args[]) throws IOException, InterruptedException {
        LogUtils.header("AuthenticationTest: Starting ...");
        AuthenticationModule authenticationModule = new AuthenticationModule();
        authenticationModule.sendAuthenticationRequest();
        LogUtils.header("AuthenticationTest: Finished");
    }

    /**
     * Answers the Authorization which is reused for each VRM web service call.
     */
    public static Authorization getAuthorization() {
        return authorization;
    }

    /**
     * Sends an authentication request to the Victron VRM API server.
     * @throws IOException
     * @throws InterruptedException
     */
     public void sendAuthenticationRequest() throws IOException, InterruptedException {
        logger.info("AuthenticationModule: sendAuthenticationRequest: starting ...");
        //logger.info(LoginString);

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOST_URL))
                .POST(HttpRequest.BodyPublishers.ofString(LoginString))
                .build();
        stopWatch.start();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        stopWatch.end();
        stopWatch.logDuration("VRM API Authentication Elapsed Time");
        logger.debug("sendPostRequest: finished");
        logger.debug("Response Status: {}", response.statusCode());
        logger.debug("Response Headers: {}", response.headers());
        //logger.debug("Response Body: {}", response.body());

         authorization = AuthorizationParser.parseAuthorization(response.body().toString());

         logger.debug("sendAuthenticationRequest: obtained authorization: {}", getAuthorization().toString());

         describeAuthorization(getAuthorization());

    }

    /**
     * Describes an authorization object.
     * @param authorization
     */
    void describeAuthorization(Authorization authorization) {
        logger.info("describeAuthorization:");
        logger.info("authorization.IdUser: {}", authorization.getIdUser());
        logger.info("authorization.VerificationMode: {}", authorization.getVerificationMode());
        //logger.info("authorization.VerificationSent: {}", authorization.getVerificationSent());
    }

//    void createRetrieval() {
//
//        logger.info("createRetrieval: starting ...");
//
//        RetrievalConfig retrievalConfig = RetrievalConfigFactory.createRetrievalConfig();
//        //try (HttpClient client = HttpClient.newHttpClient()) {
//        try (HttpClient client = HttpClient.newHttpClient()) {
//
//            logger.info("retrievalTest: running ...");
//
//            String loginURL = retrievalConfig.LoginURL;
//
//            logger.info("loginURL: {}", loginURL);
//
//        }
//
//        logger.info("createRetrieval: finished");
//
//    }

}
