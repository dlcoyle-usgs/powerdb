package gov.usgs.vrm.diagnostics;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.utils.LogUtils;
import gov.usgs.utils.TimeUtils;
import gov.usgs.vrm.installations.Installations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * Provides the VRM retrieval implementation.
 * @author dlcoyle@usgs.gov
 */
public class DiagnosticsTest {

    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
    static String HOST_URL = "https://vrmapi.victronenergy.com/v2/auth/login";
    static String LoginString = "{\"username\":\"dlcoyle@usgs.gov\",\"password\":\"XXXXXXXX\"}";

//    StringBuilder sb = new StringBuilder();
//    Installations installations = null;
//
    /**
     * Provides the main entry point.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        LogUtils.header("DiagnosticsTest: Starting ...");
        DiagnosticsTest diagnosticsTest = new DiagnosticsTest();
        logger.info("DiagnosticsTest: object=[{}]", diagnosticsTest);
        diagnosticsTest.processDiagnosticsFile();
        //LogUtils.header("DiagnosticsTest: Finished");
        LogUtils.header("DiagnosticsTest: Finished");

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
    void processDiagnosticsFile() throws IOException {
        logger.info("processDiagnosticsFile:");
        String cwd = Paths.get("").toAbsolutePath().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String directoryName = "data";
        //String inFilename = "installations.json";
        String inFilename = "installations-modified-long.json";
        inFilename = "diagnostics.json";
        String outFilename = "installations-out.json";
        File infile = new File(directoryName,inFilename);
        File outfile = new File(directoryName,outFilename);
        logger.info("CWD: {}", cwd);
        logger.info("INFILE: {}", infile.getAbsolutePath());
        logger.info("INFILE.LENGTH: {}", infile.length());
        logger.info("INFILE.LINES: {}", getFileNumberOfLines(infile.getAbsolutePath()));
        logger.info("OUTFILE: {}", outfile.getAbsolutePath());
        //Installations installations = new Installations();
        //objectMapper.writeValue(outfile, Installations.class);

        //Read and parse the Installations.json file:
        //TODO: Diagnostics diagnostics = objectMapper.readValue(infile, Diagnostics.class);
        Diagnostics diagnostics = objectMapper.readValue(infile, Diagnostics.class);
        //logger.info("Installations: {}", ((Installations) installations).toString() );

        //TODO: logger.info("Diagnostics: {}", diagnostics.toString() );
        //logger.info("Diagnostics: {}", diagnostics.toString() );

        describeDiagnostics(diagnostics);
        //describeDiagnosticsLong(diagnostics);
        selectDiagnosticsEntry(diagnostics);

        //dumpData(installations);

        //logger.info(StringDump.dump(installations));

    }

    void describeDiagnostics (Diagnostics diagnostics) {
        logger.info("describeDiagnostics:");
        logger.info("success: {}", diagnostics.getSuccess());
        logger.info("records: {}", diagnostics.getNumRecords());
    }

    void describeDiagnosticsLong (Diagnostics diagnostics) {
        logger.info("describeDiagnosticsLong:");
        logger.info("success: {}", diagnostics.getSuccess());
        logger.info("records: {}", diagnostics.getNumRecords());
        List<Record> records = diagnostics.getRecords();
        for (Record record : records) {
            logger.info("> {}", record.toStringShort() );
        }
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
     * @param diagnostics
     */
    void selectDiagnosticsEntry(Diagnostics diagnostics) {
        logger.info("selectDiagnosticsEntry:");
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

        List<Record> records = diagnostics.getRecords();
        for (Record record : records) {
            if (set.contains(record.getCode())) {
                //logger.info("* {}", record.toStringShort());
                logger.info("> {}: {}: {}", TimeUtils.convertEpochSecond(record.getTimestamp()), record.getDescription(), record.getFormattedValue());
                //logger.info("> {}", record.getTimestamp());
                //logger.info("> {}", record.getDescription());
                //logger.info("> {}", record.getRawValue());
                //logger.info(String.format("-> [%.2F] -- HOURS", record.getRawValue()));
                //logger.info("> {}", record.getFormattedValue());
                //logger.info("> {}", record.getFormatWithUnit());
                logger.info("--");
                //record.getDescription()
            }
        }
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
     * @param diagnostics
     */
    void selectDiagnosticsEntryV2(Diagnostics diagnostics) {
        logger.info("selectDiagnosticsEntryV2:");
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

        logger.info("query: set: {}", set.toString() );

        List<Record> records = diagnostics.getRecords();
        for (Record record : records) {
            if (set.contains(record.getCode())) {
                logger.info("* {}", record.toStringShort());
                logger.info("> {}", TimeUtils.convertEpochSecond(record.getTimestamp()));
                //logger.info("> {}", record.getTimestamp());
                logger.info("> {}", record.getDescription());
                logger.info("> {}", record.getRawValue());
                //logger.info(String.format("-> [%.2F] -- HOURS", record.getRawValue()));
                logger.info("> {}", record.getFormattedValue());
                logger.info("> {}", record.getFormatWithUnit());
                logger.info("--");
                //record.getDescription()
            }
        }
    }

    void selectDiagnosticsEntryV1(Diagnostics diagnostics) {
        logger.info("describeDiagnosticsPick:");
        Set<String> set = new HashSet<>();
        set.add("ba");
        set.add("bc");
        set.add("bp");
        set.add("bs");
        set.add("bst");
        set.add("bt");
        set.add("PVP");
        set.add("PVV");

        logger.info("query: set: {}", set.toString() );

        List<Record> records = diagnostics.getRecords();
        for (Record record : records) {
            if (set.contains(record.getCode())) {
                logger.info("* {}", record.toStringShort());
                logger.info("> {}", record.getDescription());
                logger.info("> {}", record.getTimestamp());
                logger.info("> {}", TimeUtils.convertEpochSecond(record.getTimestamp()));
                logger.info("> {}", record.getRawValue());
                //logger.info(String.format("-> [%.2F] -- HOURS", record.getRawValue()));
                logger.info("> {}", record.getFormattedValue());
                logger.info("> {}", record.getFormatWithUnit());
                logger.info("--");
                //record.getDescription()
            }
        }
    }

    /**
     * bt PVP
     * @param diagnostics
     */
    void describeDiagnosticsPick (Diagnostics diagnostics) {
        logger.info("describeDiagnosticsPick:");
        List<Record> records = diagnostics.getRecords();
        for (Record record : records) {
            if (record.getCode().equals("PVP")) {
                logger.info("* {}", record.toStringShort());
                logger.info("> {}", record.getDescription());
                logger.info("> {}", record.getTimestamp());
                logger.info("> {}", TimeUtils.convertEpochSecond(record.getTimestamp()));
                logger.info("> {}", record.getRawValue());
                //logger.info(String.format("-> [%.2F] -- HOURS", record.getRawValue()));
                logger.info("> {}", record.getFormattedValue());
                logger.info("> {}", record.getFormatWithUnit());
                //record.getDescription()
            }
        }
    }

    //UTIL:
    protected int getFileNumberOfLines(String filepath) throws IOException {
        try (Stream<String> fileStream = Files.lines(Paths.get(filepath))) {
            int noOfLines = (int) fileStream.count();
            return noOfLines;
        }
    }

//    try (Stream<String> fileStream = Files.lines(Paths.get(INPUT_FILE_NAME))) {
//        int noOfLines = (int) fileStream.count();
//        assertEquals(NO_OF_LINES, noOfLines);
//    }

//
//    /**
//     * Parses the VRM data and populates the prepared class structure.
//     * The jsonschema2pojo script is used to implement the code generation phase.
//     * The ID-Site value of each installation is used to subsequently retrieve the sensor history for each site.
//     * Data: C:\devel\relay\vrm-reader\data
//     * CO-Willow Cr-09019850-Hughes
//     * idSite": 347181
//     * CO-Willow Cr-09019850-StarLink
//     * idSite": 321039
//     * @throws IOException
//     */
//    void parseVRMInstallations() throws IOException {
//        logger.info("parseVRMInstallations:");
//        String cwd = Paths.get("").toAbsolutePath().toString();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String directoryName = "data";
//        //String inFilename = "installations.json";
//        String inFilename = "installations-modified-long.json";
//        String outFilename = "installations-out.json";
//        File infile = new File(directoryName,inFilename);
//        File outfile = new File(directoryName,outFilename);
//        logger.info("CWD: {}", cwd);
//        logger.info("INFILE: {}", infile.getAbsolutePath());
//        logger.info("OUTFILE: {}", outfile.getAbsolutePath());
//        //Installations installations = new Installations();
//        //objectMapper.writeValue(outfile, Installations.class);
//        Installations installations = objectMapper.readValue(infile, Installations.class);
//
//        //logger.info("Installations: {}", ((Installations) installations).toString() );
//
//        logger.info("Installations: {}", installations.toString() );
//
//        dumpData(installations);
//
//        //logger.info(StringDump.dump(installations));
//
//    }
//
//    /**
//     * Dumps the installations data to the terminal.
//     * @see "https://www.baeldung.com/java-iterate-list"
//     * @param installations
//     */
//    void dumpData(Installations installations) {
//        //StringBuilder sb = new StringBuilder();
//        sb.append(Installations.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
//        sb.append("success");
//        sb.append('=');
//        sb.append(((installations.getSuccess() == null) ? "<null>" : installations.getSuccess()));
//        sb.append(',');
//        sb.append("records");
//        sb.append('=');
//        List<Record> records = installations.getRecords();
//        int recordsCount = records.size();
//        sb.append("records-count: " + recordsCount);
//
//        sb.append(']');
//
//        logger.info("*** Data: {}", sb.toString());
//
//        sb.delete(0, sb.length());
//
//        logger.info("*** RECORDS: \n");
//        for (Record record : records) {
//            logger.info(record);
//            info("Site", record.getIdSite());
//            info("Name", record.getName());
//            info("Time-Zone", record.getTimezone());
//        }
//
//    }

//    void fileUtil() throws IOException {
//        logger.info("fileUtil:");
//        String cwd = Paths.get("").toAbsolutePath().toString();
//
//        String dirname = "data";
//        //String inFilename = "installations.json";
//        String inFilename = "installations-modified-long.json";
//        String outFilename = "installations-out.json";
//        File infile = new File(dirname,inFilename);
//        File outfile = new File(dirname,outFilename);
//        logger.info("CWD: {}", cwd);
//        logger.info("INFILE: {}", infile.getAbsolutePath());
//        logger.info("OUTFILE: {}", outfile.getAbsolutePath());
//        //Installations installations = new Installations();
//        //objectMapper.writeValue(outfile, Installations.class);
//        Installations installations = objectMapper.readValue(infile, Installations.class);
//
//        //logger.info("Installations: {}", ((Installations) installations).toString() );
//
//        logger.info("Installations: {}", installations.toString() );
//
//        dumpData(installations);
//
//        //logger.info(StringDump.dump(installations));
//
//    }

}