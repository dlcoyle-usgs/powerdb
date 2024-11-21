package gov.usgs.vrm.parameters;

public final class ParameterCdFactory {
    public String id = null;
    public String code = null;
    public String unit = null;
    public String format = null;
    public String description = null;
    public String source = null;
    public String citation = null;

    private static ParameterCdFactory INSTANCE;

    private ParameterCdFactory() {
    }

    public static ParameterCdFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ParameterCdFactory();
        }
        return INSTANCE;
    }

     /**
     * Provides USGS NWIS format compatible parameter code class instances.
      * Minimal requirement is the parameter code ID.
     */
    public static ParameterCd getParameterCd(String idString) {
        return new ParameterCd(
            normalizeIdString(idString)
        );
    }

    public static ParameterCd getParameterCd(String idString, String codeString) {
        return new ParameterCd(
        normalizeIdString(idString),
        codeString
        );
    }

    public static ParameterCd getParameterCd(String idString, String codeString, String unitString) {
        return new ParameterCd(
            normalizeIdString(idString),
            codeString,
            unitString
        );
    }

    public static ParameterCd getParameterCd(String idString, String codeString, String unitString, String descriptionString) {
        return new ParameterCd(
            normalizeIdString(idString),
            codeString,
            unitString,
            descriptionString
        );
    }

    /**
     * Answers a string representation of the object.
     */
    public String toString () {
        return String.format("parameterCdFactory: %s", "singleton instance" );
    }

    /**
     * Normalizes the Victron VRM parameter code id string to mesh with the more well-known USGS NWIS parameter code dictionary format.
     * @param numberString
     * @return
     */
    protected static String normalizeIdString (String numberString) {
        Integer numberInteger = Integer.parseInt(numberString);
        return String.format("%05d", numberInteger);
    }
}
