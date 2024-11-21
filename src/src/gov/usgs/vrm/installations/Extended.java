
package gov.usgs.vrm.installations;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idDataAttribute",
    "code",
    "description",
    "formatWithUnit",
    "dataType",
    "idDeviceType",
    "textValue",
    "instance",
    "timestamp",
    "dbusServiceType",
    "dbusPath",
    "rawValue",
    "formattedValue",
    "dataAttributeEnumValues",
    "dataAttributes",
    "instances"
})
public class Extended {

    @JsonProperty("idDataAttribute")
    private Object idDataAttribute;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("formatWithUnit")
    private String formatWithUnit;
    @JsonProperty("dataType")
    private String dataType;
    @JsonProperty("idDeviceType")
    private Long idDeviceType;
    @JsonProperty("textValue")
    private String textValue;
    @JsonProperty("instance")
    private String instance;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("dbusServiceType")
    private String dbusServiceType;
    @JsonProperty("dbusPath")
    private String dbusPath;
    @JsonProperty("rawValue")
    private String rawValue;
    @JsonProperty("formattedValue")
    private String formattedValue;
    @JsonProperty("dataAttributeEnumValues")
    private List<DataAttributeEnumValue> dataAttributeEnumValues = new ArrayList<DataAttributeEnumValue>();
    @JsonProperty("dataAttributes")
    private List<DataAttribute> dataAttributes = new ArrayList<DataAttribute>();
    @JsonProperty("instances")
    private Instances instances;

    @JsonProperty("idDataAttribute")
    public Object getIdDataAttribute() {
        return idDataAttribute;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("formatWithUnit")
    public String getFormatWithUnit() {
        return formatWithUnit;
    }

    @JsonProperty("dataType")
    public String getDataType() {
        return dataType;
    }

    @JsonProperty("idDeviceType")
    public Long getIdDeviceType() {
        return idDeviceType;
    }

    @JsonProperty("textValue")
    public String getTextValue() {
        return textValue;
    }

    @JsonProperty("instance")
    public String getInstance() {
        return instance;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("dbusServiceType")
    public String getDbusServiceType() {
        return dbusServiceType;
    }

    @JsonProperty("dbusPath")
    public String getDbusPath() {
        return dbusPath;
    }

    @JsonProperty("rawValue")
    public String getRawValue() {
        return rawValue;
    }

    @JsonProperty("formattedValue")
    public String getFormattedValue() {
        return formattedValue;
    }

    @JsonProperty("dataAttributeEnumValues")
    public List<DataAttributeEnumValue> getDataAttributeEnumValues() {
        return dataAttributeEnumValues;
    }

    @JsonProperty("dataAttributes")
    public List<DataAttribute> getDataAttributes() {
        return dataAttributes;
    }

    @JsonProperty("instances")
    public Instances getInstances() {
        return instances;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Extended.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("idDataAttribute");
        sb.append('=');
        sb.append(((this.idDataAttribute == null)?"<null>":this.idDataAttribute));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("formatWithUnit");
        sb.append('=');
        sb.append(((this.formatWithUnit == null)?"<null>":this.formatWithUnit));
        sb.append(',');
        sb.append("dataType");
        sb.append('=');
        sb.append(((this.dataType == null)?"<null>":this.dataType));
        sb.append(',');
        sb.append("idDeviceType");
        sb.append('=');
        sb.append(((this.idDeviceType == null)?"<null>":this.idDeviceType));
        sb.append(',');
        sb.append("textValue");
        sb.append('=');
        sb.append(((this.textValue == null)?"<null>":this.textValue));
        sb.append(',');
        sb.append("instance");
        sb.append('=');
        sb.append(((this.instance == null)?"<null>":this.instance));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        sb.append("dbusServiceType");
        sb.append('=');
        sb.append(((this.dbusServiceType == null)?"<null>":this.dbusServiceType));
        sb.append(',');
        sb.append("dbusPath");
        sb.append('=');
        sb.append(((this.dbusPath == null)?"<null>":this.dbusPath));
        sb.append(',');
        sb.append("rawValue");
        sb.append('=');
        sb.append(((this.rawValue == null)?"<null>":this.rawValue));
        sb.append(',');
        sb.append("formattedValue");
        sb.append('=');
        sb.append(((this.formattedValue == null)?"<null>":this.formattedValue));
        sb.append(',');
        sb.append("dataAttributeEnumValues");
        sb.append('=');
        sb.append(((this.dataAttributeEnumValues == null)?"<null>":this.dataAttributeEnumValues));
        sb.append(',');
        sb.append("dataAttributes");
        sb.append('=');
        sb.append(((this.dataAttributes == null)?"<null>":this.dataAttributes));
        sb.append(',');
        sb.append("instances");
        sb.append('=');
        sb.append(((this.instances == null)?"<null>":this.instances));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
