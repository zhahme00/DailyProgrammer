package com.zee.testing;

import java.io.*;
import java.util.*;

// http://www.reddit.com/r/dailyprogrammer/comments/2nihz6/20141126_challenge_190_intermediate_words_inside/
public class Daily190I {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : "enable1.txt";
        Set<String> lookup = new HashSet<>();
        loadLookup(lookup, fileName);
        String highestWord = getMaxInnerWordWord(lookup);

        System.out.format("%s has the most inner words", highestWord);
    }

    private static String getMaxInnerWordWord(Set<String> lookup) {
        int maxWordCount = 0;
        String highestWord = null;
        for(String word: lookup) {
            int wordCount = getInnerWords(lookup, word).size();
            if (wordCount > maxWordCount) {
                highestWord = word;
                maxWordCount = wordCount;
            }
        }

        return  highestWord;
    }

    private static void loadLookup(Set<String> lookup, String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String word = null;
            while ((word = reader.readLine()) != null) {
                lookup.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Collection<CharSequence> getInnerWords(Set<String> lookup, String word) {
        // Total possible inner words = Summation of the length of the word to query.
        int possibleInnerWords = (word.length() * (word.length() + 1)) / 2;
        List<CharSequence> innerWords = new ArrayList<>(possibleInnerWords);

        for(int len = word.length() - 1; len >= 2; len--) {
            for(int i = 0; i + len <= word.length(); i++) {
                String subWord = word.substring(i, i + len);
                if (lookup.contains(subWord)) {
                    //System.out.println(subWord);
                    innerWords.add(subWord);
                }
            }
        }

        return innerWords;
    }
}
