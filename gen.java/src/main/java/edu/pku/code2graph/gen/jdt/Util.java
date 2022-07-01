package edu.pku.code2graph.gen.jdt;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<String> splitKey(String key) {
        int inBlockLevel = 0;
        List<String> results = new ArrayList<>();
        StringBuilder sub = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            if (inBlockLevel == 0 && currentChar == '.') {
                results.add(sub.toString());
                sub = new StringBuilder();
                continue;
            }
            sub.append(currentChar);
            if (currentChar == '(') {
                inBlockLevel++;
                continue;
            }
            if (currentChar == ')') {
                inBlockLevel--;
            }
        }
        results.add(sub.toString());
        return results;
    }

}
