/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sjsu.algoclass.compress.lzw;

import edu.sjsu.algoclass.compress.Decoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rraju
 */
public class LZWDecoder implements Decoder {

    /**
     *
     * @param inputFile
     * @param outputFile
     */
    public void decompressFile(String inputFile, String outputFile) {
        Dictionary dictionary = new Dictionary();

        Reader in = null;
        Writer out = null;
        try {
            FileInputStream inputStsream = new FileInputStream(inputFile);
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            InputStreamReader isr = new InputStreamReader(inputStsream, "UTF8");
            in = new BufferedReader(isr);
            out = new OutputStreamWriter(outputStream, "UTF8");

            int inputCharVal;
            String dictionaryLookup = null;
            String prevWordInDictionary = "";

            while ((inputCharVal = in.read()) > -1) {
                dictionaryLookup = dictionary.lookupValue(inputCharVal);
                char currChar = (char) inputCharVal;
                String currWord = prevWordInDictionary + currChar;
                if (dictionary.isInDictionary(currWord)) {
                    prevWordInDictionary = currWord;
                    out.write(dictionaryLookup);
                } else {
                    out.write(dictionaryLookup);

                    // Add currWord to the dictionary.
                    dictionary.addToDictionary(currWord);
                    prevWordInDictionary = "" + currChar;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LZWEncoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LZWEncoder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception ex) {
                Logger.getLogger(LZWEncoder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
