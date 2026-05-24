package RFT_Internship_Day_14;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileSearchEngine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> wordCount = new HashMap<>();  // Store word frequency
        ArrayList<Integer> lineNumbers = new ArrayList<>();   // Store line numbers
        int totalOccurrences = 0;
        try {
            File file = new File("data.txt");
            Scanner fileReader = new Scanner(file);

            System.out.print("Enter word to search: ");
            String searchWord = sc.nextLine().toLowerCase();
            int lineNumber = 0;
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                lineNumber++;

                String lowerLine = line.toLowerCase();
                String[] words = lowerLine.split(" ");

                boolean foundInLine = false;
                for (String word : words) {
                    wordCount.put(word,wordCount.getOrDefault(word, 0) + 1);

                    if (word.equals(searchWord)) {
                        totalOccurrences++;
                        foundInLine = true;
                    }
                }

                if (foundInLine) {                 // Store line number
                    lineNumbers.add(lineNumber);
                }
            }

            System.out.println("\n......... SEARCH RESULT ..........");
            if (totalOccurrences > 0) {
                System.out.println("Word Found!");
                System.out.println("Total Occurrences: " + totalOccurrences);
                System.out.println("Line Numbers: " + lineNumbers);

            } else {
                System.out.println("Word Not Found!");
            }

            System.out.println("\n............ WORD FREQUENCY ...........");

            for (Map.Entry<String, Integer> entry
                    : wordCount.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        sc.close();
    }
}
