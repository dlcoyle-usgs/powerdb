package gov.usgs.vrm.history;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.utils.LogUtils;
import gov.usgs.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import java.io.File;
import java.io.IOException;

/**
 * Provides the VRM API history time-series retrieval implementation.
 * @author dlcoyle@usgs.gov
 */
public class HistoryTest {

    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
    static String HOST_URL = "https://vrmapi.victronenergy.com/v2/auth/login";
    static String LoginString = "{\"username\":\"dlcoyle@usgs.gov\",\"password\":\"XXXXXXXX\"}";

    /**
     * Provides the main entry point.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        LogUtils.header("HistoryTest: Starting ...");
        HistoryTest historyTest = new HistoryTest();
        logger.info("HistoryTest: object=[{}]", historyTest);
        //historyTest.processHistoryFile();
        historyTest.processHistoryJSON();
        LogUtils.header("HistoryTest: Finished");

    }

    /**
     * Parses the VRM data and populates the prepared class structure.
     * Note: The jsonschema2pojo script is used to implement the code generation phase.
     * The ID-Site value of each installation is used to subsequently retrieve the sensor history for each site.
     * Data: C:\devel\relay\vrm-reader\data
     * Example Data:
     * <ul>
     * <li>IdSite: 347181, CO-Willow Cr-09019850-Hughes</li>
     * <li>IdSite": 321039, CO-Willow Cr-09019850-StarLink</li>
     * </ul>
     * @throws IOException
     */
    void processHistoryJSON() throws IOException {
        logger.info("processHistoryJSON:");
        String cwd = Paths.get("").toAbsolutePath().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String directoryName = "data";
        //String inFilename = "installations.json";
        String inFilename = "installations-modified-long.json";
        inFilename = "history.json";
        //inFilename = "site-history-383620-P7D-single-variable-raw.json";
        String outFilename = "history-out.json";
        File infile = new File(directoryName,inFilename);
        File outfile = new File(directoryName,outFilename);
        logger.info("CWD: {}", cwd);
        logger.info("INFILE: {}", infile.getAbsolutePath());
        logger.info("INFILE.LENGTH: {}", infile.length());
        logger.info("INFILE.LINES: {}", getFileNumberOfLines(infile.getAbsolutePath()));
        logger.info("OUTFILE: {}", outfile.getAbsolutePath());

        //TESTCASE: JSONFileReader.openAndReadFile(infile);

        //JSONObject
        //Read and parse the Installations.json file:
        History history = objectMapper.readValue(infile, History.class);

        //TODO: logger.info("History: {}", history.toString() );
        //logger.info("History: {}", history.toString() );

        //describeHistoryResponse(history);

        describeHistoryMeta(history);

        describeHistoryData(history);

    }

    /**
     * Parses the VRM data and populates the prepared class structure.
     * Note: The jsonschema2pojo script is used to implement the code generation phase.
     * The ID-Site value of each installation is used to subsequently retrieve the sensor history for each site.
     * Data: C:\devel\relay\vrm-reader\data
     * Example Data:
     * <ul>
     * <li>IdSite: 347181, CO-Willow Cr-09019850-Hughes</li>
     * <li>IdSite": 321039, CO-Willow Cr-09019850-StarLink</li>
     * </ul>
     * @throws IOException
     */
    void processHistoryFile() throws IOException {
        logger.info("processHistoryFile:");
        String cwd = Paths.get("").toAbsolutePath().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String directoryName = "data";
        //String inFilename = "installations.json";
        String inFilename = "installations-modified-long.json";
        inFilename = "history.json";
        String outFilename = "history-out.json";
        File infile = new File(directoryName,inFilename);
        File outfile = new File(directoryName,outFilename);
        logger.info("CWD: {}", cwd);
        logger.info("INFILE: {}", infile.getAbsolutePath());
        logger.info("INFILE.LENGTH: {}", infile.length());
        logger.info("INFILE.LINES: {}", getFileNumberOfLines(infile.getAbsolutePath()));
        logger.info("OUTFILE: {}", outfile.getAbsolutePath());

        //Read and parse the Installations.json file:
        History history = objectMapper.readValue(infile, History.class);

        //TODO: logger.info("History: {}", history.toString() );
        logger.info("History: {}", history.toString() );

        describeHistoryResponse(history);

        describeHistoryMeta(history);

        describeHistoryData(history);

    }

    /**
     * Describes the history response status information.
     * @param history
     */
    void describeHistoryResponse(History history) {
        logger.info("describeHistoryResponse:");
        logger.info("success: {}", history.getSuccess());
        //x logger.info("records: {}", history.getNumRecords());
    }

    /**
     * Describes the history time-series meta information.
     * @param history
     */
    void describeHistoryMeta(History history) {
        logger.info("describeHistoryMeta:");
        Records records = history.getRecords();
        //V1: Meta meta = records.getMeta();
        //V1: Timeseries timeseries = meta.getTimeseries();
        //V1: logger.info("Code: {}", timeseries.getCode());
        //V1: logger.info("Code: {}", timeseries.getDescription());
        //V1: logger.info("Code: {}", timeseries.getFormatValueOnly());
        //V1: logger.info("Code: {}", timeseries.getFormatWithUnit());


        //x logger.info("records: {}", history.getNumRecords());
    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    void describeHistoryData(History history) {
        logger.info("describeHistoryLong:");
        Records records = history.getRecords();
        Data data = null; //V1: records.getData();
        List<List<String>> timeseries = data.getTimeseries();
        for (List<String> innerList : timeseries) {
            //logger.info("TYPE: {}", innerList);
            //logger.info("SIZE: {}", innerList.size());
            //logger.info("DATA: {}", innerList.toArray()[1]);
            //logger.info("OBJECT: {}", array[0]);
            //logger.info("OBJECT: {}", array[1]);
            //logger.info("VALUE: {}", array[1]);

            Object[] array = innerList.toArray();
            long timeStampEpochSecond = Long.parseLong((String)array[0]);
            double decimalValue = parseDecimalValue((String)array[1]);
            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), decimalValue );


            //for (String number : innerList) {
                // Process the number
                //System.out.println(number);
            //}

            //logger.info("TS: {}", number[0]);
            //logger.info("V : {}", number[1]);
        }
        //for (ListIterator<E> iterator = timeseries.listIterator(); iterator.hasNext(); ) {
        //    E element = iterator.next();
        //    logger.info(element);
            // 1 - can call methods of element
            // 2 - can use iter.remove() to remove the current element from the list
            // 3 - can use iter.add(...) to insert a new element into the list
            //     between element and iter->next()
            // 4 - can use iter.set(...) to replace the current element

            // ...
        //}
        //x logger.info("records: {}", history.getNumRecords());
        //List<Record> records = history.getRecords();
        //for (Record record : records) {
        //    logger.info("> {}", record.toStringShort() );
        //}
    }

    /**
     * bt PVP
     * #   N"description": "PV voltage", "code": "PVV",
     * #   N"description": "PV power", "code": "PVP",
     * #   "description": "Voltage", "code": "bv",
     * # #      "description": "Current",
     * # #      "code": "bc",
     * # #      "description": "Battery Power",
     * # #      "code": "bp",
     * #       "description": "Battery SOC", "code": "bs",
     * # #      "description": "Battery state",
     * # #      "code": "bst",
     * #       "description": "Battery Consumed Amphours", "code": "ba",
     * #       "description": "Battery Time to Go", "code": "bt",
     * @param history
     */
    void selectHistoryEntry(History history) {
        logger.info("selectHistoryEntry:");
        //Set<String> set = new HashSet<>();

        List<String> set = new ArrayList<>();

        set.add("ba");
        set.add("bc");
        set.add("bp");
        set.add("bs");
        set.add("bst");
        set.add("bt");
        set.add("PVP");
        set.add("PVV");

        logger.info("--");
        logger.info("query: set: {}", set.toString() );
        logger.info("--");

//        List<Record> records = history.getRecords();
//        for (Record record : records) {
//            if (set.contains(record.getCode())) {
//                //logger.info("* {}", record.toStringShort());
//                logger.info("> {}: {}: {}", TimeUtils.convertEpochSecond(record.getTimestamp()), record.getDescription(), record.getFormattedValue());
//                //logger.info("> {}", record.getTimestamp());
//                //logger.info("> {}", record.getDescription());
//                //logger.info("> {}", record.getRawValue());
//                //logger.info(String.format("-> [%.2F] -- HOURS", record.getRawValue()));
//                //logger.info("> {}", record.getFormattedValue());
//                //logger.info("> {}", record.getFormatWithUnit());
//                logger.info("--");
//                //record.getDescription()
//            }
//        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // UTIL:
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Parses a string into a Double.
     * Handles a policy for the NODATA object representation (used for statistics).
     * @param decimalString
     * @return
     */
    Double parseDecimalValue(String decimalString) {

        Double NODATA = Double.NaN;

        Double decimalValue = null;

        if (decimalString == null) {
            logger.warn("parseDecimalValue: NULL: returning NaN");
            decimalValue = NODATA;
        } else {
            decimalValue = Double.parseDouble(decimalString);
        }

        return decimalValue;

    }

    /**
     * Answers the number of lines in the file.
     * @param filepath
     * @return
     * @throws IOException
     */
    protected int getFileNumberOfLines(String filepath) throws IOException {
        try (Stream<String> fileStream = Files.lines(Paths.get(filepath))) {
            int noOfLines = (int) fileStream.count();
            return noOfLines;
        }
    }

}