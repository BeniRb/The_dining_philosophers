import javax.swing.*;
import java.awt.*;

public class PhilosopherPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel statusLabel;
    private JLabel eatingCountLabel;
    private JButton stopButton;

    public PhilosopherPanel(String name) {
        setLayout(new BorderLayout());
        nameLabel = new JLabel(name, SwingConstants.CENTER);
        statusLabel = new JLabel("Thinking", SwingConstants.CENTER);
        eatingCountLabel = new JLabel("Times eaten: 0", SwingConstants.CENTER);
        stopButton = new JButton("Stop");
        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.WHITE);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(statusLabel);
        centerPanel.add(eatingCountLabel);

        add(nameLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(stopButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(140, 100));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.CYAN);  // Initial state: thinking
    }

    public void setStatus(int state, int forkNumber) {
        SwingUtilities.invokeLater(() -> {
            switch (state) {
                case Philosopher.THINKING -> {
                    statusLabel.setText("Thinking");
                    setBackground(Color.CYAN);
                }
                case Philosopher.WAITING_FOR_FORK_1, Philosopher.WAITING_FOR_FORK_2 -> {
                    statusLabel.setText("Waiting for fork " + forkNumber);
                    setBackground(Color.ORANGE);
                }
                case Philosopher.EATING -> {
                    statusLabel.setText("Eating");
                    setBackground(Color.GREEN);
                }
                case Philosopher.STOPPED -> {
                    statusLabel.setText("Stopped");
                    setBackground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    public void setEatingCount(int count) {
        SwingUtilities.invokeLater(() -> eatingCountLabel.setText("Times eaten: " + count));
    }

    public JButton getStopButton() {
        return stopButton;
    }
}
