package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * File io class
 */
public class FileIO {

    public static Map<String, String[]> readFeatureWords(String path) {

        Map<String, String[]> fw = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] comp = line.split("\t");
                if (comp.length < 2 || comp[1].length()==0) continue;
                String id = comp[0];
                String[] words = comp[1].split(" ");
                fw.put(id, words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fw;
    }

}
