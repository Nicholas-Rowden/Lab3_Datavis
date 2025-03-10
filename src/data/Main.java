package data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Main class to execute the data visualization application
public class Main {
    public static void main(String[] args) {
        // Use relative path to load activity data
        String relativePath = "src/Resources/dailyActivity_merged.csv";
        ArrayList<Activity> activities = DataLoader.loadActivityData(relativePath);

        // Console output: Print first and tenth entries if available
        if (!activities.isEmpty()) {
            System.out.println("First Entry:\n" + activities.get(0));
            System.out.println("\nTenth Entry:\n" + (activities.size() >= 10 ? activities.get(9) : "Not enough data"));
        }
        // Print total number of entries
        System.out.println("\nTotal Entries: " + activities.size());

        // Launch GUI for data visualization
        javax.swing.SwingUtilities.invokeLater(() -> DataViewer.createGUI(activities));
    }
}