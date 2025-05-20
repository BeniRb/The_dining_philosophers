import javax.swing.*;
import java.awt.*;

public class PhilosopherPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel statusLabel;
    private JLabel eatingCountLabel;

    public PhilosopherPanel(String name) {
        setLayout(new BorderLayout());
        nameLabel = new JLabel(name, SwingConstants.CENTER);
        statusLabel = new JLabel("Thinking", SwingConstants.CENTER);
        eatingCountLabel = new JLabel("Times eaten: 0", SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.CENTER);
        add(eatingCountLabel, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(120, 80));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.CYAN);  // Initial state: thinking
    }

    public void setStatus(int state,int forkNumber) {
        SwingUtilities.invokeLater(() -> {
            switch (state) {
                case Philosopher.THINKING -> {
                    statusLabel.setText("Thinking");
                    setBackground(Color.CYAN);
                }
                case Philosopher.WAITING_FOR_FORK_1 -> {
                    statusLabel.setText("Waiting for fork " + forkNumber);
                    setBackground(Color.ORANGE);
                }
                case Philosopher.WAITING_FOR_FORK_2 -> {
                    statusLabel.setText("Waiting for fork " + forkNumber);
                    setBackground(Color.ORANGE);
                }
                case Philosopher.EATING -> {
                    statusLabel.setText("Eating");
                    setBackground(Color.GREEN);
                }
            }
        });
    }
    public void setEatingCount(int count) {
        SwingUtilities.invokeLater(() -> eatingCountLabel.setText("Times eaten: " + count));
    }
}
