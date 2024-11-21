package gov.usgs.utils;

import java.io.File;

public class FileSet {
    protected String directoryName = "data";
    protected String inFilename = null;
    protected String outFilename = null;
    protected File infile = null;
    protected File outfile = null;

    public FileSet(String inFilename, String outFilename) {
        this.inFilename = inFilename;
        this.outFilename = outFilename;
        init();
    }

    void init() {
        this.infile = new File(directoryName,inFilename);
        this.outfile = new File(directoryName,outFilename);
    }

    public File getInfile() {
        return infile;
    }

    public File getOutfile() {
        return outfile;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
}
