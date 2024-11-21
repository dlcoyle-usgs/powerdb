
package gov.usgs.vrm.history;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "143"
})
public class Data {

    @JsonProperty("143")
    private List<List<String>> timeseries = new ArrayList<List<String>>();

    @JsonProperty("143")
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
        sb.append(Data.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        //V1: sb.append("timeseries");
        //V1: sb.append('=');
        //V1: sb.append(((this.timeseries == null)?"<null>":this.timeseries));
        //V1: sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
