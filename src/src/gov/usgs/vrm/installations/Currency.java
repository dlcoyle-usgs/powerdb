
package gov.usgs.vrm.installations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idCurrency",
    "code",
    "name",
    "sign"
})
public class Currency {

    @JsonProperty("idCurrency")
    private Long idCurrency;
    @JsonProperty("code")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sign")
    private String sign;

    @JsonProperty("idCurrency")
    public Long getIdCurrency() {
        return idCurrency;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("sign")
    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Currency.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("idCurrency");
        sb.append('=');
        sb.append(((this.idCurrency == null)?"<null>":this.idCurrency));
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("sign");
        sb.append('=');
        sb.append(((this.sign == null)?"<null>":this.sign));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
