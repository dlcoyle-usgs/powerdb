
package gov.usgs.vrm.installations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idTag",
    "name",
    "automatic",
    "source"
})
public class Tag {

    @JsonProperty("idTag")
    private Long idTag;
    @JsonProperty("name")
    private String name;
    @JsonProperty("automatic")
    private Boolean automatic;
    @JsonProperty("source")
    private String source;

    @JsonProperty("idTag")
    public Long getIdTag() {
        return idTag;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("automatic")
    public Boolean getAutomatic() {
        return automatic;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Tag.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("idTag");
        sb.append('=');
        sb.append(((this.idTag == null)?"<null>":this.idTag));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("automatic");
        sb.append('=');
        sb.append(((this.automatic == null)?"<null>":this.automatic));
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(((this.source == null)?"<null>":this.source));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
