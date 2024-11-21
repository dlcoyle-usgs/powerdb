package gov.usgs.vrm.retrievals;

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
 */
public class RetrievalConfig {
    private static final Logger logger  = LoggerFactory.getLogger( "HistoryMain" );
    protected String LoginURL = "https://vrmapi.victronenergy.com/v2/auth/login";
    protected String LoginString = "{\"username\":dlcoyle@usgs.gov,\"password\":\"XXXXXXXX\"}'";

    protected String diagnosticsURL = "https://vrmapi.victronenergy.com/v2/installations/212679/diagnostics"; //?count=1000";
    protected String installationsURL = "https://vrmapi.victronenergy.com/v2/users/217044/installations";
    protected String installationsExtendedURL = "https://vrmapi.victronenergy.com/v2/users/217044/installations?extended=1";


    public static void main(String args[]) {
        RetrievalTest retrievalTest = new RetrievalTest();
        retrievalTest.createRetrieval();
    }

    void createRetrieval() {
        logger.info("retrievalConfig: starting ...");
        logger.info("retrievalConfig: running ...");


    }

}
