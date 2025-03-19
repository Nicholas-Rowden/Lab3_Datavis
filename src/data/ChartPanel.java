package data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanel extends JPanel {
    private List<Activity> activities;

    public ChartPanel() {
        setPreferredSize(new Dimension(500, 400));
    }

    public void updateChart(List<Activity> activities) {
        this.activities = activities;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (activities == null || activities.isEmpty()) return;

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.drawString("Daily Steps Chart", 200, 20);
        g.drawString("Date", 250, 380);
        g.drawString("Steps", 10, 180);

        int x = 50;
        int maxSteps = activities.stream().mapToInt(Activity::getTotalSteps).max().orElse(1);
        int minSteps = activities.stream().mapToInt(Activity::getTotalSteps).min().orElse(0);
        int rangeSteps = Math.max(1, maxSteps - minSteps);

        for (Activity activity : activities) {
            int barHeight = (int) ((double) (activity.getTotalSteps() - minSteps) / rangeSteps * 300);
            g.setColor(Color.BLUE);
            g.fillRect(x, 350 - barHeight, 40, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, 350 - barHeight, 40, barHeight);
            g.drawString(activity.getDate(), x, 370);
            x += 50;
        }
    }
}
