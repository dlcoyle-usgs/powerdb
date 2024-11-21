
package gov.usgs.vrm.installations;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"idCurrency", "currencyCode", "currencySign", "currencyName"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idSite",
    "accessLevel",
    "owner",
    "is_admin",
    "name",
    "identifier",
    "idUser",
    "pvMax",
    "timezone",
    "phonenumber",
    "notes",
    "geofence",
    "geofenceEnabled",
    "realtimeUpdates",
    "hasMains",
    "hasGenerator",
    "noDataAlarmTimeout",
    "alarmMonitoring",
    "invalidVRMAuthTokenUsedInLogRequest",
    "syscreated",
    "isPaygo",
    "paygoCurrency",
    "paygoTotalAmount",
    "inverterChargerControl",
    "shared",
    "device_icon",
    "alarm",
    "last_timestamp",
    "tags",
    "new_tags",
    "current_time",
    "timezone_offset",
    "images",
    "view_permissions",
    "extended",
    "demo_mode",
    "mqtt_webhost",
    "mqtt_host",
    "high_workload",
    "prices_unavailable",
    "currency",
    "current_alarms",
    "num_alarms",
    "last_location_timestamp",
    "avatar_url",
    "nodered_running",
    "guiHash",
    "venus_gui_v2"
})
public class Record {

    @JsonProperty("idSite")
    private Long idSite;
    @JsonProperty("accessLevel")
    private Long accessLevel;
    @JsonProperty("owner")
    private Boolean owner;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
    @JsonProperty("name")
    private String name;
    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("idUser")
    private Long idUser;
    @JsonProperty("pvMax")
    private Long pvMax;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("phonenumber")
    private Object phonenumber;
    @JsonProperty("notes")
    private Object notes;
    @JsonProperty("geofence")
    private Object geofence;
    @JsonProperty("geofenceEnabled")
    private Boolean geofenceEnabled;
    @JsonProperty("realtimeUpdates")
    private Boolean realtimeUpdates;
    @JsonProperty("hasMains")
    private Long hasMains;
    @JsonProperty("hasGenerator")
    private Long hasGenerator;
    @JsonProperty("noDataAlarmTimeout")
    private Object noDataAlarmTimeout;
    @JsonProperty("alarmMonitoring")
    private Long alarmMonitoring;
    @JsonProperty("invalidVRMAuthTokenUsedInLogRequest")
    private Long invalidVRMAuthTokenUsedInLogRequest;
    @JsonProperty("syscreated")
    private Long syscreated;
    @JsonProperty("isPaygo")
    private Long isPaygo;
    @JsonProperty("paygoCurrency")
    private Object paygoCurrency;
    @JsonProperty("paygoTotalAmount")
    private Object paygoTotalAmount;
    @JsonProperty("inverterChargerControl")
    private Long inverterChargerControl;
    @JsonProperty("shared")
    private Boolean shared;
    @JsonProperty("device_icon")
    private String deviceIcon;
    @JsonProperty("alarm")
    private Boolean alarm;
    @JsonProperty("last_timestamp")
    private Long lastTimestamp;
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<Tag>();
    @JsonProperty("new_tags")
    private Boolean newTags;
    @JsonProperty("current_time")
    private String currentTime;
    @JsonProperty("timezone_offset")
    private Long timezoneOffset;
    @JsonProperty("images")
    private Boolean images;
    @JsonProperty("view_permissions")
    private ViewPermissions viewPermissions;
    @JsonProperty("extended")
    private List<Extended> extended = new ArrayList<Extended>();
    @JsonProperty("demo_mode")
    private Boolean demoMode;
    @JsonProperty("mqtt_webhost")
    private String mqttWebhost;
    @JsonProperty("mqtt_host")
    private String mqttHost;
    @JsonProperty("high_workload")
    private Boolean highWorkload;
    @JsonProperty("prices_unavailable")
    private Boolean pricesUnavailable;
    @JsonProperty("currency")
    private Currency currency;
    @JsonProperty("current_alarms")
    private List<Object> currentAlarms = new ArrayList<Object>();
    @JsonProperty("num_alarms")
    private Long numAlarms;
    @JsonProperty("last_location_timestamp")
    private Boolean lastLocationTimestamp;
    @JsonProperty("avatar_url")
    private Object avatarUrl;
    @JsonProperty("nodered_running")
    private Boolean noderedRunning;
    @JsonProperty("guiHash")
    private Object guiHash;
    @JsonProperty("venus_gui_v2")
    private Boolean venusGuiV2;

    @JsonProperty("idSite")
    public Long getIdSite() {
        return idSite;
    }

    @JsonProperty("accessLevel")
    public Long getAccessLevel() {
        return accessLevel;
    }

    @JsonProperty("owner")
    public Boolean getOwner() {
        return owner;
    }

    @JsonProperty("is_admin")
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("identifier")
    public String getIdentifier() {
        return identifier;
    }

    @JsonProperty("idUser")
    public Long getIdUser() {
        return idUser;
    }

    @JsonProperty("pvMax")
    public Long getPvMax() {
        return pvMax;
    }

    @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    @JsonProperty("phonenumber")
    public Object getPhonenumber() {
        return phonenumber;
    }

    @JsonProperty("notes")
    public Object getNotes() {
        return notes;
    }

    @JsonProperty("geofence")
    public Object getGeofence() {
        return geofence;
    }

    @JsonProperty("geofenceEnabled")
    public Boolean getGeofenceEnabled() {
        return geofenceEnabled;
    }

    @JsonProperty("realtimeUpdates")
    public Boolean getRealtimeUpdates() {
        return realtimeUpdates;
    }

    @JsonProperty("hasMains")
    public Long getHasMains() {
        return hasMains;
    }

    @JsonProperty("hasGenerator")
    public Long getHasGenerator() {
        return hasGenerator;
    }

    @JsonProperty("noDataAlarmTimeout")
    public Object getNoDataAlarmTimeout() {
        return noDataAlarmTimeout;
    }

    @JsonProperty("alarmMonitoring")
    public Long getAlarmMonitoring() {
        return alarmMonitoring;
    }

    @JsonProperty("invalidVRMAuthTokenUsedInLogRequest")
    public Long getInvalidVRMAuthTokenUsedInLogRequest() {
        return invalidVRMAuthTokenUsedInLogRequest;
    }

    @JsonProperty("syscreated")
    public Long getSyscreated() {
        return syscreated;
    }

    @JsonProperty("isPaygo")
    public Long getIsPaygo() {
        return isPaygo;
    }

    @JsonProperty("paygoCurrency")
    public Object getPaygoCurrency() {
        return paygoCurrency;
    }

    @JsonProperty("paygoTotalAmount")
    public Object getPaygoTotalAmount() {
        return paygoTotalAmount;
    }

    @JsonProperty("inverterChargerControl")
    public Long getInverterChargerControl() {
        return inverterChargerControl;
    }

    @JsonProperty("shared")
    public Boolean getShared() {
        return shared;
    }

    @JsonProperty("device_icon")
    public String getDeviceIcon() {
        return deviceIcon;
    }

    @JsonProperty("alarm")
    public Boolean getAlarm() {
        return alarm;
    }

    @JsonProperty("last_timestamp")
    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    @JsonProperty("tags")
    public List<Tag> getTags() {
        return tags;
    }

    @JsonProperty("new_tags")
    public Boolean getNewTags() {
        return newTags;
    }

    @JsonProperty("current_time")
    public String getCurrentTime() {
        return currentTime;
    }

    @JsonProperty("timezone_offset")
    public Long getTimezoneOffset() {
        return timezoneOffset;
    }

    @JsonProperty("images")
    public Boolean getImages() {
        return images;
    }

    @JsonProperty("view_permissions")
    public ViewPermissions getViewPermissions() {
        return viewPermissions;
    }

    @JsonProperty("extended")
    public List<Extended> getExtended() {
        return extended;
    }

    @JsonProperty("demo_mode")
    public Boolean getDemoMode() {
        return demoMode;
    }

    @JsonProperty("mqtt_webhost")
    public String getMqttWebhost() {
        return mqttWebhost;
    }

    @JsonProperty("mqtt_host")
    public String getMqttHost() {
        return mqttHost;
    }

    @JsonProperty("high_workload")
    public Boolean getHighWorkload() {
        return highWorkload;
    }

    @JsonProperty("prices_unavailable")
    public Boolean getPricesUnavailable() {
        return pricesUnavailable;
    }

    @JsonProperty("currency")
    public Currency getCurrency() {
        return currency;
    }

    @JsonProperty("current_alarms")
    public List<Object> getCurrentAlarms() {
        return currentAlarms;
    }

    @JsonProperty("num_alarms")
    public Long getNumAlarms() {
        return numAlarms;
    }

    @JsonProperty("last_location_timestamp")
    public Boolean getLastLocationTimestamp() {
        return lastLocationTimestamp;
    }

    @JsonProperty("avatar_url")
    public Object getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("nodered_running")
    public Boolean getNoderedRunning() {
        return noderedRunning;
    }

    @JsonProperty("guiHash")
    public Object getGuiHash() {
        return guiHash;
    }

    @JsonProperty("venus_gui_v2")
    public Boolean getVenusGuiV2() {
        return venusGuiV2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Record.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("idSite");
        sb.append('=');
        sb.append(((this.idSite == null)?"<null>":this.idSite));
        sb.append(',');
        sb.append("accessLevel");
        sb.append('=');
        sb.append(((this.accessLevel == null)?"<null>":this.accessLevel));
        sb.append(',');
        sb.append("owner");
        sb.append('=');
        sb.append(((this.owner == null)?"<null>":this.owner));
        sb.append(',');
        sb.append("isAdmin");
        sb.append('=');
        sb.append(((this.isAdmin == null)?"<null>":this.isAdmin));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("identifier");
        sb.append('=');
        sb.append(((this.identifier == null)?"<null>":this.identifier));
        sb.append(',');
        sb.append("idUser");
        sb.append('=');
        sb.append(((this.idUser == null)?"<null>":this.idUser));
        sb.append(',');
        sb.append("pvMax");
        sb.append('=');
        sb.append(((this.pvMax == null)?"<null>":this.pvMax));
        sb.append(',');
        sb.append("timezone");
        sb.append('=');
        sb.append(((this.timezone == null)?"<null>":this.timezone));
        sb.append(',');
        sb.append("phonenumber");
        sb.append('=');
        sb.append(((this.phonenumber == null)?"<null>":this.phonenumber));
        sb.append(',');
        sb.append("notes");
        sb.append('=');
        sb.append(((this.notes == null)?"<null>":this.notes));
        sb.append(',');
        sb.append("geofence");
        sb.append('=');
        sb.append(((this.geofence == null)?"<null>":this.geofence));
        sb.append(',');
        sb.append("geofenceEnabled");
        sb.append('=');
        sb.append(((this.geofenceEnabled == null)?"<null>":this.geofenceEnabled));
        sb.append(',');
        sb.append("realtimeUpdates");
        sb.append('=');
        sb.append(((this.realtimeUpdates == null)?"<null>":this.realtimeUpdates));
        sb.append(',');
        sb.append("hasMains");
        sb.append('=');
        sb.append(((this.hasMains == null)?"<null>":this.hasMains));
        sb.append(',');
        sb.append("hasGenerator");
        sb.append('=');
        sb.append(((this.hasGenerator == null)?"<null>":this.hasGenerator));
        sb.append(',');
        sb.append("noDataAlarmTimeout");
        sb.append('=');
        sb.append(((this.noDataAlarmTimeout == null)?"<null>":this.noDataAlarmTimeout));
        sb.append(',');
        sb.append("alarmMonitoring");
        sb.append('=');
        sb.append(((this.alarmMonitoring == null)?"<null>":this.alarmMonitoring));
        sb.append(',');
        sb.append("invalidVRMAuthTokenUsedInLogRequest");
        sb.append('=');
        sb.append(((this.invalidVRMAuthTokenUsedInLogRequest == null)?"<null>":this.invalidVRMAuthTokenUsedInLogRequest));
        sb.append(',');
        sb.append("syscreated");
        sb.append('=');
        sb.append(((this.syscreated == null)?"<null>":this.syscreated));
        sb.append(',');
        sb.append("isPaygo");
        sb.append('=');
        sb.append(((this.isPaygo == null)?"<null>":this.isPaygo));
        sb.append(',');
        sb.append("paygoCurrency");
        sb.append('=');
        sb.append(((this.paygoCurrency == null)?"<null>":this.paygoCurrency));
        sb.append(',');
        sb.append("paygoTotalAmount");
        sb.append('=');
        sb.append(((this.paygoTotalAmount == null)?"<null>":this.paygoTotalAmount));
        sb.append(',');
        sb.append("inverterChargerControl");
        sb.append('=');
        sb.append(((this.inverterChargerControl == null)?"<null>":this.inverterChargerControl));
        sb.append(',');
        sb.append("shared");
        sb.append('=');
        sb.append(((this.shared == null)?"<null>":this.shared));
        sb.append(',');
        sb.append("deviceIcon");
        sb.append('=');
        sb.append(((this.deviceIcon == null)?"<null>":this.deviceIcon));
        sb.append(',');
        sb.append("alarm");
        sb.append('=');
        sb.append(((this.alarm == null)?"<null>":this.alarm));
        sb.append(',');
        sb.append("lastTimestamp");
        sb.append('=');
        sb.append(((this.lastTimestamp == null)?"<null>":this.lastTimestamp));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("newTags");
        sb.append('=');
        sb.append(((this.newTags == null)?"<null>":this.newTags));
        sb.append(',');
        sb.append("currentTime");
        sb.append('=');
        sb.append(((this.currentTime == null)?"<null>":this.currentTime));
        sb.append(',');
        sb.append("timezoneOffset");
        sb.append('=');
        sb.append(((this.timezoneOffset == null)?"<null>":this.timezoneOffset));
        sb.append(',');
        sb.append("images");
        sb.append('=');
        sb.append(((this.images == null)?"<null>":this.images));
        sb.append(',');
        sb.append("viewPermissions");
        sb.append('=');
        sb.append(((this.viewPermissions == null)?"<null>":this.viewPermissions));
        sb.append(',');
        sb.append("extended");
        sb.append('=');
        sb.append(((this.extended == null)?"<null>":this.extended));
        sb.append(',');
        sb.append("demoMode");
        sb.append('=');
        sb.append(((this.demoMode == null)?"<null>":this.demoMode));
        sb.append(',');
        sb.append("mqttWebhost");
        sb.append('=');
        sb.append(((this.mqttWebhost == null)?"<null>":this.mqttWebhost));
        sb.append(',');
        sb.append("mqttHost");
        sb.append('=');
        sb.append(((this.mqttHost == null)?"<null>":this.mqttHost));
        sb.append(',');
        sb.append("highWorkload");
        sb.append('=');
        sb.append(((this.highWorkload == null)?"<null>":this.highWorkload));
        sb.append(',');
        sb.append("pricesUnavailable");
        sb.append('=');
        sb.append(((this.pricesUnavailable == null)?"<null>":this.pricesUnavailable));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("currentAlarms");
        sb.append('=');
        sb.append(((this.currentAlarms == null)?"<null>":this.currentAlarms));
        sb.append(',');
        sb.append("numAlarms");
        sb.append('=');
        sb.append(((this.numAlarms == null)?"<null>":this.numAlarms));
        sb.append(',');
        sb.append("lastLocationTimestamp");
        sb.append('=');
        sb.append(((this.lastLocationTimestamp == null)?"<null>":this.lastLocationTimestamp));
        sb.append(',');
        sb.append("avatarUrl");
        sb.append('=');
        sb.append(((this.avatarUrl == null)?"<null>":this.avatarUrl));
        sb.append(',');
        sb.append("noderedRunning");
        sb.append('=');
        sb.append(((this.noderedRunning == null)?"<null>":this.noderedRunning));
        sb.append(',');
        sb.append("guiHash");
        sb.append('=');
        sb.append(((this.guiHash == null)?"<null>":this.guiHash));
        sb.append(',');
        sb.append("venusGuiV2");
        sb.append('=');
        sb.append(((this.venusGuiV2 == null)?"<null>":this.venusGuiV2));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
