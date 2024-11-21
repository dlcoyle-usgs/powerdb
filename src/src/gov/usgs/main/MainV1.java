package gov.usgs.main;

import gov.usgs.utils.LogUtils;
import gov.usgs.vrm.authentication.AuthenticationModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Provides the main entry point of the application.
 * @author dlcoyle@usgs.gov
 * Access-Token:
 * XXXXXXXX
 */
public class MainV1 {

    static final Logger logger = LoggerFactory.getLogger("HistoryMain");

    /**
     * The main entry point for the initial authentication test.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String args[]) throws IOException, InterruptedException {
        LogUtils.header("HistoryMain: Starting ...");
        AuthenticationModule authenticationModule = new AuthenticationModule();
        authenticationModule.sendAuthenticationRequest();
        LogUtils.header("HistoryMain: Finished");
    }

}
