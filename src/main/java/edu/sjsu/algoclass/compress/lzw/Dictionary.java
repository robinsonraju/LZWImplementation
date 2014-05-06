/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sjsu.algoclass.compress.lzw;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rraju
 */
public class Dictionary {

    // Size of the lookup_table. To initialize with first 256 characters
    private int LT_SIZE = 256;
    private Map<String, Integer> lookup_table = null;
    private Map<Integer, String> reverse_lookup_table = null;
    
    private int maxValue = LT_SIZE - 1;

    public Dictionary() {
        initialize();
    }

    private void initialize() {
        lookup_table = new HashMap<String, Integer>();
        reverse_lookup_table = new HashMap<Integer, String>();
        for (int i = 0; i < LT_SIZE; i++) {
            lookup_table.put("" + (char) i, i);
            reverse_lookup_table.put(i, "" + (char) i);
        }
    }

    public boolean isInitialized() {
        return lookup_table.size() > 0;
    }

    public boolean isInDictionary(String key) {
        return lookup_table.containsKey(key);
    }

    public Number lookupKey(String key) {
        return lookup_table.get(key);
    }

    public String lookupValue(Number value) {
        return reverse_lookup_table.get((Integer)value);
    }
        
    public void addToDictionary(String key) {
        lookup_table.put(key, new Integer(++maxValue));
        reverse_lookup_table.put(maxValue, key);
    }
    
    public int getMaxValue(){
        return maxValue;
    }
}
