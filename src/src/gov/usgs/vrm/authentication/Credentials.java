package gov.usgs.vrm.authentication;

public class Credentials {

    public static final String PWMASK = "XXXXXXXX";

    protected String userid = "dlcoyle@usgs.gov";

    protected String passwd = PWMASK;

    protected Credentials(String userid, String passwd) {
        this.userid = userid;
        this.passwd = passwd;
    }

    /**
     * Provides the Victron VRM API userid credentials information.
     * @return the Victron VRM API userid credentials information.
     */
    public String getUserid() {
        return this.userid;
    }

    /**
     * Provides the Victron VRM API passwd credentials information.
     * @return the Victron VRM API passwd credentials information.
     */
    public String getPasswd() {
        return this.passwd;
    }

    /**
     * Answers a string representation of the object.
     * @return a string representation of the object
     */
    public String toString() {
        return String.format("userid: %s, passwd: %s", this.userid, PWMASK);
    }

}
