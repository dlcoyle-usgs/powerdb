
package gov.usgs.vrm.installations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "update_settings",
    "settings",
    "diagnostics",
    "share",
    "vnc",
    "mqtt_rpc",
    "vebus",
    "twoway",
    "exact_location",
    "nodered",
    "nodered_dash",
    "nodered_dash_v2",
    "signalk",
    "paygo",
    "dess_config"
})
public class ViewPermissions {

    @JsonProperty("update_settings")
    private Boolean updateSettings;
    @JsonProperty("settings")
    private Boolean settings;
    @JsonProperty("diagnostics")
    private Boolean diagnostics;
    @JsonProperty("share")
    private Boolean share;
    @JsonProperty("vnc")
    private Boolean vnc;
    @JsonProperty("mqtt_rpc")
    private Boolean mqttRpc;
    @JsonProperty("vebus")
    private Boolean vebus;
    @JsonProperty("twoway")
    private Boolean twoway;
    @JsonProperty("exact_location")
    private Boolean exactLocation;
    @JsonProperty("nodered")
    private Boolean nodered;
    @JsonProperty("nodered_dash")
    private Boolean noderedDash;
    @JsonProperty("nodered_dash_v2")
    private Boolean noderedDashV2;
    @JsonProperty("signalk")
    private Boolean signalk;
    @JsonProperty("paygo")
    private Boolean paygo;
    @JsonProperty("dess_config")
    private Boolean dessConfig;

    @JsonProperty("update_settings")
    public Boolean getUpdateSettings() {
        return updateSettings;
    }

    @JsonProperty("settings")
    public Boolean getSettings() {
        return settings;
    }

    @JsonProperty("diagnostics")
    public Boolean getDiagnostics() {
        return diagnostics;
    }

    @JsonProperty("share")
    public Boolean getShare() {
        return share;
    }

    @JsonProperty("vnc")
    public Boolean getVnc() {
        return vnc;
    }

    @JsonProperty("mqtt_rpc")
    public Boolean getMqttRpc() {
        return mqttRpc;
    }

    @JsonProperty("vebus")
    public Boolean getVebus() {
        return vebus;
    }

    @JsonProperty("twoway")
    public Boolean getTwoway() {
        return twoway;
    }

    @JsonProperty("exact_location")
    public Boolean getExactLocation() {
        return exactLocation;
    }

    @JsonProperty("nodered")
    public Boolean getNodered() {
        return nodered;
    }

    @JsonProperty("nodered_dash")
    public Boolean getNoderedDash() {
        return noderedDash;
    }

    @JsonProperty("nodered_dash_v2")
    public Boolean getNoderedDashV2() {
        return noderedDashV2;
    }

    @JsonProperty("signalk")
    public Boolean getSignalk() {
        return signalk;
    }

    @JsonProperty("paygo")
    public Boolean getPaygo() {
        return paygo;
    }

    @JsonProperty("dess_config")
    public Boolean getDessConfig() {
        return dessConfig;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ViewPermissions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("updateSettings");
        sb.append('=');
        sb.append(((this.updateSettings == null)?"<null>":this.updateSettings));
        sb.append(',');
        sb.append("settings");
        sb.append('=');
        sb.append(((this.settings == null)?"<null>":this.settings));
        sb.append(',');
        sb.append("diagnostics");
        sb.append('=');
        sb.append(((this.diagnostics == null)?"<null>":this.diagnostics));
        sb.append(',');
        sb.append("share");
        sb.append('=');
        sb.append(((this.share == null)?"<null>":this.share));
        sb.append(',');
        sb.append("vnc");
        sb.append('=');
        sb.append(((this.vnc == null)?"<null>":this.vnc));
        sb.append(',');
        sb.append("mqttRpc");
        sb.append('=');
        sb.append(((this.mqttRpc == null)?"<null>":this.mqttRpc));
        sb.append(',');
        sb.append("vebus");
        sb.append('=');
        sb.append(((this.vebus == null)?"<null>":this.vebus));
        sb.append(',');
        sb.append("twoway");
        sb.append('=');
        sb.append(((this.twoway == null)?"<null>":this.twoway));
        sb.append(',');
        sb.append("exactLocation");
        sb.append('=');
        sb.append(((this.exactLocation == null)?"<null>":this.exactLocation));
        sb.append(',');
        sb.append("nodered");
        sb.append('=');
        sb.append(((this.nodered == null)?"<null>":this.nodered));
        sb.append(',');
        sb.append("noderedDash");
        sb.append('=');
        sb.append(((this.noderedDash == null)?"<null>":this.noderedDash));
        sb.append(',');
        sb.append("noderedDashV2");
        sb.append('=');
        sb.append(((this.noderedDashV2 == null)?"<null>":this.noderedDashV2));
        sb.append(',');
        sb.append("signalk");
        sb.append('=');
        sb.append(((this.signalk == null)?"<null>":this.signalk));
        sb.append(',');
        sb.append("paygo");
        sb.append('=');
        sb.append(((this.paygo == null)?"<null>":this.paygo));
        sb.append(',');
        sb.append("dessConfig");
        sb.append('=');
        sb.append(((this.dessConfig == null)?"<null>":this.dessConfig));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
