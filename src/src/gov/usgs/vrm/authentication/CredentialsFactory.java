package gov.usgs.vrm.authentication;

    public class CredentialsFactory {

    /**
     * Provides the Victron VRM API user credentials information.
     * @return the Victron VRM API user credentials information.
     */
    public static Credentials getCredentials() {

        String VRM_USERID = System.getenv("VRM_USERID");

        String VRM_PASSWD = System.getenv("VRM_PASSWD");

        if (VRM_USERID == null) throw new IllegalStateException("VRM_USERID: Environment variable is missing from system.");

        if (VRM_PASSWD == null) throw new IllegalStateException("VRM_PASSWD: Environment variable is missing from system.");

        Credentials credentials = new Credentials(VRM_USERID, VRM_PASSWD);

        return credentials;
    }

}
