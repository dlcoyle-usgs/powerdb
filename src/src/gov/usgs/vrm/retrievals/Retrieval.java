package gov.usgs.vrm.retrievals;

import gov.usgs.utils.LogUtils;
import gov.usgs.utils.StopWatch;
import gov.usgs.vrm.authorization.AccessToken;
import gov.usgs.vrm.authorization.AccessTokenFactory;
import gov.usgs.vrm.authorization.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Provides the VRM retrieval implementation.
 * @author dlcoyle@usgs.gov
 * @see "https://www.baeldung.com/java-authentication-authorization-service"
 * @see "https://docs.oracle.com/en/java/javase/11/security/java-authentication-and-authorization-service-jaas-reference-guide.html"
 */
public class Retrieval {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");

    /**
     * Provides a stop watch for measurement of the elapsed time operations within this single-threaded application.
     */
    protected StopWatch stopWatch = new StopWatch();

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
    protected Authorization authorization = null;

    /**
     * The Victron VRM API currently can optionally use an Access Token to gain authorized access into the system.
     * This is the token based API option. This is used in the current revision of this application (preferred).
     * The same Authorization is reused for each VRM web service call.
     * The Authorization will be revoked after a period of time, such as a few months (as set by the grantor).
     * A handler will be needed to implement the capability to respond to rejection of an access token.
     */
    protected AccessToken accessToken = null;

    /**
     * The Retrieval Config object.
     */
    protected RetrievalConfig retrievalConfig = null;

    /**
     * The Request URL object.
     */
    public RequestObject requestObject = new RequestObject();

    /**
     * The Request URL object.
     */
    public String requestURL = null;

    /**
     * Answers the Stopwatch tool instance.
     * @return the Stopwatch tool instance.
     */
    public StopWatch getStopWatch() {
        return this.stopWatch;
    }

    /**
     * Answers the Authorization which is reused for each VRM web service call.
     */
    protected Authorization getAuthorization() {
        return authorization;
    }

    /**
     * Answers the Access Token which is reused for each VRM web service call.
     */
    protected AccessToken getAccessToken() {
        return this.accessToken;
    }

    /**
     * Answers the Retrieval Config object.
     * @return the Retrieval Config object.
     */
    protected RetrievalConfig getRetrievalConfig() {
        return this.retrievalConfig;
    }

    /**
     * Answers the Request URL object.
     * @return the Request URL object.
     */
    public String getRequestURL() {
        return this.requestURL;
    }

    /**
     * Sets the Request URL object.
     */
    public void setRequestURL(String requestURL) {
        //logger.debug("setRequestURL: {}", requestURL);
        this.requestURL = requestURL;
        this.requestObject.requestURL = requestURL;
    }

    /**
     * The main entry point for the initial authentication test.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void xmain(String args[]) throws IOException, InterruptedException, URISyntaxException {
        LogUtils.header("RetrievalModule: Starting ...");
        Retrieval retrievalModule = new Retrieval();
        retrievalModule.init();
        retrievalModule.sendRequest();
        //retrievalModule.composeHistoryQuery();

        LogUtils.header("RetrievalModule: Finished");
    }

    /**
     * Initializes the instance.
     */
    public void init() {
        this.accessToken = AccessTokenFactory.getAccessToken();
        this.retrievalConfig = new RetrievalConfig();
    }

//    private String encodeValue(String value) throws UnsupportedEncodingException {
//        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
//    }

//    /**
//     * Composes the VRM API history query.
//     * @see "https://www.baeldung.com/java-url-encoding-decoding"
//     * @return a URL encoded string
//     */
//    String xcomposeHistoryQuery() throws UnsupportedEncodingException {
//
////            Map<String, String> requestParams = new HashMap<>();
//            requestParams.put("start", "1713204523");
//            requestParams.put("attributeIds[1]", "143");
////
//            String encodedURL = requestParams.keySet().stream()
//                    .map(key -> key + "=" + encodeValue(requestParams.get(key)))
//                    .collect(joining("&", "http://www.baeldung.com?", ""));
//
//            logger.debug("*** encodedURL: {}", encodedURL);
//
//            assertThat(testUrl, is(encodedURL));
//        for (Map.Entry<String, String> stringStringEntry : requestParams.entrySet()) {
//
//        }
//        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            // Do something with the key and value
//            String encodedKey=encodeValue(key);
//            String.format("%s=%s", encodedKey, value);
//        }
//        /**
//         * idSite='383620';
//         *
//         * #echo "SITE: ${SITE}";
//         *
//         * OUTFILE="get-site-history-${idSite}.json";
//         *
//         * cd /home/dlcoyle/devel/vrm-api
//         *
//         * query_url="https://vrmapi.victronenergy.com/v2/installations/${idSite}/widgets/Graph"
//         * query_url+='?'
//         * query_url+='start=1713204523'
//         * query_url+='&attributeIds%5B1%5D=143'
//         */
//
//        String queryURL="https://vrmapi.victronenergy.com/v2/installations/IdSite/widgets/Graph?";
//        String requestParameters="start=1713204523";
//        requestParameters+="&attributeIds%5B1%5D=143";
//        //queryURL+=this.encodeValue(requestParameters);
//        logger.debug("queryURL: {}", queryURL + requestParameters);
//
//        String encodedQueryURL = queryURL + this.encodeValue(requestParameters);
////        try {
////            encodedQueryURL = encodeValue(queryURL);
////        } catch (UnsupportedEncodingException e) {
////            throw new RuntimeException(e);
////        }
//
//        logger.debug("Encoded QueryURL: {}", encodedQueryURL);
//
//        return encodedQueryURL;
//
//    }



    /**
     * Sends an HTTP GET request to the VRM system.
     * @throws IOException
     * @throws InterruptedException
     * @throws URISyntaxException
     */
    public ResponseObject sendRequest() throws IOException, InterruptedException, URISyntaxException {

        logger.info("sendRequest: ");
        logger.debug("*** accessToken: {}", this.accessToken.getTokenString());

        ResponseObject responseObject = new ResponseObject();

        HttpResponse<String> httpResponse = null; //new HttpResponse<String>();

        String authorizationHeader = "x-authorization: Token " + this.accessToken.getTokenString();
        authorizationHeader = "Token " + this.accessToken.getTokenString();
        //logger.debug("authorizationHeader: {}", authorizationHeader);

        String requestURL = getRequestURL(); //"https://vrmapi.victronenergy.com/v2/installations/383620/widgets/Graph?start=1713204523&attributeIds%5B1%5D=143";
        //requestURL = "https://vrmapi.victronenergy.com/v2/installations/383620/widgets/Graph?start=1713204523&attributeIds%5B1%5D=143";

//        logger.debug("requestURL: {}", requestURL);
//        //logger.debug("token: {}", this.accessToken.getTokenString());
//        if (requestURL == null) {
//            logger.debug("NULL");
//        } else {
//            logger.debug("NOT NULL");
//        }

        URI uri = new URI(requestURL);

        logger.debug("requestURI: {}", uri);

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("x-authorization",  authorizationHeader)
                    .GET()
                    .build();

            // Send the request and receive the response
            stopWatch.start();
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            stopWatch.end();
            //stopWatch.logDuration("VRM API History Elapsed Time");
        }

        // Print the status code and response body
        //System.out.println("Status Code: " + response.statusCode());
        //System.out.println("Response: " + response.body());
        responseObject.setResponseBody(httpResponse.body());
        responseObject.requestObject = this.requestObject;
        responseObject.httpResponse = httpResponse;

        return responseObject;
    }

//    /**
//     * Sends an authentication request to the Victron VRM API server.
//     * @throws IOException
//     * @throws InterruptedException
//     */
//     public void sendAuthenticationRequest() throws IOException, InterruptedException {
//        logger.info("AuthenticationModule: sendAuthenticationRequest: starting ...");
//        //logger.info(LoginString);
//
//        HttpClient client = HttpClient.newBuilder().build();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(this.retrievalConfig.LoginURL))
//                .POST(HttpRequest.BodyPublishers.ofString(this.retrievalConfig.LoginString))
//                .build();
//        stopWatch.start();
//        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        stopWatch.end();
//        stopWatch.logDuration("VRM API Authentication Elapsed Time");
//        logger.debug("sendPostRequest: finished");
//        logger.debug("Response Status: {}", response.statusCode());
//        logger.debug("Response Headers: {}", response.headers());
//        //logger.debug("Response Body: {}", response.body());
//
//         authorization = AuthorizationParser.parseAuthorization(response.body().toString());
//
//         logger.debug("sendAuthenticationRequest: obtained authorization: {}", getAuthorization().toString());
//
//         describeAuthorization(getAuthorization());
//
//    }
//
//    /**
//     * Describes an authorization object.
//     * @param authorization
//     */
//    void describeAuthorization(Authorization authorization) {
//        logger.info("describeAuthorization:");
//        logger.info("authorization.IdUser: {}", authorization.getIdUser());
//        logger.info("authorization.VerificationMode: {}", authorization.getVerificationMode());
//        //logger.info("authorization.VerificationSent: {}", authorization.getVerificationSent());
//    }
//    String encodeValue(String value) throws UnsupportedEncodingException {
//        return URLEncoder.encode(value, StandardCharsets.UTF_8);
//    }
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
