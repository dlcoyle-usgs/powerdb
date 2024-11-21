
package gov.usgs.vrm.history;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "meta",
    "instance"
})

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Records {

    //V1: @JsonProperty("data")
    //V1: private Data data;
    //V2: @JsonProperty("data")
    //V2: private Map<String, Object> data;
    //V3: Restore the Data POJO Class:
    //V3: @JsonProperty("data")
    //V3: private Data data;
    //V3: public Data getData() {
    //V3:     return this.data;
    //V3:  }

    //V4: Back to the Map concept:
    @JsonProperty("data")
    public Map<String, ArrayList<ArrayList<String>>> data;
    //private Map<String, Object> data;

    @JsonProperty("meta")
    public Map<String, Object> meta;

    //V1: @JsonProperty("meta")
    //V1: @JsonIgnore
    //V1: private Meta meta;

    @JsonProperty("instance")
    private Long instance;

    //V2: @JsonProperty("data")
    //V2: public Map<String, Object> getData() {
    //V2:     return this.data;
    //V2: }
//    @JsonProperty("data")
//    public Map<String, Object> getData() {
//        return this.data;
//    }

    //V2: @JsonProperty("data")
    //V2: public void setData(Map<String, Object> data) {
    //V2:     this.data = data;
    //V2: }

    @JsonProperty("meta")
    public Map<String, Object> getMeta() {
        return this.meta;
    }

    @JsonProperty("meta")
    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
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
        sb.append(Records.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
