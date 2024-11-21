
package gov.usgs.vrm.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "timeseries"
})
public class Meta {

    @JsonProperty("timeseries")
    private Timeseries timeseries;

    @JsonProperty("timeseries")
    public Timeseries getTimeseries() {
        return timeseries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Meta.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
