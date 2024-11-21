package gov.usgs.vrm.retrievals;

public class RequestObject {
    /**
     * The Request URL object.
     */
    public String requestURL = null;

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
        this.requestURL = requestURL;
    }

    public String toString() {
        return String.format("requestURL=[%s]", this.requestURL);
    }
}
