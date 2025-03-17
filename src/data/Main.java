package data;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Use a relative path for the data file
        String filePath = "C:/Users/rowde/OneDrive/Documents/classes/Spring 2025/Java/labs/Lab3/src/Resources/data.csv";


        // Load data
        List<Activity> activities = DataLoader.loadData(filePath);

        if (activities.isEmpty()) {
            System.out.println("Error: No data loaded!");
            return; // Exit if no data is found
        }

        // Start the GUI
        DataViewer.createGUI();
    }
}
