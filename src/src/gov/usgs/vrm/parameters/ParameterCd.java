package gov.usgs.vrm.parameters;

import gov.usgs.vrm.history.History;

/**
 * Provides a USGS NWIS format compatible parameter code class.
 */
public class ParameterCd {

    //NWIS API ParameterCd:
    public String id = null;
    public String unit = null;
    public String format = null;
    public String source = null;
    public String citation = null;

    // VRM API Meta:
    public String code = null;
    public String description = null;
    public String formatValueOnly = null;
    public String formatWithUnit = null;
    public String axisGroup = null;

    public ParameterCd(String idString) {
        this.id = normalizeIdString(idString);
    }

    public ParameterCd(String idString, String codeString) {
        this.id = normalizeIdString(idString);
        this.code = codeString;
    }

    public ParameterCd(String idString, String codeString, String unitString) {
        this.id = normalizeIdString(idString);
        this.code = codeString;
        this.unit = unitString;
    }

    public ParameterCd(String idString, String codeString, String unitString, String descriptionString) {
        this.id = normalizeIdString(idString);
        this.code = codeString;
        this.unit = unitString;
        this.description = descriptionString;
    }

    /**
     * Normalizes the Victron VRM parameter code id string to mesh with the more well-known USGS NWIS parameter code dictionary format.
     * @param numberString
     * @return
     */
    protected String normalizeIdString (String numberString) {
        Integer numberInteger = Integer.parseInt(numberString);
        return String.format("%05d", numberInteger);

    }

    /**
     * Answers a string representation of the object.
     */
    public String toString1 () {
        return String.format("parameterCd: id=%s, code=%s", this.id, this.code );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ParameterCd.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');

        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');

        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');

        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');

        sb.append("axisGroup");
        sb.append('=');
        sb.append(((this.axisGroup == null)?"<null>":this.axisGroup));
        sb.append(',');

        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
