
package gov.usgs.vrm.historyv1.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code",
    "description",
    "formatValueOnly",
    "formatWithUnit",
    "axisGroup"
})
public class Timeseries {

    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("formatValueOnly")
    private String formatValueOnly;
    @JsonProperty("formatWithUnit")
    private String formatWithUnit;
    @JsonProperty("axisGroup")
    private String axisGroup;

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("formatValueOnly")
    public String getFormatValueOnly() {
        return formatValueOnly;
    }

    @JsonProperty("formatWithUnit")
    public String getFormatWithUnit() {
        return formatWithUnit;
    }

    @JsonProperty("axisGroup")
    public String getAxisGroup() {
        return axisGroup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Timeseries.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("formatValueOnly");
        sb.append('=');
        sb.append(((this.formatValueOnly == null)?"<null>":this.formatValueOnly));
        sb.append(',');
        sb.append("formatWithUnit");
        sb.append('=');
        sb.append(((this.formatWithUnit == null)?"<null>":this.formatWithUnit));
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
