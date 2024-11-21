
package gov.usgs.vrm.installations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "instance",
    "dbusServiceType",
    "dbusPath"
})
public class DataAttribute {

    @JsonProperty("instance")
    private Long instance;
    @JsonProperty("dbusServiceType")
    private String dbusServiceType;
    @JsonProperty("dbusPath")
    private String dbusPath;

    @JsonProperty("instance")
    public Long getInstance() {
        return instance;
    }

    @JsonProperty("dbusServiceType")
    public String getDbusServiceType() {
        return dbusServiceType;
    }

    @JsonProperty("dbusPath")
    public String getDbusPath() {
        return dbusPath;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataAttribute.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("instance");
        sb.append('=');
        sb.append(((this.instance == null)?"<null>":this.instance));
        sb.append(',');
        sb.append("dbusServiceType");
        sb.append('=');
        sb.append(((this.dbusServiceType == null)?"<null>":this.dbusServiceType));
        sb.append(',');
        sb.append("dbusPath");
        sb.append('=');
        sb.append(((this.dbusPath == null)?"<null>":this.dbusPath));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
