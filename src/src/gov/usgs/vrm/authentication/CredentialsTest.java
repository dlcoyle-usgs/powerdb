package gov.usgs.vrm.authentication;

import gov.usgs.utils.LogUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Provides a test case for credentials management.
 */
public class CredentialsTest {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");

    public static void main(String[] args) throws IOException {

        LogUtils.header("CredentialsTest: Starting ...");

        Credentials credentials = CredentialsFactory.getCredentials();

        logger.info("retrieved credentials: credentials: {}", credentials.toString());

        LogUtils.header("CredentialsTest: Finished");

    }
}
