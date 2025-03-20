package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for loading activity data from a CSV file.
 * Parses and converts data into a list of Activity objects.
 */
public class DataLoader {
    /**
     * Loads activity data from the specified CSV file.
     *
     * @param filePath The path to the CSV file
     * @return List of Activity objects representing the data
     */
    public static List<Activity> loadData(String filePath) {
        List<Activity> activities = new ArrayList<>();
        File file = new File(filePath);

        System.out.println("Attempting to load file: " + file.getAbsolutePath());

        if (!file.exists()) {
            System.out.println("ERROR: File not found at " + file.getAbsolutePath());
            return activities;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 6) {
                    try {
                        Activity activity = new Activity(
                                values[0], // ID
                                values[1], // Date
                                parseDoubleToInt(values[2]), // Steps
                                parseDoubleToInt(values[3]), // Active Minutes
                                parseDoubleToInt(values[4]), // Sedentary Minutes
                                parseDoubleToInt(values[5])  // Calories
                        );
                        activities.add(activity);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid row: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
        return activities;
    }

    /**
     * Parses a numeric string and converts it to an integer.
     * Handles cases where the value might be in decimal format.
     *
     * @param value The numeric string to parse
     * @return The parsed integer value, or 0 if invalid
     */
    private static int parseDoubleToInt(String value) {
        try {
            return (int) Math.round(Double.parseDouble(value.trim()));
        } catch (NumberFormatException e) {
            System.out.println("Warning: Invalid number format for value: " + value);
            return 0;
        }
    }
}
