package data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.nio.file.Paths;

public class DataViewer {
    private static TablePanel tablePanel;
    private static StatsPanel statsPanel;
    private static ChartPanel chartPanel;
    private static DetailsPanel detailsPanel;
    private static FilterPanel filterPanel;

    public static void createGUI() {
        // Use a relative path for the data file
        String filePath = Paths.get("src", "Resources", "data.csv").toString();

        // Load data
        List<Activity> activities = DataLoader.loadData(filePath);

        if (activities.isEmpty()) {
            System.out.println("Error: No data loaded from file " + filePath);
            return; // Exit if no data is found
        }
        System.out.println("Loaded " + activities.size() + " records from " + filePath);

        JFrame frame = new JFrame("Activity Data Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize components
        tablePanel = new TablePanel();
        statsPanel = new StatsPanel();
        chartPanel = new ChartPanel();
        detailsPanel = new DetailsPanel();

        // FilterPanel setup with lambda expressions for updating panels
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

        // Table selection listener to update details and chart
        tablePanel.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tablePanel.getTable().getSelectedRow() != -1) {
                    int row = tablePanel.getTable().getSelectedRow();
                    if (row >= 0 && row < activities.size()) {
                        detailsPanel.updateDetails(activities.get(row));
                        chartPanel.updateChart(List.of(activities.get(row))); // Show only selected data
                    }
                }
            }
        });

        // Set frame properties
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}
