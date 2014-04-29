/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sjsu.algoclass.compress.common;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author rraju
 */
public class FileUtilTest extends TestCase {

    private static String INPUT = "/temp/input.txt";

    public void testReadInFile() {
        File inputFile = new File(INPUT);
        byte[] data = null;
        try {
            data = FileUtil.readInFile(inputFile);
        } catch (IOException ex) {
            Logger.getLogger(FileUtilTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertNotNull(data);
    }
}
