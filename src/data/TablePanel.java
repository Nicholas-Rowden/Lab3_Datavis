package data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public TablePanel() {
        String[] columnNames = {"ID", "Date", "Steps", "Active Min", "Sedentary Min", "Calories"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void updateTable(List<Activity> activities) {
        model.setRowCount(0); // Clear old data
        for (Activity activity : activities) {
            model.addRow(new Object[]{activity.getId(), activity.getDate(), activity.getTotalSteps(),
                    activity.getVeryActiveMinutes(), activity.getSedentaryMinutes(), activity.getCalories()});
        }
    }

    public JTable getTable() {
        return table;
    }
}
