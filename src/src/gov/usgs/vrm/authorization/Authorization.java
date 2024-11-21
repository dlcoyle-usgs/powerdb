
package gov.usgs.vrm.authorization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "token",
    "idUser",
    "verification_mode",
    "verification_sent"
})

/**
 * Provides the parsed JWT token VRM authorization object representation.
 */
public class Authorization {

    @JsonProperty("token")
    private String token;
    @JsonProperty("idUser")
    private Long idUser;
    @JsonProperty("verification_mode")
    private String verificationMode;
    @JsonProperty("verification_sent")
    private Boolean verificationSent;

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("idUser")
    public Long getIdUser() {
        return idUser;
    }

    @JsonProperty("verification_mode")
    public String getVerificationMode() {
        return verificationMode;
    }

    @JsonProperty("verification_sent")
    public Boolean getVerificationSent() {
        return verificationSent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Authorization.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null)?"<null>":this.token));
        sb.append(',');
        sb.append("idUser");
        sb.append('=');
        sb.append(((this.idUser == null)?"<null>":this.idUser));
        sb.append(',');
        sb.append("verificationMode");
        sb.append('=');
        sb.append(((this.verificationMode == null)?"<null>":this.verificationMode));
        sb.append(',');
        sb.append("verificationSent");
        sb.append('=');
        sb.append(((this.verificationSent == null)?"<null>":this.verificationSent));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
