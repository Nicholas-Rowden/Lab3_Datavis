package data;

import java.nio.file.Paths;
import java.util.List;

/**
 * Entry point for the Activity Data Viewer application.
 * Loads data from a CSV file and initializes the GUI.
 */
public class Main {
    public static void main(String[] args) {
        // Define the relative path to the data file
        String filePath = Paths.get("src", "Resources", "dailyActivity_merged.csv").toString();

        // Load data from file
        List<Activity> activities = DataLoader.loadData(filePath);

        if (activities.isEmpty()) {
            System.out.println("Error: No data loaded!");
            return;
        }

        // Print summary of data
        System.out.println("First entry: " + activities.get(0));
        System.out.println("Tenth entry: " + (activities.size() >= 10 ? activities.get(9) : "Less than 10 entries available."));
        System.out.println("Total entries: " + activities.size());

        // Launch the graphical user interface
        DataViewer.createGUI(activities);
    }
}
