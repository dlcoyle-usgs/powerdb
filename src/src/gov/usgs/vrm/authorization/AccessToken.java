package gov.usgs.vrm.authorization;

public class AccessToken {

    public static final String PWMASK = "XXXXXXXX";

    protected String token = PWMASK;

    protected AccessToken(String token) {
        this.token = token;
    }

    /**
     * Provides the Victron VRM API access token authorization information.
     */
    public String getTokenString() {
        return this.token;
    }

    /**
     * Answers a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
        return String.format("token: %s", this.token);
    }

}
