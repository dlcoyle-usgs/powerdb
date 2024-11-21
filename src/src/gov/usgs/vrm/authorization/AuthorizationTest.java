package gov.usgs.vrm.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.usgs.utils.FileSet;
import gov.usgs.utils.FileSetUtils;

import java.io.IOException;

/**
 * TODO: Move to test.
 */
public class AuthorizationTest {
    FileSet fileSet = null;
    Authorization authorization = null;

    public static void main(String[] args) throws IOException {

        AuthorizationTest carTest = new AuthorizationTest();
        carTest.init();
        carTest.unmarshalJSON();
        carTest.marshalJSON();

    }

    void init() {
        info("init:");
        this.fileSet = FileSetUtils.getFileSet("vrm-authorization.json", "vrm-authorization-2.json");
    }

    void unmarshalJSON() throws IOException {
        info("unmarshalJSON:");

        ObjectMapper objectMapper = new ObjectMapper();

        authorization = objectMapper.readValue(fileSet.getInfile(), Authorization.class);

        info(authorization.toString());
        info("Token", authorization.getToken());
        info("IdUser", authorization.getIdUser());
        info("VMode", authorization.getVerificationMode());
        info("VSent", Boolean.toString(authorization.getVerificationSent()));


        //info(authorization.getType());
        //info(authorization.getColor());

    }

    void marshalJSON() throws IOException {
        info("marshalJSON:");

        ObjectMapper objectMapper = new ObjectMapper();

        //authorization.setColor("Red");

        objectMapper.writeValue(fileSet.getOutfile(), authorization);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    // LOGGING UTILS:
    ///////////////////////////////////////////////////////////////////////////////////////////////
    void info(String value) {
        System.out.printf("info: [%s]\n", value);
    }
    void info(String name, String value) {
        System.out.printf("%12s: %s\n", name, value);
    }
    void info(String name, Long value) {
        System.out.printf("%12s: %s\n", name, value.toString());
    }
}
