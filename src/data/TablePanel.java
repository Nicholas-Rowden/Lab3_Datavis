package data;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel for displaying activity data in a tabular format.
 * Allows sorting and alternating row colors for better readability.
 */
public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    /**
     * Constructs a TablePanel with a structured table layout.
     */
    public TablePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        String[] columnNames = {"ID", "Date", "Steps", "Active Min", "Sedentary Min", "Calories"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFillsViewportHeight(true);

        // Alternate row colors for better readability
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : Color.WHITE);
                return c;
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Updates the table with new activity data.
     *
     * @param activities List of activity records
     */
    public void updateTable(List<Activity> activities) {
        model.setRowCount(0);
        for (Activity activity : activities) {
            model.addRow(new Object[]{activity.getId(), activity.getDate(), activity.getTotalSteps(),
                    activity.getVeryActiveMinutes(), activity.getSedentaryMinutes(), activity.getCalories()});
        }
    }

    /**
     * Provides access to the JTable for selection handling.
     *
     * @return The JTable component
     */
    public JTable getTable() {
        return table;
    }
}
