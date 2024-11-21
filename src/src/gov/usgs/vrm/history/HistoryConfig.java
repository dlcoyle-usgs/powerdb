package gov.usgs.vrm.history;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * login_url = 'https://vrmapi.victronenergy.com/v2/auth/login'
 * diagnostics_url = 'https://vrmapi.victronenergy.com/v2/installations/212679/diagnostics
 * optional: ?count=1000'
 * installations_url = 'https://vrmapi.victronenergy.com/v2/users/217044/installations'
 *
 * #login_string = '{"username":dlcoyle@usgs.gov,"password":"Vict2021!!"}'
 * login_string = '{"username":"dlcoyle@usgs.gov","password":"NPS1201!!"}'
 * #NPS1201!!
 * #Vict2021!!
 * String requestURL = "https://vrmapi.victronenergy.com/v2/installations/383620/widgets/Graph?start=1713204523&attributeIds%5B1%5D=143";
 *
 * ITE='321039';
 * SITE='347181';
 * idSite='347181';
 * idSite='321039';
 * # Hughes
 * idSite='347181';
 * # Pinewood
 * idSite='383620';
 *
 * #echo "SITE: ${SITE}";
 * # Hughes
 * idSite='347181';
 *
 * #DATETIME=`date -u +"%Y-%m-%dT%H:%M:%SZ"`;
 * DATETIME=`date -u +"%Y-%m-%dT%H:%M:%SZ"`;
 *
 */
public class HistoryConfig {
    private static final Logger logger  = LoggerFactory.getLogger( "HistoryMain" );
    public String historyQueryURL = "https://vrmapi.victronenergy.com/v2/installations/383620/widgets/Graph?start=1713204523&attributeIds%5B1%5D=143";
    public String idSite = "347181";


    public String getHistoryQueryURL() {
        historyQueryURL = "https://vrmapi.victronenergy.com/v2/installations/" + idSite + "/widgets/Graph?start=1713204523&attributeIds%5B1%5D=143";
        historyQueryURL = "https://vrmapi.victronenergy.com/v2/installations/" + idSite + "/widgets/Graph?attributeIds%5B1%5D=143";
        return historyQueryURL;
    }

    public void setHistoryQueryURL(String historyQueryURL) {
        this.historyQueryURL = historyQueryURL;
    }
//    protected String diagnosticsURL = "https://vrmapi.victronenergy.com/v2/installations/212679/diagnostics"; //?count=1000";
//    protected String installationsURL = "https://vrmapi.victronenergy.com/v2/users/217044/installations";
//    protected String installationsExtendedURL = "https://vrmapi.victronenergy.com/v2/users/217044/installations?extended=1";

}
