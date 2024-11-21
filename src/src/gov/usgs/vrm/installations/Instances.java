
package gov.usgs.vrm.installations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "278",
    "277"
})
public class Instances {

    @JsonProperty("278")
    private gov.usgs.vrm.installations._278 _278;
    @JsonProperty("277")
    private gov.usgs.vrm.installations._277 _277;

    @JsonProperty("278")
    public gov.usgs.vrm.installations._278 get278() {
        return _278;
    }

    @JsonProperty("277")
    public gov.usgs.vrm.installations._277 get277() {
        return _277;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Instances.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("_278");
        sb.append('=');
        sb.append(((this._278 == null)?"<null>":this._278));
        sb.append(',');
        sb.append("_277");
        sb.append('=');
        sb.append(((this._277 == null)?"<null>":this._277));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
