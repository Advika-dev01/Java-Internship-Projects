package RFT_Internship_Day_12;

import java.io.*;
import java.util.*;
class Employee {
    int id;
    String name;
    String department;
    double salary;
    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class CSVReportGenerator {
    public static void main(String[] args) {
        String fileName = "employees.csv";

        ArrayList<Employee> employees = new ArrayList<>();
        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                if (data.length != 4) {
                    System.out.println("Invalid Row Skipped: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String department = data[2];
                    double salary = Double.parseDouble(data[3]);

                    Employee emp = new Employee(id, name, department, salary);
                    employees.add(emp);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid Number Format: " + line);
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error Reading File");
            return;
        }

        // Find Average Salary Per Department
        HashMap<String, Double> totalSalary = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();

        for (Employee emp : employees) {
            totalSalary.put(
                    emp.department,
                    totalSalary.getOrDefault(emp.department, 0.0) + emp.salary
            );
            // Count employees
            count.put(
                    emp.department,
                    count.getOrDefault(emp.department, 0) + 1
            );
        }

        // Find Highest Paid Employee
        Employee highestPaid = employees.get(0);
        for (Employee emp : employees) {
            if (emp.salary > highestPaid.salary) {
                highestPaid = emp;
            }
        }

        // Display Report
        System.out.println("\n........ EMPLOYEE REPORT ..........");
        System.out.println("\nAverage Salary Per Department:");
        for (String dept : totalSalary.keySet()) {
            double average = totalSalary.get(dept) / count.get(dept);
            System.out.println(dept + " : " + average);
        }
        System.out.println("\nHighest Paid Employee:");
        System.out.println(      highestPaid.name +
                          " (" + highestPaid.department + ")" +
                 " - Salary: " + highestPaid.salary
        );

        try {    // Save Report To File

            BufferedWriter writer =  new BufferedWriter(new FileWriter("report.txt"));

            writer.write("............ EMPLOYEE REPORT ..............\n\n");
            writer.write("Average Salary Per Department:\n");
            for (String dept : totalSalary.keySet()) {
                double average = totalSalary.get(dept) / count.get(dept);
                writer.write(dept + " : " + average + "\n");
            }
            writer.write("\nHighest Paid Employee:\n");
            writer.write(
                        highestPaid.name +
                            " (" + highestPaid.department + ")" +
                            " - Salary: " + highestPaid.salary
            );
            writer.close();
            System.out.println("\nReport saved to report.txt");

        } catch (IOException e) {
            System.out.println("Error Writing Report File");
        }
    }
}
