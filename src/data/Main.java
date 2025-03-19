package data;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Use correct relative path inside 'src/Resources/'
        String filePath = Paths.get("src", "Resources", "dailyActivity_merged.csv").toString();

        // Load data
        List<Activity> activities = DataLoader.loadData(filePath);

        if (activities.isEmpty()) {
            System.out.println("Error: No data loaded!");
            return;
        }

        // Print console output
        System.out.println("First entry: " + activities.get(0));
        System.out.println("Tenth entry: " + (activities.size() >= 10 ? activities.get(9) : "Less than 10 entries available."));
        System.out.println("Total entries: " + activities.size());

        // Start the GUI
        DataViewer.createGUI(activities);
    }
}
