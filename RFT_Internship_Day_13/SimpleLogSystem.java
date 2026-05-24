package RFT_Internship_Day_13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimpleLogSystem {
    public static void main(String[] args) {

        int errorCount = 0;
        int infoCount = 0;
        int warningCount = 0;
        // Store ERROR lines
        ArrayList<String> errorLines = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("SampleLogs.txt"));
            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("ERROR")) {
                    errorCount++;
                    errorLines.add(line);
                }
                else if (line.startsWith("INFO")) {
                    infoCount++;
                }
                else if (line.startsWith("WARNING")) {
                    warningCount++;
                }
            }
            br.close();

            System.out.println("----- LOG COUNTS -----");
            System.out.println("ERROR   : " + errorCount);
            System.out.println("INFO    : " + infoCount);
            System.out.println("WARNING : " + warningCount);

            System.out.println("\n----- MOST FREQUENT LOG -----");
            if (errorCount >= infoCount && errorCount >= warningCount) {
                System.out.println("ERROR");
            }
            else if (infoCount >= errorCount && infoCount >= warningCount) {
                System.out.println("INFO");
            }
            else {
                System.out.println("WARNING");
            }

            System.out.println("\n-----ALL  ERROR LINES -----");
            for (String error : errorLines) {
                System.out.println(error);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
}
