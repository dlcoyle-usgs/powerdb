package gov.usgs.vrm.history;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.main.HistoryMain;
import gov.usgs.utils.TimeUtils;
import gov.usgs.vrm.parameters.ParameterCd;
import gov.usgs.vrm.parameters.ParameterCdFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

/**
 * Provides the VRM API history time-series retrieval implementation.
 * @author dlcoyle@usgs.gov
 */
public class HistoryParser {

    static final Logger logger = LogManager.getLogger(HistoryParser.class);

    public static boolean USE_WARNINGS = false;

    public static int DEBUG = 1;

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
    public History parseHistoryJSON(String jsonString) throws JsonProcessingException {
        logger.info("parseHistoryJSON:");
        //logger.debug("jsonString: {}", jsonString);

        ObjectMapper objectMapper = new ObjectMapper();

        gov.usgs.vrm.history.History history = objectMapper.readValue(jsonString, gov.usgs.vrm.history.History.class);

        ParameterCd parameterCd = this.parseParameterCd(history);

        logger.debug("parseHistoryJSON: parameterCd: {}", parameterCd);

        //V1: describeHistoryResponse(history);

        //V1: describeHistoryMeta(history);

        //describeHistoryMeta(history);

        //describeHistoryDataV3(history);

        //describeHistoryData(history);

        return history;

    }
    VRMCode vrmCode = VRMCode.code;
    public enum VRMCode {
        code,
        description,
        formatValueOnly,
        formatWithUnit,
        axisGroup;
    }
    public static HistoryModule.OnlineStatus status = HistoryModule.OnlineStatus.ONLINE;

    /**
     * Parses the parameter code data information.
     * @param history
     */
    public ParameterCd parseParameterCd(History history) {
        logger.info("parseParameterCd:");
        LinkedHashMap linkedHashMap = null;

        Records records = history.getRecords();
        Map<String, Object> meta = records.meta;
        Set<Map.Entry<String, Object>> entries = meta.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        Map.Entry<String, Object> next = iterator.next();

        String parameterCodeIdString = next.getKey();
        logger.debug("parameterCodeIdString: {}", parameterCodeIdString);

        ParameterCd parameterCd = ParameterCdFactory.getParameterCd(parameterCodeIdString);

        Object value = next.getValue();
        if (value instanceof java.util.LinkedHashMap) {
            linkedHashMap = (java.util.LinkedHashMap) value;
        } else {
            throw new IllegalStateException("unexpected data type: " + value.getClass() );
        }

        for (Object object : linkedHashMap.keySet()) {

            String vrmCodeString = object.toString();

            if (vrmCodeString.equalsIgnoreCase(VRMCode.code.name())) {
                parameterCd.code = linkedHashMap.get("code").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.description.name())) {
                parameterCd.description = linkedHashMap.get("description").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.formatValueOnly.name())) {
                parameterCd.formatValueOnly = linkedHashMap.get("formatValueOnly").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.formatWithUnit.name())) {
                parameterCd.formatWithUnit = linkedHashMap.get("formatWithUnit").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.axisGroup.name())) {
                parameterCd.axisGroup = linkedHashMap.get("axisGroup").toString();
            }
        }

        return parameterCd;
    }

    /**
     * Describes the history time-series data information.
     * @param history the history parameter object
     */
    public void describeHistoryData(History history) {
        logger.info("describeHistoryData: Starting");
        Records records = history.getRecords();
        //Map<String, Object> data = records.data;
        Map<String, ArrayList<ArrayList<String>>> data = records.data;
        //logger.debug("describeHistoryData: records.data: {}", data.toString());
        //logger.debug("describeHistoryData: records.data: {}", data.keySet());
        Set<Map.Entry<String, ArrayList<ArrayList<String>>>> entries = data.entrySet();
        //logger.debug("describeHistoryData: records.data.entry-set: {}", entries);
        //logger.debug("describeHistoryData: records.data.entries-size: {}", entries.size());
        Iterator<Map.Entry<String, ArrayList<ArrayList<String>>>> iterator = entries.iterator();
        //logger.debug("describeHistoryData: records.data.entries-next: {}", iterator.next() );
        //logger.debug("describeHistoryData: records.data.entries-next: {}", iterator.next() );
        Map.Entry<String, ArrayList<ArrayList<String>>> next = iterator.next();
        //logger.debug("describeHistoryData: object class: {}", next.getClass());
        logger.debug("describeHistoryData: object class key: {}", next.getKey());
        //logger.debug("describeHistoryData: object class value: {}", next.getValue());
        //logger.debug("describeHistoryData: object class key: {}", next.getKey().getClass());
        //logger.debug("describeHistoryData: object class value: {}", next.getValue().getClass());

        ArrayList<ArrayList<String>> value = next.getValue();
        //logger.debug("describeHistoryData: object class value: {}", value.getClass());
        logger.debug("describeHistoryData: result list size: {}", value.size());
        for (Object object : value) {
            //logger.debug("object: {}", object.getClass());
            ArrayList innerList = (ArrayList) object;
            //logger.debug("list-size: {}", innerList.size());
            //Object first = innerList.get(0);
            //Object second = innerList.get(1);
            String timeStampStringOrig = (String) innerList.get(0);
            String valueStringOrig     = (String) innerList.get(1);

            if (DEBUG > 1 ) logger.debug("*** timeStampStringOrig: {}, valueStringOrig: {}", timeStampStringOrig, valueStringOrig);

            Long timeStampLong = Long.parseLong(timeStampStringOrig);
            Instant timeStampInstant = TimeUtils.convertEpochSecond(timeStampLong.longValue());

            Double valueDouble = parseDoubleValue(valueStringOrig);

            logger.debug("> timeStampInstant: {}, valueDouble: {}", timeStampInstant, valueDouble);

            //long timeStampEpochSecond = ((Integer) first).longValue();

//            if (second != null) {
//                //logger.debug("second class: {}", second.getClass());logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second);
//            } else {
//                logger.warn("second class: {}", "WAS NULL - not writing the values");
//            }
        }
        logger.info("describeHistoryData: Finished");
    }
    /**
     * Describes the history time-series data information.
     * @param history the history parameter object
     */
    public void describeHistoryDataDebug(History history) {
        logger.info("describeHistoryDataDebug: Starting");
        Records records = history.getRecords();
        //Map<String, Object> data = records.data;
        Map<String, ArrayList<ArrayList<String>>> data = records.data;
        logger.debug("describeHistoryData: records.data: {}", data.toString() );
        logger.debug("describeHistoryData: records.data: {}", data.keySet() );
        Set<Map.Entry<String, ArrayList<ArrayList<String>>>> entries = data.entrySet();
        logger.debug("describeHistoryData: records.data.entry-set: {}", entries );
        logger.debug("describeHistoryData: records.data.entries-size: {}", entries.size() );
        Iterator<Map.Entry<String, ArrayList<ArrayList<String>>>> iterator = entries.iterator();
//        logger.debug("describeHistoryData: records.data.entries-next: {}", iterator.next() );
//        logger.debug("describeHistoryData: records.data.entries-next: {}", iterator.next() );
        Map.Entry<String, ArrayList<ArrayList<String>>> next = iterator.next();
        logger.debug("describeHistoryData: object class: {}", next.getClass() );
        logger.debug("describeHistoryData: object class key: {}", next.getKey() );
        logger.debug("describeHistoryData: object class value: {}", next.getValue() );
        logger.debug("describeHistoryData: object class key: {}", next.getKey().getClass() );
        logger.debug("describeHistoryData: object class value: {}", next.getValue().getClass() );

        ArrayList<ArrayList<String>> value = next.getValue();
        logger.debug("describeHistoryData: object class value: {}", value.getClass() );
        logger.debug("describeHistoryData: object class value size: {}", value.size() );
        for (Object object : value) {
            logger.debug("object: {}", object.getClass() );
            ArrayList innerList = (ArrayList) object;
            logger.debug("list-size: {}", innerList.size() );
            Object first = innerList.get(0);
            Object second = innerList.get(1);
            logger.debug("first class: {}", first.getClass());
            //long timeStampEpochSecond = ((Integer) first).longValue();

            if (second != null) {
                logger.debug("second class: {}", second.getClass());
                //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second);
            } else {
                logger.warn("second class: {}", "WAS NULL - not writing the values");
            }
        }
        logger.info("describeHistoryDataDebug: Finished");

        //logger.debug("describeHistoryData: records.data: {}", records.data.toString() );
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            logger.debug("*** KEY: " + entry.getKey() + ":" + entry.getValue());
//            String numberString = entry.getKey();
//            String codeString = "bv";
//            String unitString = "V";
//            String descriptionString = "Battery Volts";
//            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
//            logger.debug("> parameterCd: {}", parameterCd.toString());
//
//            Object object = entry.getValue();
//
//            logger.debug("object-class: {}", object.getClass());
//
//            List<Object> outerList = null;
//            List<Object> innerList = null;
//            if (object instanceof java.util.ArrayList) {
//                outerList = (List<Object>) object;
//            }
//            logger.debug("outerlist: {}", outerList.toString());
//
//            logger.debug("outerList.size: {}", outerList.size());
//
//            ListIterator<Object> outerListIterator = outerList.listIterator();
//
//            String timeStampString = null;
//            String valueString = null;
//            while (outerListIterator.hasNext()) {
//                Object itemObject = outerListIterator.next();
//                logger.debug("> itemObject: {}", itemObject);
//                logger.debug("> itemObject class: {}", itemObject.getClass());
//
//                if (object instanceof java.util.ArrayList) {
//                    logger.debug("itemObject: {}", true);
//                    innerList = (List<Object>) itemObject;
//                    logger.debug("innerList.size: {}", innerList.size());
//                    if (innerList.size() == 2) {
//                        logger.debug("innerList.size: {}", "OK");
//                        Object first = innerList.get(0);
//                        Object second = innerList.get(1);
//
//                        logger.debug("first class: {}", first.getClass());
//                        long timeStampEpochSecond = ((Integer)first).longValue();
//
//                        if (second != null) {
//                            logger.debug("second class: {}", second.getClass());
//                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        } else {
//                            logger.warn("second class: {}", "WAS NULL - not writing the values");
//                        }
//
//                        //double decimalValue = ((Double)second).doubleValue();
//
//                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        //logger.debug("timeStampString: {}", timeStampString);
//                        //logger.debug("timeStampString: OKOK");
//                    } else {
//                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
//                    }
//                }
//            }
//        }
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            logger.debug("*** KEY: " + entry.getKey() + ":" + entry.getValue());
//            String numberString = entry.getKey();
//            String codeString = "bv";
//            String unitString = "V";
//            String descriptionString = "Battery Volts";
//            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
//            logger.debug("> parameterCd: {}", parameterCd.toString());
//
//            Object object = entry.getValue();
//
//            logger.debug("object-class: {}", object.getClass());
//
//            List<Object> outerList = null;
//            List<Object> innerList = null;
//            if (object instanceof java.util.ArrayList) {
//                outerList = (List<Object>) object;
//            }
//            logger.debug("outerlist: {}", outerList.toString());
//
//            logger.debug("outerList.size: {}", outerList.size());
//
//            ListIterator<Object> outerListIterator = outerList.listIterator();
//
//            String timeStampString = null;
//            String valueString = null;
//            while (outerListIterator.hasNext()) {
//                Object itemObject = outerListIterator.next();
//                logger.debug("> itemObject: {}", itemObject);
//                logger.debug("> itemObject class: {}", itemObject.getClass());
//
//                if (object instanceof java.util.ArrayList) {
//                    logger.debug("itemObject: {}", true);
//                    innerList = (List<Object>) itemObject;
//                    logger.debug("innerList.size: {}", innerList.size());
//                    if (innerList.size() == 2) {
//                        logger.debug("innerList.size: {}", "OK");
//                        Object first = innerList.get(0);
//                        Object second = innerList.get(1);
//
//                        logger.debug("first class: {}", first.getClass());
//                        long timeStampEpochSecond = ((Integer)first).longValue();
//
//                        if (second != null) {
//                            logger.debug("second class: {}", second.getClass());
//                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        } else {
//                            logger.warn("second class: {}", "WAS NULL - not writing the values");
//                        }
//
//                        //double decimalValue = ((Double)second).doubleValue();
//
//                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        //logger.debug("timeStampString: {}", timeStampString);
//                        //logger.debug("timeStampString: OKOK");
//                    } else {
//                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
//                    }
//                }
//            }
//        }
    }
    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataV4(History history) {
        logger.debug("describeHistoryData:");

        Records records = history.getRecords();
        logger.debug("describeHistoryData: records: {}", records.toString() );

//        Data data = history.getRecords().getData();
//        logger.debug("describeHistoryData: data: {}", data.toString() );
//
//        List<List<String>> timeseries = new ArrayList<List<String>>();
//        logger.debug("describeHistoryData: timeseries: {}", timeseries.toString() );
//
//        timeseries = data.getTimeseries();
//        logger.debug("describeHistoryData: timeseries: {}", timeseries.toString() );

//        Map<String, Object> data = null; //V2: records.getData();
//        logger.debug("describeHistoryData: data: {}", data.toString() );
//
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            logger.debug("***" + entry.getKey() + ":" + entry.getValue());
//            String numberString = entry.getKey();
//            String codeString = "bv";
//            String unitString = "V";
//            String descriptionString = "Battery Volts";
//            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
//            logger.debug("> parameterCd: {}", parameterCd.toString());
//
//            Object object = entry.getValue();
//
//            logger.debug("object-class: {}", object.getClass());
//
//            List<Object> outerList = null;
//            List<Object> innerList = null;
//            if (object instanceof java.util.ArrayList) {
//                outerList = (List<Object>) object;
//            }
//            logger.debug("outerlist: {}", outerList.toString());
//
//            logger.debug("outerList.size: {}", outerList.size());
//
//            ListIterator<Object> listIterator = outerList.listIterator();
//
//            String timeStampString = null;
//            String valueString = null;
//            while (listIterator.hasNext()) {
//                Object itemObject = listIterator.next();
//                logger.debug("itemObject: {}", itemObject);
//
//                if (object instanceof java.util.ArrayList) {
//                    logger.debug("itemObject: {}", true);
//                    innerList = (List<Object>) itemObject;
//                    logger.debug("innerList.size: {}", innerList.size());
//                    if (innerList.size() == 2) {
//                        logger.debug("innerList.size: {}", "OK");
//                        Object first = innerList.get(0);
//                        Object second = innerList.get(1);
//
//                        logger.debug("first class: {}", first.getClass());
//                        long timeStampEpochSecond = ((Integer)first).longValue();
//
//                        if (second != null) {
//                            logger.debug("second class: {}", second.getClass());
//                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        } else {
//                            logger.warn("second class: {}", "WAS NULL - not writing the values");
//                        }
//
//                        //double decimalValue = ((Double)second).doubleValue();
//
//                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        //logger.debug("timeStampString: {}", timeStampString);
//                        //logger.debug("timeStampString: OKOK");
//                    } else {
//                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
//                    }
//                }
//            }
//        }
    }


    /**
     * Describes the history response status information.
     * @param history
     */
    public void describeHistoryResponse(History history) {
        logger.debug("describeHistoryResponse:");
        logger.debug("success: {}", history.getSuccess());
        //V1: logger.debug("records: {}", history.getRecords().getData().getTimeseries().size());
    }

    /**
     * Describes the history time-series meta information.
     * @param history
     */
    public void describeHistoryMetaV3(History history) {
        logger.debug("describeHistoryMeta:");

        Records records = history.getRecords();

        Map<String, Object> meta = records.getMeta();

        logger.debug("describeHistoryMeta: meta: {}", meta.toString() );

        parseMeta(meta);

        //V1: Meta meta = records.getMeta();
        //V1: Timeseries timeseries = meta.getTimeseries();
        //V1: logger.debug("Code: {}", timeseries.getCode());
        //V1: logger.debug("Code: {}", timeseries.getDescription());
        //V1: logger.debug("Code: {}", timeseries.getFormatValueOnly());
        //V1: logger.debug("Code: {}", timeseries.getFormatWithUnit());
        //x logger.debug("records: {}", history.getNumRecords());
    }

    void parseMeta(Map<String, Object> meta) {
        logger.debug("parseMeta:");
        for (Map.Entry<String, Object> entry : meta.entrySet()) {
            logger.debug("*** KEY: {}", entry.getKey());
            //logger.debug("*** KEY: " + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.debug("> parameterCd: {}", parameterCd.toString());

            //Object value = entry.getValue();

            Object object = entry.getValue();

            logger.debug("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof java.util.ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.debug("outerlist: {}", outerList.toString());

            logger.debug("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.debug("itemObject: {}", itemObject);

                if (object instanceof java.util.ArrayList) {
                    logger.debug("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.debug("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.debug("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.debug("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.debug("second class: {}", second.getClass());
                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.debug("timeStampString: {}", timeStampString);
                        //logger.debug("timeStampString: OKOK");
                    } else {
                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
                    }
                }
            }
        }
    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataV3(History history) {
        logger.debug("describeHistoryData:");
        Records records = history.getRecords();
        Map<String, Object> data = null; //V2: records.getData();
        logger.debug("describeHistoryData: data: {}", data.toString() );

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.debug("***" + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.debug("> parameterCd: {}", parameterCd.toString());

            Object object = entry.getValue();

            logger.debug("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof java.util.ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.debug("outerlist: {}", outerList.toString());

            logger.debug("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.debug("itemObject: {}", itemObject);

                if (object instanceof java.util.ArrayList) {
                    logger.debug("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.debug("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.debug("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.debug("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.debug("second class: {}", second.getClass());
                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.debug("timeStampString: {}", timeStampString);
                        //logger.debug("timeStampString: OKOK");
                    } else {
                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
                    }
                }
            }
        }
    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryMetaV2(History history) {
        logger.debug("describeHistoryData:");
        Records records = history.getRecords();
        Map<String, Object> data = null; //V2: records.getData();
        logger.debug("describeHistoryData: data: {}", data.toString() );

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.debug("***" + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.debug("> parameterCd: {}", parameterCd.toString());

            Object object = entry.getValue();

            logger.debug("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof java.util.ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.debug("outerlist: {}", outerList.toString());

            logger.debug("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.debug("itemObject: {}", itemObject);

                if (object instanceof java.util.ArrayList) {
                    logger.debug("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.debug("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.debug("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.debug("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.debug("second class: {}", second.getClass());
                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.debug("timeStampString: {}", timeStampString);
                        //logger.debug("timeStampString: OKOK");
                    } else {
                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
                    }
                }
            }
        }

//            System.out.println(listIterator.next());
//            System.out.println(listIterator.next());

//            while (outerList.iterator().hasNext()) {
//                Object itemObject = outerList.iterator().next();
//                logger.debug("itemObject: {}", itemObject );
//            }


       // }
        //V1: List<List<String>> timeseries = data.getTimeseries();
        //V1: for (List<String> innerList : timeseries) {
            //logger.debug("TYPE: {}", innerList);
            //logger.debug("SIZE: {}", innerList.size());
            //logger.debug("DATA: {}", innerList.toArray()[1]);
            //logger.debug("OBJECT: {}", array[0]);
            //logger.debug("OBJECT: {}", array[1]);
            //logger.debug("VALUE: {}", array[1]);

        //V1: Object[] array = innerList.toArray();
        //V1: long timeStampEpochSecond = Long.parseLong((String)array[0]);
        //V1: double decimalValue = parseDecimalValue((String)array[1]);
        //V1: logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), decimalValue );


            //for (String number : innerList) {
                // Process the number
                //System.out.println(number);
            //}

            //logger.debug("TS: {}", number[0]);
            //logger.debug("V : {}", number[1]);
        //V1: }
        //for (ListIterator<E> iterator = timeseries.listIterator(); iterator.hasNext(); ) {
        //    E element = iterator.next();
        //    logger.debug(element);
            // 1 - can call methods of element
            // 2 - can use iter.remove() to remove the current element from the list
            // 3 - can use iter.add(...) to insert a new element into the list
            //     between element and iter->next()
            // 4 - can use iter.set(...) to replace the current element

            // ...
        //}
        //x logger.debug("records: {}", history.getNumRecords());
        //List<Record> records = history.getRecords();
        //for (Record record : records) {
        //    logger.debug("> {}", record.toStringShort() );
        //}
    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataV2(History history) {
        logger.debug("describeHistoryData:");
        Records records = history.getRecords();
        Map<String, Object> data = null; //V2: records.getData();
        logger.debug("describeHistoryData: data: {}", data.toString() );

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.debug("***" + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.debug("> parameterCd: {}", parameterCd.toString());

            Object object = entry.getValue();

            logger.debug("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof java.util.ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.debug("outerlist: {}", outerList.toString());

            logger.debug("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.debug("itemObject: {}", itemObject);

                if (object instanceof java.util.ArrayList) {
                    logger.debug("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.debug("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.debug("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.debug("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.debug("second class: {}", second.getClass());
                            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.debug("timeStampString: {}", timeStampString);
                        //logger.debug("timeStampString: OKOK");
                    } else {
                        logger.debug("timeStampString: SIZE ISSUE: NOT OK");
                    }
                }
            }
        }

//            System.out.println(listIterator.next());
//            System.out.println(listIterator.next());

//            while (outerList.iterator().hasNext()) {
//                Object itemObject = outerList.iterator().next();
//                logger.debug("itemObject: {}", itemObject );
//            }


        // }
        //V1: List<List<String>> timeseries = data.getTimeseries();
        //V1: for (List<String> innerList : timeseries) {
        //logger.debug("TYPE: {}", innerList);
        //logger.debug("SIZE: {}", innerList.size());
        //logger.debug("DATA: {}", innerList.toArray()[1]);
        //logger.debug("OBJECT: {}", array[0]);
        //logger.debug("OBJECT: {}", array[1]);
        //logger.debug("VALUE: {}", array[1]);

        //V1: Object[] array = innerList.toArray();
        //V1: long timeStampEpochSecond = Long.parseLong((String)array[0]);
        //V1: double decimalValue = parseDecimalValue((String)array[1]);
        //V1: logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), decimalValue );


        //for (String number : innerList) {
        // Process the number
        //System.out.println(number);
        //}

        //logger.debug("TS: {}", number[0]);
        //logger.debug("V : {}", number[1]);
        //V1: }
        //for (ListIterator<E> iterator = timeseries.listIterator(); iterator.hasNext(); ) {
        //    E element = iterator.next();
        //    logger.debug(element);
        // 1 - can call methods of element
        // 2 - can use iter.remove() to remove the current element from the list
        // 3 - can use iter.add(...) to insert a new element into the list
        //     between element and iter->next()
        // 4 - can use iter.set(...) to replace the current element

        // ...
        //}
        //x logger.debug("records: {}", history.getNumRecords());
        //List<Record> records = history.getRecords();
        //for (Record record : records) {
        //    logger.debug("> {}", record.toStringShort() );
        //}
    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataV1(History history) {
        logger.debug("describeHistoryData:");
        Records records = history.getRecords();
        Data data = null; //V1: records.getData();
        List<List<String>> timeseries = data.getTimeseries();
        for (List<String> innerList : timeseries) {
            //logger.debug("TYPE: {}", innerList);
            //logger.debug("SIZE: {}", innerList.size());
            //logger.debug("DATA: {}", innerList.toArray()[1]);
            //logger.debug("OBJECT: {}", array[0]);
            //logger.debug("OBJECT: {}", array[1]);
            //logger.debug("VALUE: {}", array[1]);

            Object[] array = innerList.toArray();
            long timeStampEpochSecond = Long.parseLong((String)array[0]);
            double decimalValue = parseDoubleValue((String)array[1]);
            logger.debug("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), decimalValue );


            //for (String number : innerList) {
            // Process the number
            //System.out.println(number);
            //}

            //logger.debug("TS: {}", number[0]);
            //logger.debug("V : {}", number[1]);
        }
        //for (ListIterator<E> iterator = timeseries.listIterator(); iterator.hasNext(); ) {
        //    E element = iterator.next();
        //    logger.debug(element);
        // 1 - can call methods of element
        // 2 - can use iter.remove() to remove the current element from the list
        // 3 - can use iter.add(...) to insert a new element into the list
        //     between element and iter->next()
        // 4 - can use iter.set(...) to replace the current element

        // ...
        //}
        //x logger.debug("records: {}", history.getNumRecords());
        //List<Record> records = history.getRecords();
        //for (Record record : records) {
        //    logger.debug("> {}", record.toStringShort() );
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
        logger.debug("selectHistoryEntry:");
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

        logger.debug("--");
        logger.debug("query: set: {}", set.toString() );
        logger.debug("--");

//        List<Record> records = history.getRecords();
//        for (Record record : records) {
//            if (set.contains(record.getCode())) {
//                //logger.debug("* {}", record.toStringShort());
//                logger.debug("> {}: {}: {}", TimeUtils.convertEpochSecond(record.getTimestamp()), record.getDescription(), record.getFormattedValue());
//                //logger.debug("> {}", record.getTimestamp());
//                //logger.debug("> {}", record.getDescription());
//                //logger.debug("> {}", record.getRawValue());
//                //logger.debug(String.format("-> [%.2F] -- HOURS", record.getRawValue()));
//                //logger.debug("> {}", record.getFormattedValue());
//                //logger.debug("> {}", record.getFormatWithUnit());
//                logger.debug("--");
//                //record.getDescription()
//            }
//        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // UTIL:
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Parses a string into a Double.
     * Implements a policy for the NODATA object representation as NaN when the VRM API returns a NULL value.
     * @param doubleString
     * @return
     */
    Double parseDoubleValue(String doubleString) {

        Double NODATA = Double.NaN;

        Double doubleValue = null;

        if (doubleString == null) {
            if (USE_WARNINGS) logger.warn("parseDecimalValue: NULL: returning NaN");
            doubleValue = NODATA;
        } else {
            doubleValue = Double.parseDouble(doubleString);
        }

        return doubleValue;

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