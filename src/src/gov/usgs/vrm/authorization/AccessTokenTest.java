package gov.usgs.vrm.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.utils.FileSet;
import gov.usgs.utils.FileSetUtils;
import gov.usgs.utils.LogUtils;
import gov.usgs.vrm.authentication.Credentials;
import gov.usgs.vrm.authentication.CredentialsFactory;
import gov.usgs.vrm.installations.InstallationsTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TODO: Move to test.
 */
public class AccessTokenTest {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");

    public static void main(String[] args) throws IOException {

        LogUtils.header("AccessTokenTest: Starting ...");

        AccessToken accessToken = AccessTokenFactory.getAccessToken();

        logger.info("retrieved access token: accessTokenString: {}", accessToken.getTokenString());

        LogUtils.header("AccessTokenTest: Finished");

    }

}
