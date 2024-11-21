
package gov.usgs.vrm.diagnostics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nameEnum",
    "valueEnum"
})
public class DataAttributeEnumValue {

    @JsonProperty("nameEnum")
    private String nameEnum;
    @JsonProperty("valueEnum")
    private Long valueEnum;

    @JsonProperty("nameEnum")
    public String getNameEnum() {
        return nameEnum;
    }

    @JsonProperty("valueEnum")
    public Long getValueEnum() {
        return valueEnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataAttributeEnumValue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nameEnum");
        sb.append('=');
        sb.append(((this.nameEnum == null)?"<null>":this.nameEnum));
        sb.append(',');
        sb.append("valueEnum");
        sb.append('=');
        sb.append(((this.valueEnum == null)?"<null>":this.valueEnum));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
