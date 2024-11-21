package src.gov.usgs.vrm.parsers;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
public class JSONParser {
//    public static void main (String[] args) throws JsonProcessingException {
//        String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 }";
//
//        SimpleModule module =
//                new SimpleModule("CarDeserializer", new Version(3, 1, 8, null, null, null));
//        module.addDeserializer(Car.class, new CarDeserializer(Car.class));
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(module);
//
//        Car car = mapper.readValue(json, Car.class);
//
//    }

}
