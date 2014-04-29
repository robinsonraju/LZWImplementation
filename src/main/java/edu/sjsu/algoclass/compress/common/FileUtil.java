/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sjsu.algoclass.compress.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author rraju
 */
public class FileUtil {

    public static byte[] readInFile(File file) throws IOException {
        byte[] data = new byte[(int) file.length()];
        FileInputStream is = new FileInputStream(file);

        try {
            is.read(data);
        } finally {
            is.close();
        }

        return data;
    }
}
