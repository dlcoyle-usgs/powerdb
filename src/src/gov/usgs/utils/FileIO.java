package gov.usgs.utils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.*;
import org.apache.commons.io.FileUtils;

/**
 * Provides a collection of file utility methods.
 * @see "https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file"
 */
public class FileIO {


    /**
     * Reads the requested file into a string and closes the file.
     * @param infile
     * @return string
     * @throws IOException
     */
    public static String readFile(File infile) throws IOException {
        //File file = new File("data.txt");
        //org.apache.commons.io.FileUtils.
        return org.apache.commons.io.FileUtils.readFileToString(infile, StandardCharsets.UTF_8);
    }

}
