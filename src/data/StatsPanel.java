package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatsPanel extends JPanel {
    private JLabel avgStepsLabel, avgActiveLabel, avgCaloriesLabel;

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
        avgStepsLabel.setText("Avg Steps: " + calculateAverage(activities, "steps"));
        avgActiveLabel.setText("Avg Active Min: " + calculateAverage(activities, "active"));
        avgCaloriesLabel.setText("Avg Calories: " + calculateAverage(activities, "calories"));
    }

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
