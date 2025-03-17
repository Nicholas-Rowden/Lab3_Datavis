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

        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.drawString("Daily Steps", 50, 20);
        int x = 50;
        int maxSteps = activities.stream().mapToInt(Activity::getTotalSteps).max().orElse(1);

        for (Activity activity : activities) {
            int barHeight = (int) ((double) activity.getTotalSteps() / maxSteps * 350);
            g.setColor(Color.BLUE);
            g.fillRect(x, 350 - barHeight + 50, 40, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, 350 - barHeight + 50, 40, barHeight);
            g.drawString(activity.getDate(), x, 370);
            x += 50;
        }
    }
}
