package RFT_Internship_Day_11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class AdvancedTextAnalyzer {
    public static void main(String[] args) {
        String filePath = "sample.txt";
        int lineCount = 0;
        int wordCount = 0;
        String longestWord = "";
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                line = line.toLowerCase();

                line = line.replaceAll("[^a-zA-Z0-9 ]", "");

                String[] words = line.split("\\s+");

                for (String word : words) {

                    // Ignore empty strings
                    if (word.length() > 0) {
                        wordCount++;
                        if (wordFrequency.containsKey(word)) {
                            wordFrequency.put(word, wordFrequency.get(word) + 1);
                        } else {
                            wordFrequency.put(word, 1);
                        }

                        if (word.length() > longestWord.length()) {
                            longestWord = word;
                        }
                    }
                }
            }
            br.close();
            String mostFrequentWord = "";
            int maxCount = 0;
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {

                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentWord = entry.getKey();
                }
            }

            // Display results
            System.out.println(".......... TEXT ANALYSIS RESULT ........");
            System.out.println("Total Lines        : " + lineCount);
            System.out.println("Total Words        : " + wordCount);
            System.out.println("Longest Word       : " + longestWord);
            System.out.println("Most Frequent Word : " + mostFrequentWord);
            System.out.println("Frequency Count    : " + maxCount);

            //Display Top 5 Words
            System.out.println("\n........TOP 5 WORDS ...........");

            ArrayList<Map.Entry<String, Integer>> list =
                    new ArrayList<>(wordFrequency.entrySet());

            // Sort by frequency in descending order
            Collections.sort(list, (a, b) -> b.getValue() - a.getValue());

            int limit = Math.min(5, list.size());
            for (int i = 0; i < limit; i++) {
                System.out.println(
                        list.get(i).getKey() + " -> " + list.get(i).getValue()
                );
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
