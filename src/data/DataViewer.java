package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DataViewer {
    private static TablePanel tablePanel;
    private static StatsPanel statsPanel;
    private static ChartPanel chartPanel;
    private static DetailsPanel detailsPanel;
    private static FilterPanel filterPanel;

    public static void createGUI(List<Activity> activities) {
        if (activities.isEmpty()) {
            System.out.println("Error: No data to display.");
            return;
        }

        JFrame frame = new JFrame("Activity Data Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize components
        tablePanel = new TablePanel();
        statsPanel = new StatsPanel();
        chartPanel = new ChartPanel();
        detailsPanel = new DetailsPanel();

        // FilterPanel setup
        filterPanel = new FilterPanel(filteredData -> {
            tablePanel.updateTable(filteredData);
            statsPanel.updateStats(filteredData);
            chartPanel.updateChart(filteredData);
        }, () -> {
            tablePanel.updateTable(activities);
            statsPanel.updateStats(activities);
            chartPanel.updateChart(activities);
        }, activities);

        // Ensure table is populated at startup
        tablePanel.updateTable(activities);

        // Add components to frame
        frame.add(filterPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(statsPanel, BorderLayout.SOUTH);
        frame.add(chartPanel, BorderLayout.EAST);
        frame.add(detailsPanel, BorderLayout.WEST);

        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
