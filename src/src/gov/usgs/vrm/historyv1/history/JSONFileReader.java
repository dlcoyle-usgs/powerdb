package gov.usgs.vrm.historyv1.history;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader {
    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
//    public static void openAndReadFilePath(String[] args) {
//        String path = "path/to/your/file.txt";
//        try (BufferedReader br = new BufferedReader(new JSONFileReader(path))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static StringBuilder jsonString = new StringBuilder();

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
