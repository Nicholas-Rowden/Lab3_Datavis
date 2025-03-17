package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static List<Activity> loadData(String filePath) {
        List<Activity> activities = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header row
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 6) {
                    Activity activity = new Activity(
                            values[0], // ID
                            values[1], // Date
                            Integer.parseInt(values[2]), // Steps
                            Integer.parseInt(values[3]), // Active Minutes
                            Integer.parseInt(values[4]), // Sedentary Minutes
                            Integer.parseInt(values[5])  // Calories
                    );
                    activities.add(activity);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
        return activities;
    }
}
