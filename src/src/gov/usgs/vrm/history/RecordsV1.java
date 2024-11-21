
package gov.usgs.vrm.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    //V1: "meta",
    "instance"
})

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordsV1 {

    //V1: @JsonProperty("data")
    //V1: private Data data;
    @JsonProperty("data")
    private Map<String, Object> data;

    //V1: @JsonProperty("meta")
    //V1: @JsonIgnore
    //V1: private Meta meta;

    @JsonProperty("instance")
    private Long instance;

    @JsonProperty("data")
    public Map<String, Object> getData() {
        return this.data;
    }

    @JsonProperty("data")
    public void setDetails(Map<String, Object> data) {
        this.data = data;
    }

    //V1: @JsonProperty("data")
    //V1: public Data getData() {
    //V1:    return data;
    //V1: }

    //V1: @JsonProperty("meta")
    //V1: public Meta getMeta() {
    //V1:     return meta;
    //V1: }

    @JsonProperty("instance")
    public Long getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RecordsV1.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        //V1: sb.append("meta");
        //V1: sb.append('=');
        //V1: sb.append(((this.meta == null)?"<null>":this.meta));
        //V1: sb.append(',');
        sb.append("instance");
        sb.append('=');
        sb.append(((this.instance == null)?"<null>":this.instance));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
