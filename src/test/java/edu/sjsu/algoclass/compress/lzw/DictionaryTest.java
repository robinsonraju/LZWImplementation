/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sjsu.algoclass.compress.lzw;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rraju
 */
public class DictionaryTest {

    Dictionary dictionary = null;

    public DictionaryTest() {
    }

    @Before
    public void setUp() {
        dictionary = new Dictionary();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIsInitialized(){
        assertTrue(dictionary.isInitialized());
    }
 
    @Test
    public void testIsInDictionary(){
        assertTrue(dictionary.isInDictionary("a"));
        assertTrue(dictionary.isInDictionary("A"));
    }
    
    @Test
    public void testValuesInDictionary(){
        assertTrue(new Integer(97).equals(dictionary.lookupKey("a")));
        assertTrue(new Integer(65).equals(dictionary.lookupKey("A")));
    }
    
    @Test
    public void addToDictionary(){
        // Add string that is not found in the dictionary
        String str = "ab";
        dictionary.addToDictionary(str);
        assertTrue(dictionary.getMaxValue() == dictionary.lookupKey(str).intValue());
    }
}