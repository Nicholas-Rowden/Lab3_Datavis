package data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Class responsible for displaying the activity data in a graphical user interface
class DataViewer {
    public static void createGUI(ArrayList<Activity> activities) {
        JFrame frame = new JFrame("Activity Data Viewer"); // Create the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define table columns
        String[] columnNames = {"ID", "Date", "Steps", "Active Min", "Sedentary Min", "Calories"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate table model with activity data
        for (Activity activity : activities) {
            model.addRow(new Object[]{activity.id, activity.date, activity.totalSteps, activity.veryActiveMinutes, activity.sedentaryMinutes, activity.calories});
        }

        // Create and add table to a scroll pane
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Set frame properties and display
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

