/*
 * To change this template, choose Tools | Templates
 * and open the template inputStream the editor.
 */
package edu.sjsu.algoclass.compress.lzw;

import edu.sjsu.algoclass.compress.Encoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is an implementation of LZW Compression algorithm. 
 * 
 * @author rraju
 */
public class LZWEncoder implements Encoder {

    /**
     * <pre>
     * Pseudo code
     * -----------
     * previous_word_in_dictionary = ""
     * while(current_char_value > -1 i.e [input is not exhausted])
     *      current_word = previous_word_in_dictionary + current_char
     *      If (current_word is in dictionary)
     *          previous_word_in_dictionary = current word
     *      else
     *          Write previous_word_in_dictionary to output
     *          Add current_word to dictionary
     *          previous_word_in_dictionary = current_char
     * </pre>
     *
     * @param inputFile
     * @param outputFile
     */
    public void compressFile(String inputFile, String outputFile) {
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
            String prevWordInDictionary = "";

            while ((inputCharVal = in.read()) > -1) {
                char currChar = (char) inputCharVal;
                String currWord = prevWordInDictionary + currChar;
                if (dictionary.isInDictionary(currWord)) {
                    prevWordInDictionary = currWord;
                } else {
                    out.write(dictionary.lookupKey(prevWordInDictionary).intValue());

                    // Add currWord to the dictionary.
                    dictionary.addToDictionary(currWord);
                    prevWordInDictionary = "" + currChar;
                }
            }

            if (!prevWordInDictionary.equals("")) {
                out.write(dictionary.lookupKey(prevWordInDictionary).intValue());
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

    /**
     *
     * @param inputFile String
     * @return outputFile String
     */
    public String compressFile(String inputFile) {
        String outputFile = inputFile + ".out";
        compressFile(inputFile, outputFile);
        return outputFile;
    }

    /**
     *
     * @param uncompressed
     * @return compressed
     */
    public String compressString(String uncompressed) {
        List<Integer> result = getEncodedValues(uncompressed);
        return convertEncodedResultListToString(result);
    }

    /**
     *
     * @param result
     * @return
     */
    private String convertEncodedResultListToString(List<Integer> result) {
        StringBuilder sb = new StringBuilder();
        for (Integer val : result) {
            sb.append(val + " ");
        }
        return sb.toString().trim();
    }

    /**
     *
     * @param uncompressed
     * @return List<Integer> list of integers
     */
    public List<Integer> getEncodedValues(String uncompressed) {
        Dictionary dictionary = new Dictionary();

        String prevWordInDictionary = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char currChar : uncompressed.toCharArray()) {
            String currWord = prevWordInDictionary + currChar;
            if (dictionary.isInDictionary(currWord)) {
                prevWordInDictionary = currWord;
            } else {
                result.add(dictionary.lookupKey(prevWordInDictionary).intValue());
                // Add currWord to the dictionary.
                dictionary.addToDictionary(currWord);
                prevWordInDictionary = "" + currChar;
            }
        }

        if (!prevWordInDictionary.equals("")) {
            result.add(dictionary.lookupKey(prevWordInDictionary).intValue());
        }

        return result;
    }
}
