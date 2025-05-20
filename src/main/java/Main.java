import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Create forks
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

        // Create JFrame for UI
        JFrame frame = new JFrame("Dining Philosophers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create PhilosopherPanels and Philosophers
        PhilosopherPanel p1Panel = new PhilosopherPanel("John Locke");
        PhilosopherPanel p2Panel = new PhilosopherPanel("Plato");
        PhilosopherPanel p3Panel = new PhilosopherPanel("Freud");
        PhilosopherPanel p4Panel = new PhilosopherPanel("Marx");
        PhilosopherPanel p5Panel = new PhilosopherPanel("Nietzsche");

        frame.add(p1Panel);
        frame.add(p2Panel);
        frame.add(p3Panel);
        frame.add(p4Panel);
        frame.add(p5Panel);

        // Create Philosophers and pass their panels
        Philosopher philosopher1 = new Philosopher("John Locke", fork1, fork2, p1Panel);
        Philosopher philosopher2 = new Philosopher("Plato", fork2, fork3, p2Panel);
        Philosopher philosopher3 = new Philosopher("Freud", fork3, fork4, p3Panel);
        Philosopher philosopher4 = new Philosopher("Marx", fork4, fork5, p4Panel);
        Philosopher philosopher5 = new Philosopher("Nietzsche", fork5, fork1, p5Panel);

        // Show frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}