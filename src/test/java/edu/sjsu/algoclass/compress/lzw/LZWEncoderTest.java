package edu.sjsu.algoclass.compress.lzw;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rraju
 */
public class LZWEncoderTest {

    private static String INPUT = "/temp/input.txt";
    private static String OUTPUT = "/temp/output.txt";
    LZWEncoder encoder = null;

    public LZWEncoderTest() {
    }

    @Before
    public void setUp() {
        cleanUpOutputFiles();
        encoder = new LZWEncoder();
    }

    @After
    public void tearDown() {
        //cleanUpOutputFiles();
    }

    /**
     * Test compressFile method
     */
    @Test
    public void testCompressFile() {
        File outputFile = new File(OUTPUT);

        // Check if the output file if of 0 length
        assertTrue(outputFile.length() == 0);

        // Compress the input
        encoder.compressFile(INPUT, OUTPUT);

        // Check if the output file if of >0 length
        assertTrue(outputFile.length() > 0);
    }

    /**
     * Test if the encoder converts to the right values. [Expected] to fail if
     * we change the dictionary. Change the test if we change the dictionary or
     * make this more generic.
     */
    @Test
    public void testCompressString() {
        String res = encoder.compressString("sir");
        assertEquals("115 105 114", res);

        String res2 = encoder.compressString("sir sid");
        assertEquals("115 105 114 32 256 100", res2);

    }
    
    private void cleanUpOutputFiles() {
        File enc_output = new File(OUTPUT);
        if (enc_output.exists()) {
            enc_output.delete();
        }
    }
}
