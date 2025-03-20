package data;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

/**
 * A JPanel component that visualizes activity data as a bar chart.
 * Displays daily steps with labeled bars.
 */
public class ChartPanel extends JPanel {
    private List<Activity> activities; // List of activity records to visualize

    /**
     * Constructs a ChartPanel with default size and appearance settings.
     */
    public ChartPanel() {
        setPreferredSize(new Dimension(500, 400));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }

    /**
     * Updates the chart with a new list of activity records and repaints the panel.
     *
     * @param newActivities List of Activity objects to be displayed
     */
    public void updateChart(List<Activity> newActivities) {
        this.activities = newActivities;
        repaint(); // Trigger panel redraw
    }

    /**
     * Paints the bar chart based on the activity data.
     *
     * @param g Graphics object used for drawing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (activities == null || activities.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("SansSerif", Font.BOLD, 14));
        g2.drawString("Daily Steps Chart", 200, 20);
        g2.drawString("Steps", 10, 180);

        int x = 50; // Initial x-coordinate for bars
        int maxSteps = activities.stream().mapToInt(Activity::getTotalSteps).max().orElse(1);
        int rangeSteps = Math.max(1, maxSteps);

        for (Activity activity : activities) {
            int barHeight = (int) ((double) activity.getTotalSteps() / rangeSteps * 300);
            g2.setColor(new Color(70, 130, 180)); // Steel Blue color
            g2.fillRect(x, 350 - barHeight, 40, barHeight);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, 350 - barHeight, 40, barHeight);

            // Rotate text for date labels to prevent overlap
            String date = activity.getDate().length() > 10 ? activity.getDate().substring(5) : activity.getDate();
            AffineTransform orig = g2.getTransform();
            g2.rotate(-Math.PI / 4, x + 15, 385);
            g2.drawString(date, x + 10, 385);
            g2.setTransform(orig);

            x += 50; // Move to next bar position
        }
    }
}
