package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> splitSignature = new ArrayList<>();

        splitSignature.add(source);

        for (String delimiter : delimiters) {
            List<String> tempList = new ArrayList<>();
            for (String str : splitSignature) {
                String[] parts = str.split(delimiter, -1);
                for (String part : parts) {
                    if (!part.isEmpty()) {
                        tempList.add(part);
                    }
                }
            }
            splitSignature = tempList;
        }

        return splitSignature;
    }
}
