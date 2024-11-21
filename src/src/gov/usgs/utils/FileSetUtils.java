package gov.usgs.utils;

import java.nio.file.Paths;

public class FileSetUtils {

    public static FileSet getFileSet(String inFilename, String outFilename) {
        FileSet fileSet = new FileSet(inFilename,outFilename);
        return fileSet;
    }

    public static void displayFileSet(FileSet fileSet) {
        String cwd = Paths.get("").toAbsolutePath().toString();
        System.out.printf("CWD: [%s]\n", cwd);
        System.out.printf("INFILE: [%s]\n", fileSet.infile.getAbsolutePath());
        System.out.printf("OUTFILE: [%s]\n", fileSet.outfile.getAbsolutePath());
    }

}
