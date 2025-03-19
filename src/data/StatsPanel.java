package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.text.DecimalFormat;

public class StatsPanel extends JPanel {
    private JLabel avgStepsLabel, avgActiveLabel, avgCaloriesLabel;
    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public StatsPanel() {
        setLayout(new GridLayout(3, 1));
        avgStepsLabel = new JLabel("Avg Steps: 0");
        avgActiveLabel = new JLabel("Avg Active Min: 0");
        avgCaloriesLabel = new JLabel("Avg Calories: 0");

        add(avgStepsLabel);
        add(avgActiveLabel);
        add(avgCaloriesLabel);
    }

    public void updateStats(List<Activity> activities) {
        avgStepsLabel.setText("Avg Steps: " + df.format(calculateAverage(activities, "steps")));
        avgActiveLabel.setText("Avg Active Min: " + df.format(calculateAverage(activities, "active")));
        avgCaloriesLabel.setText("Avg Calories: " + df.format(calculateAverage(activities, "calories")));
    }

    private double calculateAverage(List<Activity> activities, String type) {
        if (activities.isEmpty()) return 0;
        return switch (type) {
            case "steps" -> activities.stream().mapToDouble(Activity::getTotalSteps).average().orElse(0);
            case "active" -> activities.stream().mapToDouble(Activity::getVeryActiveMinutes).average().orElse(0);
            case "calories" -> activities.stream().mapToDouble(Activity::getCalories).average().orElse(0);
            default -> 0;
        };
    }
}
