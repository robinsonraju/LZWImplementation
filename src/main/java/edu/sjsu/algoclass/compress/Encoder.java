package edu.sjsu.algoclass.compress;

/**
 *
 * @author rraju
 */
public interface Encoder {

    public void compressFile(String inputFile, String outputFile);

    public String compressFile(String inputFile);
    
    public String compressString(String inputStr);
}
