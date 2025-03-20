package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * A panel providing user controls to filter activity data.
 * Users can filter data by minimum steps, maximum calories, and minimum active minutes.
 */
public class FilterPanel extends JPanel {
    private JTextField minStepsField, maxCaloriesField, minActiveMinutesField;

    /**
     * Constructs a FilterPanel with input fields and buttons for filtering activity data.
     *
     * @param onFilterApplied Callback function triggered when filters are applied
     * @param onClearFilters Callback function triggered when filters are cleared
     * @param allActivities List of all Activity records to filter
     */
    public FilterPanel(Consumer<List<Activity>> onFilterApplied, Runnable onClearFilters, List<Activity> allActivities) {
        setLayout(new FlowLayout());

        minStepsField = new JTextField(5);
        maxCaloriesField = new JTextField(5);
        minActiveMinutesField = new JTextField(5);
        JButton applyFilter = new JButton("Apply Filters");
        JButton clearFilter = new JButton("Clear Filters");

        // Listener to apply filters when the button is clicked
        applyFilter.addActionListener(e -> {
            try {
                Integer minSteps = parseInteger(minStepsField.getText());
                Integer maxCalories = parseInteger(maxCaloriesField.getText());
                Integer minActiveMinutes = parseInteger(minActiveMinutesField.getText());

                // Filter activities based on input criteria
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

        // Listener to clear filters when the button is clicked
        clearFilter.addActionListener(e -> {
            minStepsField.setText("");
            maxCaloriesField.setText("");
            minActiveMinutesField.setText("");
            onClearFilters.run();
        });

        // Add UI components to the panel
        add(new JLabel("Min Steps:"));
        add(minStepsField);
        add(new JLabel("Max Calories:"));
        add(maxCaloriesField);
        add(new JLabel("Min Active Minutes:"));
        add(minActiveMinutesField);
        add(applyFilter);
        add(clearFilter);
    }

    /**
     * Parses an input string to an Integer, returning null if the input is empty.
     *
     * @param text Input text to be converted to an Integer
     * @return Integer value if valid, otherwise null
     */
    private Integer parseInteger(String text) {
        return text.isEmpty() ? null : Integer.parseInt(text.trim());
    }
}
