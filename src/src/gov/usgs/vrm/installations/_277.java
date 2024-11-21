
package gov.usgs.vrm.installations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rawValue",
    "formattedValue",
    "textValue",
    "timestamp"
})
public class _277 {

    @JsonProperty("rawValue")
    private String rawValue;
    @JsonProperty("formattedValue")
    private String formattedValue;
    @JsonProperty("textValue")
    private String textValue;
    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("rawValue")
    public String getRawValue() {
        return rawValue;
    }

    @JsonProperty("formattedValue")
    public String getFormattedValue() {
        return formattedValue;
    }

    @JsonProperty("textValue")
    public String getTextValue() {
        return textValue;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_277 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("rawValue");
        sb.append('=');
        sb.append(((this.rawValue == null)?"<null>":this.rawValue));
        sb.append(',');
        sb.append("formattedValue");
        sb.append('=');
        sb.append(((this.formattedValue == null)?"<null>":this.formattedValue));
        sb.append(',');
        sb.append("textValue");
        sb.append('=');
        sb.append(((this.textValue == null)?"<null>":this.textValue));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
