
package gov.usgs.vrm.diagnostics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idSite",
    "timestamp",
    "Device",
    "instance",
    "idDataAttribute",
    "description",
    "formatWithUnit",
    "dbusServiceType",
    "dbusPath",
    "code",
    "bitmask",
    "formattedValue",
    "rawValue",
    "dataAttributeEnumValues",
    "id"
})
public class V1 {

    @JsonProperty("idSite")
    private Long idSite;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("Device")
    private String device;
    @JsonProperty("instance")
    private Long instance;
    @JsonProperty("idDataAttribute")
    private Long idDataAttribute;
    @JsonProperty("description")
    private String description;
    @JsonProperty("formatWithUnit")
    private String formatWithUnit;
    @JsonProperty("dbusServiceType")
    private String dbusServiceType;
    @JsonProperty("dbusPath")
    private String dbusPath;
    @JsonProperty("code")
    private String code;
    @JsonProperty("bitmask")
    private Long bitmask;
    @JsonProperty("formattedValue")
    private String formattedValue;
    @JsonProperty("rawValue")
    private Long rawValue;
    @JsonProperty("dataAttributeEnumValues")
    private List<DataAttributeEnumValue> dataAttributeEnumValues = new ArrayList<DataAttributeEnumValue>();
    @JsonProperty("id")
    private Long id;

    @JsonProperty("idSite")
    public Long getIdSite() {
        return idSite;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("Device")
    public String getDevice() {
        return device;
    }

    @JsonProperty("instance")
    public Long getInstance() {
        return instance;
    }

    @JsonProperty("idDataAttribute")
    public Long getIdDataAttribute() {
        return idDataAttribute;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("formatWithUnit")
    public String getFormatWithUnit() {
        return formatWithUnit;
    }

    @JsonProperty("dbusServiceType")
    public String getDbusServiceType() {
        return dbusServiceType;
    }

    @JsonProperty("dbusPath")
    public String getDbusPath() {
        return dbusPath;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("bitmask")
    public Long getBitmask() {
        return bitmask;
    }

    @JsonProperty("formattedValue")
    public String getFormattedValue() {
        return formattedValue;
    }

    @JsonProperty("rawValue")
    public Long getRawValue() {
        return rawValue;
    }

    @JsonProperty("dataAttributeEnumValues")
    public List<DataAttributeEnumValue> getDataAttributeEnumValues() {
        return dataAttributeEnumValues;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V1.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("idSite");
        sb.append('=');
        sb.append(((this.idSite == null)?"<null>":this.idSite));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("device");
        sb.append('=');
        sb.append(((this.device == null)?"<null>":this.device));
        sb.append(',');
        sb.append("instance");
        sb.append('=');
        sb.append(((this.instance == null)?"<null>":this.instance));
        sb.append(',');
        sb.append("idDataAttribute");
        sb.append('=');
        sb.append(((this.idDataAttribute == null)?"<null>":this.idDataAttribute));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("formatWithUnit");
        sb.append('=');
        sb.append(((this.formatWithUnit == null)?"<null>":this.formatWithUnit));
        sb.append(',');
        sb.append("dbusServiceType");
        sb.append('=');
        sb.append(((this.dbusServiceType == null)?"<null>":this.dbusServiceType));
        sb.append(',');
        sb.append("dbusPath");
        sb.append('=');
        sb.append(((this.dbusPath == null)?"<null>":this.dbusPath));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("bitmask");
        sb.append('=');
        sb.append(((this.bitmask == null)?"<null>":this.bitmask));
        sb.append(',');
        sb.append("formattedValue");
        sb.append('=');
        sb.append(((this.formattedValue == null)?"<null>":this.formattedValue));
        sb.append(',');
        sb.append("rawValue");
        sb.append('=');
        sb.append(((this.rawValue == null)?"<null>":this.rawValue));
        sb.append(',');
        sb.append("dataAttributeEnumValues");
        sb.append('=');
        sb.append(((this.dataAttributeEnumValues == null)?"<null>":this.dataAttributeEnumValues));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
