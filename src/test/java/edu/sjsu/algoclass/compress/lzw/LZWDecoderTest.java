package edu.sjsu.algoclass.compress.lzw;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rraju
 */
public class LZWDecoderTest {

    private static String ENC_INPUT = "/tmp/input.txt";
    private static String ENC_OUTPUT = "/tmp/enc-output.txt";
    private static String DEC_OUTPUT = "/tmp/dec-output.txt";
    LZWEncoder encoder = null;
    LZWDecoder decoder = null;

    public LZWDecoderTest() {
    }

    @Before
    public void setUp() {
        cleanUpOutputFiles();
        encoder = new LZWEncoder();
        decoder = new LZWDecoder();
    }

    @After
    public void tearDown() {
        //cleanUpOutputFiles();
    }

    /**
     * Test compressFile method
     */
    @Test
    public void testDecompressFile() {
        try {
            // Compress the input
            encoder.compressFile(ENC_INPUT, ENC_OUTPUT);

            // Check if the output file is of length > 0
            assertTrue((new File(ENC_OUTPUT)).length() > 0);

            // Decompress the input
            decoder.decompressFile(ENC_OUTPUT, DEC_OUTPUT);

            // Check if the output file is of length > 0
            assertTrue((new File(DEC_OUTPUT)).length() > 0);
        } catch (Exception ex) {
            Logger.getLogger(LZWDecoderTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    private void cleanUpOutputFiles() {
        File enc_output = new File(ENC_OUTPUT);
        if (enc_output.exists()) {
            enc_output.delete();
        }

        File dec_output = new File(DEC_OUTPUT);
        if (dec_output.exists()) {
            dec_output.delete();
        }
    }
}
