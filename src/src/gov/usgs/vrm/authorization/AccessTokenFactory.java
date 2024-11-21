package gov.usgs.vrm.authorization;

    public class AccessTokenFactory {

    public static String ACCESS_TOKEN_STRING = "6ca9e08f809b21f6f4fe3082bf7aeb36e1ed391dc01e9a139fe14d7907473090";

    /**
     * Answers the Victron VRM API access token value.
     * @return the Victron VRM API access token value.
     */
    public static AccessToken getAccessToken() {

        ACCESS_TOKEN_STRING = System.getenv("VRM_TOKEN");

        if (ACCESS_TOKEN_STRING == null) throw new IllegalStateException("VRM_TOKEN: Environment variable is missing from system.");

        AccessToken accessToken = new AccessToken(ACCESS_TOKEN_STRING);

        return accessToken;
    }

}
