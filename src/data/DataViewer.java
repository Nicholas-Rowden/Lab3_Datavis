package data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

/**
 * Main GUI class that provides an interface for viewing activity data.
 * Integrates multiple panels for tabular data, statistics, charts, filtering, and details.
 */
public class DataViewer {
    private static TablePanel tablePanel;
    private static StatsPanel statsPanel;
    private static ChartPanel chartPanel;
    private static DetailsPanel detailsPanel;
    private static FilterPanel filterPanel;
    private static List<Activity> activities;

    /**
     * Initializes and displays the graphical user interface.
     *
     * @param activitiesList List of activity records to be displayed
     */
    public static void createGUI(List<Activity> activitiesList) {
        if (activitiesList.isEmpty()) {
            System.out.println("Error: No data to display.");
            return;
        }

        activities = activitiesList;

        JFrame frame = new JFrame("Activity Data Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize panels
        tablePanel = new TablePanel();
        statsPanel = new StatsPanel();
        chartPanel = new ChartPanel();
        detailsPanel = new DetailsPanel();

        // Filter panel with filtering functionality
        filterPanel = new FilterPanel(filteredData -> {
            tablePanel.updateTable(filteredData);
            statsPanel.updateStats(filteredData);
            chartPanel.updateChart(filteredData);
        }, () -> {
            tablePanel.updateTable(activities);
            statsPanel.updateStats(activities);
            chartPanel.updateChart(activities);
        }, activities);

        // Populate table and chart with initial data
        tablePanel.updateTable(activities);
        chartPanel.updateChart(activities);

        // Ensure clicking a table row updates details and chart
        tablePanel.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && tablePanel.getTable().getSelectedRow() != -1) {
                    int row = tablePanel.getTable().getSelectedRow();
                    if (row >= 0 && row < activities.size()) {
                        Activity selectedActivity = activities.get(row);
                        detailsPanel.updateDetails(selectedActivity);
                        chartPanel.updateChart(List.of(selectedActivity)); // Show only selected row in chart
                    }
                }
            }
        });

        // Layout arrangement
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
        centerPanel.add(statsPanel, BorderLayout.SOUTH);

        frame.add(filterPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(chartPanel, BorderLayout.EAST);
        frame.add(detailsPanel, BorderLayout.WEST);

        frame.setSize(1100, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
