
package gov.usgs.vrm.historyv1.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "records"
})
public class History {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("records")
    private Records records;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("records")
    public Records getRecords() {
        return records;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(History.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("success");
        sb.append('=');
        sb.append(((this.success == null)?"<null>":this.success));
        sb.append(',');
        sb.append("records");
        sb.append('=');
        sb.append(((this.records == null)?"<null>":this.records));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
