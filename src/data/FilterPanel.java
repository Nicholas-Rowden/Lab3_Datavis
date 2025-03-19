package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class FilterPanel extends JPanel {
    private JTextField minStepsField, maxCaloriesField, minActiveMinutesField;

    public FilterPanel(Consumer<List<Activity>> onFilterApplied, Runnable onClearFilters, List<Activity> allActivities) {
        setLayout(new FlowLayout());

        minStepsField = new JTextField(5);
        maxCaloriesField = new JTextField(5);
        minActiveMinutesField = new JTextField(5);
        JButton applyFilter = new JButton("Apply Filters");
        JButton clearFilter = new JButton("Clear Filters");

        applyFilter.addActionListener(e -> {
            try {
                Integer minSteps = parseInteger(minStepsField.getText());
                Integer maxCalories = parseInteger(maxCaloriesField.getText());
                Integer minActiveMinutes = parseInteger(minActiveMinutesField.getText());

                List<Activity> filteredData = allActivities.stream()
                        .filter(a -> (minSteps == null || a.getTotalSteps() >= minSteps))
                        .filter(a -> (maxCalories == null || a.getCalories() <= maxCalories))
                        .filter(a -> (minActiveMinutes == null || a.getVeryActiveMinutes() >= minActiveMinutes))
                        .toList();

                onFilterApplied.accept(filteredData);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearFilter.addActionListener(e -> {
            minStepsField.setText("");
            maxCaloriesField.setText("");
            minActiveMinutesField.setText("");
            onClearFilters.run();
        });

        add(new JLabel("Min Steps:"));
        add(minStepsField);
        add(new JLabel("Max Calories:"));
        add(maxCaloriesField);
        add(new JLabel("Min Active Minutes:"));
        add(minActiveMinutesField);
        add(applyFilter);
        add(clearFilter);
    }

    private Integer parseInteger(String text) {
        return text.isEmpty() ? null : Integer.parseInt(text.trim());
    }
}
