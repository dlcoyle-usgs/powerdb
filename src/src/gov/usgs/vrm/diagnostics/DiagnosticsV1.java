
package gov.usgs.vrm.diagnostics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "records",
    "num_records"
})
public class DiagnosticsV1 {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("records")
    private List<Record> records = new ArrayList<Record>();
    @JsonProperty("num_records")
    private Long numRecords;

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("records")
    public List<Record> getRecords() {
        return records;
    }

    @JsonProperty("num_records")
    public Long getNumRecords() {
        return numRecords;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DiagnosticsV1.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("success");
        sb.append('=');
        sb.append(((this.success == null)?"<null>":this.success));
        sb.append(',');
        sb.append("records");
        sb.append('=');
        sb.append(((this.records == null)?"<null>":this.records));
        sb.append(',');
        sb.append("numRecords");
        sb.append('=');
        sb.append(((this.numRecords == null)?"<null>":this.numRecords));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
