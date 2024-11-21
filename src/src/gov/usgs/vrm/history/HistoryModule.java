package gov.usgs.vrm.history;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.usgs.main.HistoryMain;
import gov.usgs.utils.LogUtils;
import gov.usgs.vrm.authorization.Authorization;

import gov.usgs.vrm.retrievals.ResponseObject;
import gov.usgs.vrm.retrievals.Retrieval;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Provides the VRM retrieval implementation.
 * @author dlcoyle@usgs.gov
 * @see "https://www.baeldung.com/java-authentication-authorization-service"
 * @see "https://docs.oracle.com/en/java/javase/11/security/java-authentication-and-authorization-service-jaas-reference-guide.html"
 */
public class HistoryModule { //extends Retrieval {
    static final Logger logger = LogManager.getLogger(HistoryModule.class);
    public enum OnlineStatus {
        ONLINE,
        OFFLINE,
        DATABASE;
    }
    protected OnlineStatus status = OnlineStatus.ONLINE;
    public OnlineStatus getStatus() {
        return this.status;
    }
    public void setStatus(OnlineStatus status) {
        this.status = status;
    }

    /**
     * Performs the retrieval for the given history.
     * @throws IOException exception
     * @throws InterruptedException exception
     */
    public void performRetrieval(HistoryConfig historyConfig) throws IOException, InterruptedException, URISyntaxException {
        LogUtils.header("HistoryModule: performRetrieval: Starting ...");
        //HistoryConfig historyConfig = new HistoryConfig();
        //HistoryModule historyModule = new HistoryModule();
        String jsonString  = null;

        if (getStatus() == OnlineStatus.ONLINE) {
            logger.info("Status: {}", getStatus());
            Retrieval retrieval = new Retrieval();
            retrieval.init();
            retrieval.setRequestURL(historyConfig.getHistoryQueryURL());
            ResponseObject responseObject = retrieval.sendRequest();
            retrieval.getStopWatch().logDuration("VRM API History Elapsed Time");
            this.handleResponse(responseObject);
            jsonString  = responseObject.getResponseBody();
            logger.info("jsonString-length: {}", jsonString.length() );
        }
        else if (getStatus() == OnlineStatus.OFFLINE) {
            logger.info("Status: {}", getStatus());

            jsonString = JSONFileReader.openAndReadFilePath("data/site-history-383620-P7D-single-variable-raw.json");

            logger.info("jsonString-length: {}", jsonString.length() );

        } else {
            logger.error("unhandled status: {}", getStatus());
        }

        HistoryParser historyParser = new HistoryParser();

        History history = historyParser.parseHistoryJSON(jsonString);

        //DEBUG: Level 1
        historyParser.describeHistoryData(history);

        //DEBUG: Level 2
        //historyParser.describeHistoryDataDebug(history);

        LogUtils.header("HistoryModule: performRetrieval: Finished");
    }


//    /**
//     * The main entry point for the initial authentication test.
//     * @param args
//     * @throws IOException
//     * @throws InterruptedException
//     */
//    public static void main(String args[]) throws IOException, InterruptedException, URISyntaxException {
//        LogUtils.header("HistoryModule: Starting ...");
//
//        HistoryConfig historyConfig = new HistoryConfig();
//        HistoryModule historyModule = new HistoryModule();
//        String jsonString  = null;
//
//        if (getStatus() == OnlineStatus.ONLINE) {
//            logger.info("Status: {}", getStatus());
//            Retrieval retrieval = new Retrieval();
//            retrieval.init();
//            retrieval.setRequestURL(historyConfig.getHistoryQueryURL());
//            ResponseObject responseObject = retrieval.sendRequest();
//            retrieval.getStopWatch().logDuration("VRM API History Elapsed Time");
//            historyModule.handleResponse(responseObject);
//            jsonString  = responseObject.getResponseBody();
//            logger.info("jsonString-length: {}", jsonString.length() );
//        }
//        else if (getStatus() == OnlineStatus.OFFLINE) {
//            logger.info("Status: {}", getStatus());
//
//            jsonString = JSONFileReader.openAndReadFilePath("data/site-history-383620-P7D-single-variable-raw.json");
//
//            logger.info("jsonString-length: {}", jsonString.length() );
//
//        } else {
//            logger.error("unhandled status: {}", getStatus());
//        }
//
//        HistoryParser historyParser = new HistoryParser();
//
//        History history = historyParser.parseHistoryJSON(jsonString);
//
//        //DEBUG: historyParser.describeHistoryData(history);
//
//        LogUtils.header("HistoryModule: Finished");
//    }

    /**
     * The main entry point for the initial authentication test.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void mainv1(String args[]) throws IOException, InterruptedException, URISyntaxException {
        LogUtils.header("HistoryModule: Starting ...");

        HistoryConfig historyConfig = new HistoryConfig();
        HistoryModule historyModule = new HistoryModule();

        Retrieval retrieval = new Retrieval();
        retrieval.init();
        retrieval.setRequestURL(historyConfig.getHistoryQueryURL());
        ResponseObject responseObject = retrieval.sendRequest();

        retrieval.getStopWatch().logDuration("VRM API History Elapsed Time");

        logger.debug("responseObject: {}", responseObject.toString());

        historyModule.handleResponse(responseObject);

        LogUtils.header("HistoryModule: Finished");
    }

    /**
     * Handles the data layer response payload.
     * @param responseObject
     * @throws JsonProcessingException
     */
    void handleResponse(ResponseObject responseObject) throws JsonProcessingException {
        logger.info("HistoryModule: handleResponse: TODO: Handle HTTP Return Codes");
        logger.debug("responseObject: {}", responseObject.toString());
        logger.info("HistoryModule: HTTP Status: {}", responseObject.getHttpResponse().statusCode());
        int httpStatusCode = responseObject.getHttpResponse().statusCode();
        if (httpStatusCode == 200) {
            logger.info("HistoryModule: HTTP Status: OK");
        } else {
            throw new IllegalStateException(String.format("ERROR: Unexpected HTTP Status Code: %s", httpStatusCode));
        }

        //String jsonString  = responseObject.getResponseBody();
        //logger.info("HistoryModule: jsonString=: {}", jsonString);
        String replacementString = null;
        //logger.debug("jsonString: {}", jsonString);
        //int foundCount = 0;
        //String searchString = "\"143\"";
        //String replaceString = "\"timeseries\"";
        //if (jsonString.contains("\"143\":")) {
        //    foundCount++;
        //    replacementString = jsonString.replaceAll(searchString, replaceString);
        //}
        //logger.debug("replacementString: {}", replacementString);

//        HistoryParser historyParser = new HistoryParser();
//
//        //V1: History history = historyParser.parseHistoryJSON(replacementString);
//
//        History history = historyParser.parseHistoryJSON(jsonString);
//
//        logger.info("HistoryModule: Data: {}", "S");
//
//        historyParser.describeHistoryData(history);
    }

    /**
     * Handles the data layer response payload.
     * @param responseObject
     * @throws JsonProcessingException
     */
    void handleResponseV1(ResponseObject responseObject) throws JsonProcessingException {
        logger.info("HistoryModule: handleResponse:");
        String jsonString  = responseObject.getResponseBody();
        String replacementString = null;
        //logger.debug("jsonString: {}", jsonString);
        int foundCount = 0;
        String searchString = "\"143\"";
        String replaceString = "\"timeseries\"";
        if (jsonString.contains("\"143\":")) {
            foundCount++;
            replacementString = jsonString.replaceAll(searchString, replaceString);
        }
        //logger.debug("replacementString: {}", replacementString);

        HistoryParser historyParser = new HistoryParser();

        History history = historyParser.parseHistoryJSON(replacementString);

        logger.info("HistoryModule: Data:");

        historyParser.describeHistoryData(history);
    }

    /**
     * Composes the VRM API history query.
     * @see "https://www.baeldung.com/java-url-encoding-decoding"
     * @return a URL encoded string
     */
    String composeHistoryQuery() throws UnsupportedEncodingException {

//            Map<String, String> requestParams = new HashMap<>();
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
        /**
         * idSite='383620';
         *
         * #echo "SITE: ${SITE}";
         *
         * OUTFILE="get-site-history-${idSite}.json";
         *
         * cd /home/dlcoyle/devel/vrm-api
         *
         * query_url="https://vrmapi.victronenergy.com/v2/installations/${idSite}/widgets/Graph"
         * query_url+='?'
         * query_url+='start=1713204523'
         * query_url+='&attributeIds%5B1%5D=143'
         */

        String queryURL="https://vrmapi.victronenergy.com/v2/installations/IdSite/widgets/Graph?";
        String requestParameters="start=1713204523";
        requestParameters+="&attributeIds%5B1%5D=143";
        //queryURL+=this.encodeValue(requestParameters);
        logger.debug("queryURL: {}", queryURL + requestParameters);

        String encodedQueryURL = queryURL + this.encodeValue(requestParameters);
//        try {
//            encodedQueryURL = encodeValue(queryURL);
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }

        logger.debug("Encoded QueryURL: {}", encodedQueryURL);

        return encodedQueryURL;

    }

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

//    /**
//     * Sends an HTTP GET request to the VRM system.
//     * @throws IOException
//     * @throws InterruptedException
//     * @throws URISyntaxException
//     */
//    public void xsendGetRequest() throws IOException, InterruptedException, URISyntaxException {
//        logger.info("sendGetRequest: ");
//
//        /**
//         * The Http Response object.
//         */
//        HttpResponse<String> response;
//
//        String authorizationHeader = "x-authorization: Token " + this.accessToken.getTokenString();
//        authorizationHeader = "Token " + this.accessToken.getTokenString();
//        //logger.debug("authorizationHeader: {}", authorizationHeader);
//
//        String requestURL = "https://vrmapi.victronenergy.com/v2/installations/383620/widgets/Graph?start=1713204523&attributeIds%5B1%5D=143";
//
//        logger.debug("requestURL: {}", requestURL);
//
//        /**
//         * The Http Client object.
//         */
//        try (HttpClient client = HttpClient.newHttpClient()) {
//            logger.debug("token: {}", this.accessToken.getTokenString());
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(new URI(requestURL)) // Replace with your URL
//                    .header("x-authorization",  authorizationHeader)
//                    .GET() // GET request
//                    .build();
//
//            // Send the request and receive the response
//            stopWatch.start();
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            stopWatch.end();
//            stopWatch.logDuration("VRM API History Elapsed Time");
//        }
//
//        // Print the status code and response body
//        System.out.println("Status Code: " + response.statusCode());
//        System.out.println("Response: " + response.body());
//        String jsonString  = response.body();
//        String replacementString = null;
//        logger.debug("jsonString: {}", jsonString);
//        int foundCount = 0;
//        String searchString = "\"143\"";
//        String replaceString = "\"timeseries\"";
//        if (jsonString.contains("\"143\":")) {
//            foundCount++;
//            logger.debug("found: {}", foundCount);
//            replacementString = jsonString.replaceAll(searchString, replaceString);
//        }
//        logger.debug("replacementString: {}", replacementString);
//
//        HistoryParser historyParser = new HistoryParser();
//
//        History history = historyParser.parseHistoryJSON(replacementString);
//    }

//    /**
//     * Sends an authentication request to the Victron VRM API server.
//     * @throws IOException
//     * @throws InterruptedException
//     */
//     public void xsendAuthenticationRequest() throws IOException, InterruptedException {
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
//         xdescribeAuthorization(getAuthorization());
//
//    }

    /**
     * Describes an authorization object.
     * @param authorization
     */
    void xdescribeAuthorization(Authorization authorization) {
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
