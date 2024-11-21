package gov.usgs.vrm.history;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;

public class JSONFileReader {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
    public static StringBuilder jsonString = new StringBuilder();

    /**
     * HistoryMain entry point.
     * Provides the V2 implementation.
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {

        logger.info("main:");

        logger.info("dir: {}", System.getProperty("user.dir"));

        String jsonString = openAndReadFilePath("data/site-history-383620-P7D-single-variable-raw.json");

        logger.info("jsonString-length: {}", jsonString.length() );

        HistoryParser historyParser = new HistoryParser();

        History history = historyParser.parseHistoryJSON(jsonString);

        //historyParser.describeHistoryData(history);

        //historyParser.describeHistoryMeta(history);

        logger.info("main: status: {}", "OK");

    }

    /**
     * HistoryMain entry point.
     * Provides the V2 implementation.
     * @param args
     * @throws IOException
     */
    public static void mainV2(String [] args) throws IOException {

        logger.info("main:");

        logger.info("dir: {}", System.getProperty("user.dir"));

        String jsonString = openAndReadFilePath("data/site-history-383620-P7D-single-variable-raw.json");

        logger.info("jsonString-length: {}", jsonString.length() );

        HistoryParser historyParser = new HistoryParser();

        History history = historyParser.parseHistoryJSON(jsonString);

        //historyParser.describeHistoryData(history);

        //historyParser.describeHistoryMeta(history);

        logger.info("main: status: {}", "OK");

    }


    /**
     * Opens and reads a file and returns a string of the file contents.
     * @param path
     * @return String file contents
     * @throws IOException
     */
    public static String openAndReadFilePath(String path) throws IOException {
        File file = new File(path);
        byte[] byteArray = Files.readAllBytes(file.toPath());
        return new String(byteArray);
    }

    /**
     * Opens and reads a file.
     * @param infile
     * String content = readFile("test.txt", StandardCharsets.UTF_8);
     * The platform default is available from the Charset class itself:
     *
     * String content = readFile("test.txt", Charset.defaultCharset());
     */
    public static void openAndReadFile(File infile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(infile))) {
            String line;
           // bufferedReader.
            int foundCount = 0;
            String searchString = "143";
                    String replaceString = "timeseries";
                    while ((line = bufferedReader.readLine()) != null) {
                        logger.debug("LINE: {}", line);
                        if (line.contains("\"143\":")) {
                            foundCount++;
                            logger.debug("found: {}", foundCount);
                            String replacementString = line.replace(searchString, replaceString);
                            //line.replaceAll()
                    logger.debug("replacementString: {}", replacementString);
                    jsonString.append(replacementString.trim());
                } else {
                    jsonString.append(line.trim());
                }
            }
            logger.debug("found: {}", foundCount);
            logger.debug("jsonString: {} ", jsonString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens and reads a file.
     * @param infile
     * String content = readFile("test.txt", StandardCharsets.UTF_8);
     * The platform default is available from the Charset class itself:
     *
     * String content = readFile("test.txt", Charset.defaultCharset());
     */
    public static void openAndReadFileV1(File infile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(infile))) {
            String line;
            // bufferedReader.
            int foundCount = 0;
            String searchString = "143";
            String replaceString = "timeseries";
            while ((line = bufferedReader.readLine()) != null) {
                logger.debug("LINE: {}", line);
                if (line.contains("\"143\":")) {
                    foundCount++;
                    logger.debug("found: {}", foundCount);
                    String replacementString = line.replace(searchString, replaceString);
                    //line.replaceAll()
                    logger.debug("replacementString: {}", replacementString);
                    jsonString.append(replacementString.trim());
                } else {
                    jsonString.append(line.trim());
                }
            }
            logger.debug("found: {}", foundCount);
            logger.debug("jsonString: {} ", jsonString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens and reads a file.
     * @param infile
     */
    public static void openAndModifyFile(File infile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(infile))) {
            String line;
            int foundCount = 0;
            String searchString = "143";
            String replaceString = "timeseries";
            while ((line = bufferedReader.readLine()) != null) {
                logger.debug("LINE: {}", line);
                if (line.contains("\"143\":")) {
                    foundCount++;
                    logger.debug("found: {}", foundCount);
                    String replacementString = line.replace(searchString, replaceString);
                    logger.debug("replacementString: {}", replacementString);
                    jsonString.append(replacementString.trim());
                } else {
                    jsonString.append(line.trim());
                }
            }
            logger.debug("found: {}", foundCount);
            logger.debug("jsonString: {} ", jsonString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
