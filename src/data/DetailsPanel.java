package data;

import javax.swing.*;

/**
 * A panel for displaying detailed activity information.
 * Extends JTextArea to provide a formatted, non-editable text output.
 */
public class DetailsPanel extends JTextArea {
    /**
     * Constructs a DetailsPanel with predefined styling and dimensions.
     */
    public DetailsPanel() {
        setEditable(false);
        setRows(6);
        setColumns(25);
        setBorder(BorderFactory.createTitledBorder("Activity Details"));
    }

    /**
     * Updates the displayed details based on the selected Activity.
     *
     * @param activity The Activity object containing data to display
     */
    public void updateDetails(Activity activity) {
        setText("ID: " + activity.getId() + "\n" +
                "Date: " + activity.getDate() + "\n" +
                "Steps: " + activity.getTotalSteps() + "\n" +
                "Active Min: " + activity.getVeryActiveMinutes() + "\n" +
                "Sedentary Min: " + activity.getSedentaryMinutes() + "\n" +
                "Calories: " + activity.getCalories());
    }
}