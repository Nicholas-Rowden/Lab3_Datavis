package data;

import javax.swing.*;

public class DetailsPanel extends JTextArea {
    public DetailsPanel() {
        setEditable(false);
        setRows(5);
        setColumns(30);
    }

    public void updateDetails(Activity activity) {
        setText("ID: " + activity.getId() + "\n" +
                "Date: " + activity.getDate() + "\n" +
                "Steps: " + activity.getTotalSteps() + "\n" +
                "Active Min: " + activity.getVeryActiveMinutes() + "\n" +
                "Sedentary Min: " + activity.getSedentaryMinutes() + "\n" +
                "Calories: " + activity.getCalories());
    }
}
