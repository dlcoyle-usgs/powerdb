
package gov.usgs.vrm.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timeseries"
})
public class DataV1 {

    @JsonProperty("timeseries")
    private List<List<String>> timeseries = new ArrayList<List<String>>();

    @JsonProperty("timeseries")
    public List<List<String>> getTimeseries() {
        return timeseries;
    }

//    @JsonProperty("timeseries")
//    private List<List<Long>> timeseries = new ArrayList<List<Long>>();
//
//    @JsonProperty("timeseries")
//    public List<List<Long>> getTimeseries() {
//        return timeseries;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataV1.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("timeseries");
        sb.append('=');
        sb.append(((this.timeseries == null)?"<null>":this.timeseries));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
