package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.text.DecimalFormat;

/**
 * Panel displaying statistical summaries of activity data.
 * Shows average step count, active minutes, and calories burned.
 */
public class StatsPanel extends JPanel {
    private JLabel avgStepsLabel, avgActiveLabel, avgCaloriesLabel;
    private static final DecimalFormat df = new DecimalFormat("#,##0");

    /**
     * Constructs a StatsPanel with predefined styling and layout.
     */
    public StatsPanel() {
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Statistics"));
        setBackground(Color.WHITE);

        avgStepsLabel = createStyledLabel("Avg Steps: 0");
        avgActiveLabel = createStyledLabel("Avg Active Min: 0");
        avgCaloriesLabel = createStyledLabel("Avg Calories: 0");

        add(avgStepsLabel);
        add(avgActiveLabel);
        add(avgCaloriesLabel);
    }

    /**
     * Updates the displayed statistics based on the provided activity list.
     *
     * @param activities List of activity records
     */
    public void updateStats(List<Activity> activities) {
        avgStepsLabel.setText("Avg Steps: " + df.format(calculateAverage(activities, "steps")));
        avgActiveLabel.setText("Avg Active Min: " + df.format(calculateAverage(activities, "active")));
        avgCaloriesLabel.setText("Avg Calories: " + df.format(calculateAverage(activities, "calories")));
    }

    /**
     * Creates a styled label for displaying statistics.
     *
     * @param text Initial label text
     * @return Configured JLabel instance
     */
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        return label;
    }

    /**
     * Calculates the average value for a specified metric.
     *
     * @param activities List of activities to analyze
     * @param type The metric to calculate ("steps", "active", "calories")
     * @return The calculated average value
     */
    private double calculateAverage(List<Activity> activities, String type) {
        if (activities.isEmpty()) return 0;
        return switch (type) {
            case "steps" -> activities.stream().mapToInt(Activity::getTotalSteps).average().orElse(0);
            case "active" -> activities.stream().mapToInt(Activity::getVeryActiveMinutes).average().orElse(0);
            case "calories" -> activities.stream().mapToInt(Activity::getCalories).average().orElse(0);
            default -> 0;
        };
    }
}
