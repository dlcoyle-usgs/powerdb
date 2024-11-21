package gov.usgs.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;

import gov.usgs.vrm.authorization.AccessToken;
/**
 * Make sure to replace `"http://example.com"` with the URL you wish to send the request to, and `"your_access_token_here"` with your actual access token.
 * This code uses Java's built-in `java.net.http.HttpClient` which is available from Java 11 onwards.
 * If you're using an older version of Java, you might need to use a different library like Apache HttpClient.
 */
public class HttpClientUtil {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        /**
         * The access token used for the Victron VRM API.
         */
        //String ACCESS_TOKEN = AccessToken.getAccessToken();

        /**
         * The HttpClient object.
         */
        HttpResponse<String> response;

        try (HttpClient client = HttpClient.newHttpClient()) {

            /**
             * Create a request.
             */
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://example.com")) // Replace with your URL
                    .header("Authorization", "Bearer your_access_token_here") // Replace with your access token
                    .GET() // GET request
                    .build();

            // Send the request and receive the response
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        // Print the status code and response body
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response: " + response.body());
    }
}


