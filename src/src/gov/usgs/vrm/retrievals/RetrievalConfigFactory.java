package gov.usgs.vrm.retrievals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetrievalConfigFactory {
    private static final Logger logger  = LoggerFactory.getLogger( "HistoryMain" );
    protected String LoginURL = "https://vrmapi.victronenergy.com/v2/auth/login";
    protected String DiagnosticsURL = "https://vrmapi.victronenergy.com/v2/installations/212679/diagnostics?count=1000";
    protected String QueryURL1 = "https://vrmapi.victronenergy.com/v2/users/217044/installations";
    protected String QueryURL = "https://vrmapi.victronenergy.com/v2/users/217044/installations?extended=1";
    protected String LoginString = "{\"username\":dlcoyle@usgs.gov,\"password\":\"XXXXXXXX\"}'";

    public static void main(String args[]) {
        RetrievalConfigFactory retrievalConfigFactory = new RetrievalConfigFactory();
        RetrievalConfig retrievalConfig = retrievalConfigFactory.createRetrievalConfig();
    }

    public static RetrievalConfig createRetrievalConfig() {
        logger.info("RetrievalConfigFactory: createRetrievalConfig:");
        RetrievalConfig retrievalConfig = new RetrievalConfig();
        return retrievalConfig;
    }

}
