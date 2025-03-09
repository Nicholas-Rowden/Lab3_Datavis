package data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Class responsible for reading and processing data from the CSV file
class DataLoader {
    public static ArrayList<Activity> loadActivityData(String filePath) {
        ArrayList<Activity> activityList = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> dataLines = lines.skip(1).collect(Collectors.toList()); // Skip header row

            for (String line : dataLines) {
                String[] values = line.split(",");
                System.out.println("Columns found: " + values.length + " | Data: " + line); // Debugging line

                if (values.length >= 15) { // Ensure at least 15 columns exist
                    activityList.add(new Activity(
                            values[0], values[1], Integer.parseInt(values[2]),
                            Integer.parseInt(values[10]), Integer.parseInt(values[13]), Integer.parseInt(values[14]) // Fix index
                    ));
                } else {
                    System.out.println("Skipping invalid row: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Could not find file at " + filePath);
            e.printStackTrace();
        }
        return activityList;
    }

}
