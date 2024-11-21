package gov.usgs.main;

import gov.usgs.utils.LogUtils;
import gov.usgs.vrm.history.HistoryConfig;
import gov.usgs.vrm.history.HistoryModule;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Provides the main entry point of the application.
 * @author dlcoyle@usgs.gov
 * Access-Token:
 * ENVIRONMENT:
 * VRM_TOKEN=6ca9e08f809b21f6f4fe3082bf7aeb36e1ed391dc01e9a139fe14d7907473090
 */
public class HistoryMain {

    static final Logger logger = LogManager.getLogger(HistoryMain.class);

    /**
     * The main entry point for the initial authentication test.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String args[]) throws IOException, InterruptedException, URISyntaxException {
        logger.info(">>>HistoryMain: Starting");
        String cwd = System.getProperty("user.dir");
        System.out.println( "HistoryMain: CWD: " + cwd);
        LogUtils.header("HistoryMain: Starting ...");
        logger.info("HistoryMain: CWD: {}", cwd);
        HistoryConfig historyConfig = new HistoryConfig();
        HistoryModule historyModule = new HistoryModule();
        try {
            historyModule.performRetrieval(historyConfig);
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            logger.error("URISyntaxException: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        LogUtils.header("HistoryMain: Finished");
    }

}
