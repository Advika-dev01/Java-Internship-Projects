package RFT_Internship_Day_15;

import java.io.*;
import java.util.ArrayList;

public class DataFilterExportTool {
    public static void main(String[] args) {

        String inputFile = "employeesdata.txt";
        String outputFile = "filtered_data.txt";

        ArrayList<String> filteredData = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            String header = br.readLine();
            filteredData.add(header);

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                int salary = Integer.parseInt(data[2]);

                // Filter condition
                if (salary > 50000 && age < 30) {
                    filteredData.add(line);
                }
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
            for (String record : filteredData) {
                bw.write(record);
                bw.newLine();
            }

            bw.close();
            System.out.println("Filtered data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
