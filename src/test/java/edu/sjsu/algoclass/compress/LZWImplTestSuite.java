/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sjsu.algoclass.compress;

import edu.sjsu.algoclass.compress.common.FileUtilTest;
import edu.sjsu.algoclass.compress.lzw.DictionaryTest;
import edu.sjsu.algoclass.compress.lzw.LZWDecoderTest;
import edu.sjsu.algoclass.compress.lzw.LZWEncoderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rraju
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    FileUtilTest.class,
    DictionaryTest.class,
    LZWEncoderTest.class,
    LZWDecoderTest.class})
public class LZWImplTestSuite {
}