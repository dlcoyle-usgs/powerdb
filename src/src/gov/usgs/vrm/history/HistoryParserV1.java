package gov.usgs.vrm.history;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.utils.TimeUtils;
import gov.usgs.vrm.parameters.ParameterCd;
import gov.usgs.vrm.parameters.ParameterCdFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class HistoryParserV1 {

    static final Logger logger = LoggerFactory.getLogger("HistoryMain");
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

        History history = objectMapper.readValue(jsonString, History.class);

        //V1: describeHistoryResponse(history);

        //V1: describeHistoryMeta(history);

        describeHistoryMeta(history);

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
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryMeta(History history) {
        logger.info("describeHistoryMeta:");

        Records records = history.getRecords();
        //Map<String, Object> data = records.data;
        //Map<String, ArrayList<ArrayList<String>>> data = records.data;
        Map<String, Object> meta = records.meta;
        //logger.info("describeHistoryMeta: records.data: {}", data.toString());
        //logger.info("describeHistoryMeta: records.data: {}", data.keySet());
        Set<Map.Entry<String, Object>> entries = meta.entrySet();
        //logger.info("describeHistoryMeta: records.data.entry-set: {}", entries);
        //logger.info("describeHistoryMeta: records.data.entries-size: {}", entries.size());
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();

        //logger.info("describeHistoryMeta: records.data.entries-next: {}", iterator.next() );
        //logger.info("describeHistoryMeta: records.data.entries-next: {}", iterator.next() );
        Map.Entry<String, Object> next = iterator.next();

        //logger.info("describeHistoryMeta: object class: {}", next.getClass());
        logger.info("describeHistoryMeta: object class key: {}", next.getKey());
        String parameterCodeIdString = next.getKey();
        logger.info("parameterCodeIdString: {}", parameterCodeIdString);
        ParameterCd parameterCd = ParameterCdFactory.getParameterCd(parameterCodeIdString);
        //, codeString, unitString, descriptionString);
        //logger.info("describeHistoryMeta: object class value: {}", next.getValue());
        //logger.info("describeHistoryMeta: object class key: {}", next.getKey().getClass());
        //logger.info("describeHistoryMeta: object class value: {}", next.getValue().getClass());

        logger.info("parameterCd: {}", parameterCd);

        Object value = next.getValue();

        logger.info("describeHistoryMeta: object class value: {}", value.getClass());
        LinkedHashMap linkedHashMap = null;
        if (value instanceof LinkedHashMap) {
            linkedHashMap = (LinkedHashMap) value;
        }

        logger.info("list size: {}", linkedHashMap.size());

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

        //return parameterCd;

    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    public ParameterCd parseParameterCd(History history) {
        logger.info("parseParameterCd:");
        Records records = history.getRecords();
        Map<String, Object> meta = records.meta;
        Set<Map.Entry<String, Object>> entries = meta.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        Map.Entry<String, Object> next = iterator.next();
        String parameterCodeIdString = next.getKey();
        logger.info("parameterCodeIdString: {}", parameterCodeIdString);
        ParameterCd parameterCd = ParameterCdFactory.getParameterCd(parameterCodeIdString);
        logger.info("parameterCd: {}", parameterCd);
        Object value = next.getValue();
        LinkedHashMap linkedHashMap = null;
        if (value instanceof LinkedHashMap) {
            linkedHashMap = (LinkedHashMap) value;
        } else {
            throw new IllegalStateException("unexpected data type: " + value.getClass() );
        }
        logger.info("list size: {}", linkedHashMap.size());
        logger.info("list keys: {}", linkedHashMap.keySet());

        for (Object o : linkedHashMap.keySet()) {
            String vrmCodeString = o.toString();
            logger.info("describeHistoryMeta: object class list vrmCodeString: {}", vrmCodeString );
            if (vrmCodeString.equalsIgnoreCase(VRMCode.code.name())) {
                logger.info("describeHistoryMeta: VRMCode: {}", VRMCode.code.name() );
                parameterCd.code = linkedHashMap.get("code").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.description.name())) {
                logger.info("describeHistoryMeta: VRMCode: {}", VRMCode.description.name() );
                parameterCd.description = linkedHashMap.get("description").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.formatValueOnly.name())) {
                logger.info("describeHistoryMeta: VRMCode: {}", VRMCode.formatValueOnly.name() );
                parameterCd.formatValueOnly = linkedHashMap.get("formatValueOnly").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.formatWithUnit.name())) {
                logger.info("describeHistoryMeta: VRMCode: {}", VRMCode.formatWithUnit.name() );
                parameterCd.formatWithUnit = linkedHashMap.get("formatWithUnit").toString();
            }
            if (vrmCodeString.equalsIgnoreCase(VRMCode.axisGroup.name())) {
                logger.info("describeHistoryMeta: VRMCode: {}", VRMCode.axisGroup.name() );
                parameterCd.axisGroup = linkedHashMap.get("axisGroup").toString();
                System.out.println( "*****" + linkedHashMap.get("axisGroup") );
            }

            logger.info("--");
        }
        logger.info("parameterCd: {}", parameterCd);
        return parameterCd;
    }

    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryData(History history) {
        logger.info("describeHistoryData:");
        Records records = history.getRecords();
        //Map<String, Object> data = records.data;
        Map<String, ArrayList<ArrayList<String>>> data = records.data;
        //logger.info("describeHistoryData: records.data: {}", data.toString());
        //logger.info("describeHistoryData: records.data: {}", data.keySet());
        Set<Map.Entry<String, ArrayList<ArrayList<String>>>> entries = data.entrySet();
        //logger.info("describeHistoryData: records.data.entry-set: {}", entries);
        //logger.info("describeHistoryData: records.data.entries-size: {}", entries.size());
        Iterator<Map.Entry<String, ArrayList<ArrayList<String>>>> iterator = entries.iterator();
        //logger.info("describeHistoryData: records.data.entries-next: {}", iterator.next() );
        //logger.info("describeHistoryData: records.data.entries-next: {}", iterator.next() );
        Map.Entry<String, ArrayList<ArrayList<String>>> next = iterator.next();
        //logger.info("describeHistoryData: object class: {}", next.getClass());
        logger.info("describeHistoryData: object class key: {}", next.getKey());
        //logger.info("describeHistoryData: object class value: {}", next.getValue());
        //logger.info("describeHistoryData: object class key: {}", next.getKey().getClass());
        //logger.info("describeHistoryData: object class value: {}", next.getValue().getClass());

        ArrayList<ArrayList<String>> value = next.getValue();
        //logger.info("describeHistoryData: object class value: {}", value.getClass());
        logger.info("describeHistoryData: result list size: {}", value.size());
        for (Object object : value) {
            //logger.info("object: {}", object.getClass());
            ArrayList innerList = (ArrayList) object;
            //logger.info("list-size: {}", innerList.size());
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
//                //logger.info("second class: {}", second.getClass());logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second);
//            } else {
//                logger.warn("second class: {}", "WAS NULL - not writing the values");
//            }
        }
    }
    /**
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataDebug(History history) {
        logger.info("describeHistoryData:");
        Records records = history.getRecords();
        //Map<String, Object> data = records.data;
        Map<String, ArrayList<ArrayList<String>>> data = records.data;
        logger.info("describeHistoryData: records.data: {}", data.toString() );
        logger.info("describeHistoryData: records.data: {}", data.keySet() );
        Set<Map.Entry<String, ArrayList<ArrayList<String>>>> entries = data.entrySet();
        logger.info("describeHistoryData: records.data.entry-set: {}", entries );
        logger.info("describeHistoryData: records.data.entries-size: {}", entries.size() );
        Iterator<Map.Entry<String, ArrayList<ArrayList<String>>>> iterator = entries.iterator();
//        logger.info("describeHistoryData: records.data.entries-next: {}", iterator.next() );
//        logger.info("describeHistoryData: records.data.entries-next: {}", iterator.next() );
        Map.Entry<String, ArrayList<ArrayList<String>>> next = iterator.next();
        logger.info("describeHistoryData: object class: {}", next.getClass() );
        logger.info("describeHistoryData: object class key: {}", next.getKey() );
        logger.info("describeHistoryData: object class value: {}", next.getValue() );
        logger.info("describeHistoryData: object class key: {}", next.getKey().getClass() );
        logger.info("describeHistoryData: object class value: {}", next.getValue().getClass() );

        ArrayList<ArrayList<String>> value = next.getValue();
        logger.info("describeHistoryData: object class value: {}", value.getClass() );
        logger.info("describeHistoryData: object class value size: {}", value.size() );
        for (Object object : value) {
            logger.info("object: {}", object.getClass() );
            ArrayList innerList = (ArrayList) object;
            logger.info("list-size: {}", innerList.size() );
            Object first = innerList.get(0);
            Object second = innerList.get(1);
            logger.info("first class: {}", first.getClass());
            //long timeStampEpochSecond = ((Integer) first).longValue();

            if (second != null) {
                logger.info("second class: {}", second.getClass());
                //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second);
            } else {
                logger.warn("second class: {}", "WAS NULL - not writing the values");
            }
        }

        //logger.info("describeHistoryData: records.data: {}", records.data.toString() );
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            logger.info("*** KEY: " + entry.getKey() + ":" + entry.getValue());
//            String numberString = entry.getKey();
//            String codeString = "bv";
//            String unitString = "V";
//            String descriptionString = "Battery Volts";
//            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
//            logger.info("> parameterCd: {}", parameterCd.toString());
//
//            Object object = entry.getValue();
//
//            logger.info("object-class: {}", object.getClass());
//
//            List<Object> outerList = null;
//            List<Object> innerList = null;
//            if (object instanceof java.util.ArrayList) {
//                outerList = (List<Object>) object;
//            }
//            logger.info("outerlist: {}", outerList.toString());
//
//            logger.info("outerList.size: {}", outerList.size());
//
//            ListIterator<Object> outerListIterator = outerList.listIterator();
//
//            String timeStampString = null;
//            String valueString = null;
//            while (outerListIterator.hasNext()) {
//                Object itemObject = outerListIterator.next();
//                logger.info("> itemObject: {}", itemObject);
//                logger.info("> itemObject class: {}", itemObject.getClass());
//
//                if (object instanceof java.util.ArrayList) {
//                    logger.info("itemObject: {}", true);
//                    innerList = (List<Object>) itemObject;
//                    logger.info("innerList.size: {}", innerList.size());
//                    if (innerList.size() == 2) {
//                        logger.info("innerList.size: {}", "OK");
//                        Object first = innerList.get(0);
//                        Object second = innerList.get(1);
//
//                        logger.info("first class: {}", first.getClass());
//                        long timeStampEpochSecond = ((Integer)first).longValue();
//
//                        if (second != null) {
//                            logger.info("second class: {}", second.getClass());
//                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        } else {
//                            logger.warn("second class: {}", "WAS NULL - not writing the values");
//                        }
//
//                        //double decimalValue = ((Double)second).doubleValue();
//
//                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        //logger.info("timeStampString: {}", timeStampString);
//                        //logger.info("timeStampString: OKOK");
//                    } else {
//                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
//                    }
//                }
//            }
//        }
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            logger.info("*** KEY: " + entry.getKey() + ":" + entry.getValue());
//            String numberString = entry.getKey();
//            String codeString = "bv";
//            String unitString = "V";
//            String descriptionString = "Battery Volts";
//            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
//            logger.info("> parameterCd: {}", parameterCd.toString());
//
//            Object object = entry.getValue();
//
//            logger.info("object-class: {}", object.getClass());
//
//            List<Object> outerList = null;
//            List<Object> innerList = null;
//            if (object instanceof java.util.ArrayList) {
//                outerList = (List<Object>) object;
//            }
//            logger.info("outerlist: {}", outerList.toString());
//
//            logger.info("outerList.size: {}", outerList.size());
//
//            ListIterator<Object> outerListIterator = outerList.listIterator();
//
//            String timeStampString = null;
//            String valueString = null;
//            while (outerListIterator.hasNext()) {
//                Object itemObject = outerListIterator.next();
//                logger.info("> itemObject: {}", itemObject);
//                logger.info("> itemObject class: {}", itemObject.getClass());
//
//                if (object instanceof java.util.ArrayList) {
//                    logger.info("itemObject: {}", true);
//                    innerList = (List<Object>) itemObject;
//                    logger.info("innerList.size: {}", innerList.size());
//                    if (innerList.size() == 2) {
//                        logger.info("innerList.size: {}", "OK");
//                        Object first = innerList.get(0);
//                        Object second = innerList.get(1);
//
//                        logger.info("first class: {}", first.getClass());
//                        long timeStampEpochSecond = ((Integer)first).longValue();
//
//                        if (second != null) {
//                            logger.info("second class: {}", second.getClass());
//                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        } else {
//                            logger.warn("second class: {}", "WAS NULL - not writing the values");
//                        }
//
//                        //double decimalValue = ((Double)second).doubleValue();
//
//                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        //logger.info("timeStampString: {}", timeStampString);
//                        //logger.info("timeStampString: OKOK");
//                    } else {
//                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
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
        logger.info("describeHistoryData:");

        Records records = history.getRecords();
        logger.info("describeHistoryData: records: {}", records.toString() );

//        Data data = history.getRecords().getData();
//        logger.info("describeHistoryData: data: {}", data.toString() );
//
//        List<List<String>> timeseries = new ArrayList<List<String>>();
//        logger.info("describeHistoryData: timeseries: {}", timeseries.toString() );
//
//        timeseries = data.getTimeseries();
//        logger.info("describeHistoryData: timeseries: {}", timeseries.toString() );

//        Map<String, Object> data = null; //V2: records.getData();
//        logger.info("describeHistoryData: data: {}", data.toString() );
//
//        for (Map.Entry<String, Object> entry : data.entrySet()) {
//            logger.info("***" + entry.getKey() + ":" + entry.getValue());
//            String numberString = entry.getKey();
//            String codeString = "bv";
//            String unitString = "V";
//            String descriptionString = "Battery Volts";
//            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
//            logger.info("> parameterCd: {}", parameterCd.toString());
//
//            Object object = entry.getValue();
//
//            logger.info("object-class: {}", object.getClass());
//
//            List<Object> outerList = null;
//            List<Object> innerList = null;
//            if (object instanceof java.util.ArrayList) {
//                outerList = (List<Object>) object;
//            }
//            logger.info("outerlist: {}", outerList.toString());
//
//            logger.info("outerList.size: {}", outerList.size());
//
//            ListIterator<Object> listIterator = outerList.listIterator();
//
//            String timeStampString = null;
//            String valueString = null;
//            while (listIterator.hasNext()) {
//                Object itemObject = listIterator.next();
//                logger.info("itemObject: {}", itemObject);
//
//                if (object instanceof java.util.ArrayList) {
//                    logger.info("itemObject: {}", true);
//                    innerList = (List<Object>) itemObject;
//                    logger.info("innerList.size: {}", innerList.size());
//                    if (innerList.size() == 2) {
//                        logger.info("innerList.size: {}", "OK");
//                        Object first = innerList.get(0);
//                        Object second = innerList.get(1);
//
//                        logger.info("first class: {}", first.getClass());
//                        long timeStampEpochSecond = ((Integer)first).longValue();
//
//                        if (second != null) {
//                            logger.info("second class: {}", second.getClass());
//                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        } else {
//                            logger.warn("second class: {}", "WAS NULL - not writing the values");
//                        }
//
//                        //double decimalValue = ((Double)second).doubleValue();
//
//                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
//                        //logger.info("timeStampString: {}", timeStampString);
//                        //logger.info("timeStampString: OKOK");
//                    } else {
//                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
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
        logger.info("describeHistoryResponse:");
        logger.info("success: {}", history.getSuccess());
        //V1: logger.info("records: {}", history.getRecords().getData().getTimeseries().size());
    }

    /**
     * Describes the history time-series meta information.
     * @param history
     */
    public void describeHistoryMetaV3(History history) {
        logger.info("describeHistoryMeta:");

        Records records = history.getRecords();

        Map<String, Object> meta = records.getMeta();

        logger.info("describeHistoryMeta: meta: {}", meta.toString() );

        parseMeta(meta);

        //V1: Meta meta = records.getMeta();
        //V1: Timeseries timeseries = meta.getTimeseries();
        //V1: logger.info("Code: {}", timeseries.getCode());
        //V1: logger.info("Code: {}", timeseries.getDescription());
        //V1: logger.info("Code: {}", timeseries.getFormatValueOnly());
        //V1: logger.info("Code: {}", timeseries.getFormatWithUnit());
        //x logger.info("records: {}", history.getNumRecords());
    }

    void parseMeta(Map<String, Object> meta) {
        logger.info("parseMeta:");
        for (Map.Entry<String, Object> entry : meta.entrySet()) {
            logger.info("*** KEY: {}", entry.getKey());
            //logger.info("*** KEY: " + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.info("> parameterCd: {}", parameterCd.toString());

            //Object value = entry.getValue();

            Object object = entry.getValue();

            logger.info("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.info("outerlist: {}", outerList.toString());

            logger.info("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.info("itemObject: {}", itemObject);

                if (object instanceof ArrayList) {
                    logger.info("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.info("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.info("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.info("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.info("second class: {}", second.getClass());
                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.info("timeStampString: {}", timeStampString);
                        //logger.info("timeStampString: OKOK");
                    } else {
                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
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
        logger.info("describeHistoryData:");
        Records records = history.getRecords();
        Map<String, Object> data = null; //V2: records.getData();
        logger.info("describeHistoryData: data: {}", data.toString() );

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.info("***" + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.info("> parameterCd: {}", parameterCd.toString());

            Object object = entry.getValue();

            logger.info("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.info("outerlist: {}", outerList.toString());

            logger.info("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.info("itemObject: {}", itemObject);

                if (object instanceof ArrayList) {
                    logger.info("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.info("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.info("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.info("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.info("second class: {}", second.getClass());
                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.info("timeStampString: {}", timeStampString);
                        //logger.info("timeStampString: OKOK");
                    } else {
                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
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
        logger.info("describeHistoryData:");
        Records records = history.getRecords();
        Map<String, Object> data = null; //V2: records.getData();
        logger.info("describeHistoryData: data: {}", data.toString() );

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.info("***" + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.info("> parameterCd: {}", parameterCd.toString());

            Object object = entry.getValue();

            logger.info("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.info("outerlist: {}", outerList.toString());

            logger.info("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.info("itemObject: {}", itemObject);

                if (object instanceof ArrayList) {
                    logger.info("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.info("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.info("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.info("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.info("second class: {}", second.getClass());
                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.info("timeStampString: {}", timeStampString);
                        //logger.info("timeStampString: OKOK");
                    } else {
                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
                    }
                }
            }
        }

//            System.out.println(listIterator.next());
//            System.out.println(listIterator.next());

//            while (outerList.iterator().hasNext()) {
//                Object itemObject = outerList.iterator().next();
//                logger.info("itemObject: {}", itemObject );
//            }


       // }
        //V1: List<List<String>> timeseries = data.getTimeseries();
        //V1: for (List<String> innerList : timeseries) {
            //logger.info("TYPE: {}", innerList);
            //logger.info("SIZE: {}", innerList.size());
            //logger.info("DATA: {}", innerList.toArray()[1]);
            //logger.info("OBJECT: {}", array[0]);
            //logger.info("OBJECT: {}", array[1]);
            //logger.info("VALUE: {}", array[1]);

        //V1: Object[] array = innerList.toArray();
        //V1: long timeStampEpochSecond = Long.parseLong((String)array[0]);
        //V1: double decimalValue = parseDecimalValue((String)array[1]);
        //V1: logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), decimalValue );


            //for (String number : innerList) {
                // Process the number
                //System.out.println(number);
            //}

            //logger.info("TS: {}", number[0]);
            //logger.info("V : {}", number[1]);
        //V1: }
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
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataV2(History history) {
        logger.info("describeHistoryData:");
        Records records = history.getRecords();
        Map<String, Object> data = null; //V2: records.getData();
        logger.info("describeHistoryData: data: {}", data.toString() );

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            logger.info("***" + entry.getKey() + ":" + entry.getValue());
            String numberString = entry.getKey();
            String codeString = "bv";
            String unitString = "V";
            String descriptionString = "Battery Volts";
            ParameterCd parameterCd = ParameterCdFactory.getParameterCd(numberString, codeString, unitString, descriptionString);
            logger.info("> parameterCd: {}", parameterCd.toString());

            Object object = entry.getValue();

            logger.info("object-class: {}", object.getClass());

            List<Object> outerList = null;
            List<Object> innerList = null;
            if (object instanceof ArrayList) {
                outerList = (List<Object>) object;
            }
            logger.info("outerlist: {}", outerList.toString());

            logger.info("outerList.size: {}", outerList.size());

            ListIterator<Object> listIterator = outerList.listIterator();

            String timeStampString = null;
            String valueString = null;
            while (listIterator.hasNext()) {
                Object itemObject = listIterator.next();
                logger.info("itemObject: {}", itemObject);

                if (object instanceof ArrayList) {
                    logger.info("itemObject: {}", true);
                    innerList = (List<Object>) itemObject;
                    logger.info("innerList.size: {}", innerList.size());
                    if (innerList.size() == 2) {
                        logger.info("innerList.size: {}", "OK");
                        Object first = innerList.get(0);
                        Object second = innerList.get(1);

                        logger.info("first class: {}", first.getClass());
                        long timeStampEpochSecond = ((Integer)first).longValue();

                        if (second != null) {
                            logger.info("second class: {}", second.getClass());
                            logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        } else {
                            logger.warn("second class: {}", "WAS NULL - not writing the values");
                        }

                        //double decimalValue = ((Double)second).doubleValue();

                        //logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), second );
                        //logger.info("timeStampString: {}", timeStampString);
                        //logger.info("timeStampString: OKOK");
                    } else {
                        logger.info("timeStampString: SIZE ISSUE: NOT OK");
                    }
                }
            }
        }

//            System.out.println(listIterator.next());
//            System.out.println(listIterator.next());

//            while (outerList.iterator().hasNext()) {
//                Object itemObject = outerList.iterator().next();
//                logger.info("itemObject: {}", itemObject );
//            }


        // }
        //V1: List<List<String>> timeseries = data.getTimeseries();
        //V1: for (List<String> innerList : timeseries) {
        //logger.info("TYPE: {}", innerList);
        //logger.info("SIZE: {}", innerList.size());
        //logger.info("DATA: {}", innerList.toArray()[1]);
        //logger.info("OBJECT: {}", array[0]);
        //logger.info("OBJECT: {}", array[1]);
        //logger.info("VALUE: {}", array[1]);

        //V1: Object[] array = innerList.toArray();
        //V1: long timeStampEpochSecond = Long.parseLong((String)array[0]);
        //V1: double decimalValue = parseDecimalValue((String)array[1]);
        //V1: logger.info("> {}: {}", TimeUtils.convertEpochSecond(timeStampEpochSecond), decimalValue );


        //for (String number : innerList) {
        // Process the number
        //System.out.println(number);
        //}

        //logger.info("TS: {}", number[0]);
        //logger.info("V : {}", number[1]);
        //V1: }
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
     * Describes the history time-series data information.
     * @param history
     */
    public void describeHistoryDataV1(History history) {
        logger.info("describeHistoryData:");
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
            double decimalValue = parseDoubleValue((String)array[1]);
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