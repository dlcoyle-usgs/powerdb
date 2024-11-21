package gov.usgs.vrm.installations;
import gov.usgs.utils.LogUtils;
import gov.usgs.vrm.retrievals.RetrievalTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.usgs.utils.LogUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
//import gov.usgs.vrm.installations.Installations;
//import gov.usgs.vrm.installations.Record;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/**
 * Provides the VRM retrieval implementation.
 * @author dlcoyle@usgs.gov
 */
public class InstallationsTest {

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

        LogUtils.header("InstallationsTest: Starting ...");
        InstallationsTest installationsTest = new InstallationsTest();
        logger.info("InstallationsTest: object=[{}]", installationsTest);
        installationsTest.parseVRMInstallationsFile();
        //LogUtils.header("InstallationsTest: Finished");
        LogUtils.header("InstallationsTest: Finished");

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
    void parseVRMInstallationsFile() throws IOException {
        logger.info("parseVRMInstallationsFile:");
        String cwd = Paths.get("").toAbsolutePath().toString();
        ObjectMapper objectMapper = new ObjectMapper();
        String directoryName = "data";
        //String inFilename = "installations.json";
        String inFilename = "installations-modified-long.json";
        inFilename = "installations_20240420.json";
        String outFilename = "installations-out.json";
        File infile = new File(directoryName,inFilename);
        File outfile = new File(directoryName,outFilename);
        logger.info("CWD: {}", cwd);
        logger.info("INFILE: {}", infile.getAbsolutePath());
        logger.info("OUTFILE: {}", outfile.getAbsolutePath());
        //Installations installations = new Installations();
        //objectMapper.writeValue(outfile, Installations.class);

        //Read and parse the Installations.json file:
        Installations installations = objectMapper.readValue(infile, Installations.class);

        //logger.info("Installations: {}", ((Installations) installations).toString() );

        logger.info("Installations: {}", installations.toString() );

        //dumpData(installations);

        //logger.info(StringDump.dump(installations));

    }


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