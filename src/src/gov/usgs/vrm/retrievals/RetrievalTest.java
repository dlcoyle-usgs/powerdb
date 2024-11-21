package gov.usgs.vrm.retrievals;

import gov.usgs.vrm.authorization.Authorization;
import gov.usgs.vrm.authorization.AuthorizationParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import gov.usgs.utils.LogUtils;

/**
 * Provides the VRM retrieval implementation.
 * @author dlcoyle@usgs.gov
 */
public class RetrievalTest {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
    static final String STARS = "***************************************************************************************************";
    static String HOST_URL = "https://vrmapi.victronenergy.com/v2/auth/login";
    static String LoginString = "{\"username\":\"dlcoyle@usgs.gov\",\"password\":\"XXXXXXXX\"}";

    /**
     * The VrmAuthorization is currently stored in memory for the duration of the program run.
     * The plan going forward it to add this to persistent storage.
     * The authorization is reused for each VRM web service call.
     * Also, the authorization is eventually revoked so a handler will be needed to implement
     * the capability to respond to an authorization error and "log in" again to obtain a
     * new JWT authorization.
     * This current implementation provides in effect the "singleton instance".
     * This will be changed soon.
     */
    public static Authorization authorization = null;

    /**
     * The main entry point for the initial retrieval test.
     * TODO: Move to test.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String args[]) throws IOException, InterruptedException {
        LogUtils.header("RetrievalTest: Starting ...");
        RetrievalTest retrievalTest = new RetrievalTest();
        retrievalTest.sendPostRequest();
        LogUtils.header("RetrievalTest: Finished");
    }

    public static void main_v1(String args[]) throws IOException, InterruptedException {
        logger.info(STARS);
        logger.info( "*** RetrievalTest: Starting ...");
        logger.info(STARS);
        RetrievalTest retrievalTest = new RetrievalTest();
        retrievalTest.sendPostRequest();
        logger.info(STARS);
        logger.info( "*** RetrievalTest: Finished");
        logger.info(STARS);
    }

    /**
     * Sends a POST request.
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
     void sendPostRequest() throws IOException, InterruptedException {
        logger.info("sendPostRequest: starting ...");
        //logger.info(LoginString);

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOST_URL))
                .POST(HttpRequest.BodyPublishers.ofString(LoginString))
                .build();

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug("sendPostRequest: finished");
        logger.debug("Response Status: {}", response.statusCode());
        logger.debug("Response Headers: {}", response.headers());
        //logger.debug("Response Body: {}", response.body());

         authorization = AuthorizationParser.parseAuthorization(response.body().toString());

         logger.info("sendPostRequest: obtained authorization: {}", authorization.toString());

    }

    void createRetrieval() {

        logger.info("createRetrieval: starting ...");

        RetrievalConfig retrievalConfig = RetrievalConfigFactory.createRetrievalConfig();
        //try (HttpClient client = HttpClient.newHttpClient()) {
        try (HttpClient client = HttpClient.newHttpClient()) {

            logger.info("retrievalTest: running ...");

            String loginURL = retrievalConfig.LoginURL;

            logger.info("loginURL: {}", loginURL);

        }

        logger.info("createRetrieval: finished");

    }
}
