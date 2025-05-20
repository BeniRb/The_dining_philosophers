import java.util.Random;

public class Philosopher {
    public static final int THINKING = 1;
    public static final int WAITING_FOR_FORK_1 = 2;
    public static final int WAITING_FOR_FORK_2 = 3;
    public static final int EATING = 4;
    private PhilosopherPanel uiPanel;

    private String name;
    private int status;
    private int eatingCount;
    private Fork fork1;
    private Fork fork2;

    public Philosopher (String name, Fork fork1, Fork fork2,PhilosopherPanel Panel) {
        this.name = name;
        this.status = THINKING;
        this.eatingCount = 0;
        this.fork1 = fork1;
        this.fork2 = fork2;
        this.uiPanel = Panel;
        this.start();
    }


    private void start() {
        new Thread(() -> {
            Random random = new Random();
            while (true) {
                setStatus(THINKING, -1);
                System.out.println(this); // print philosopher status (thinking)
                Utils.sleep(random.nextInt(5000)); // Thinking time

                setStatus(WAITING_FOR_FORK_1, fork1.getNumber());
                System.out.println(this); // print waiting for fork 1
                synchronized (fork1) {
                    while (fork1.getHeldBy() != null) {
                        Utils.sleep(100);
                    }
                    fork1.setHeldBy(this);
                }
                System.out.println(name + " picked up fork " + fork1.getNumber());

                Utils.sleep(random.nextInt(2000)); // small delay before second fork

                setStatus(WAITING_FOR_FORK_2, fork2.getNumber());
                System.out.println(this); // print waiting for fork 2
                synchronized (fork2) {
                    while (fork2.getHeldBy() != null) {
                        Utils.sleep(100);
                    }
                    fork2.setHeldBy(this);
                }
                System.out.println(name + " picked up fork " + fork2.getNumber());

                setStatus(EATING, -1);
                System.out.println(this); // print eating
                Utils.sleep(random.nextInt(5000)); // Eating time

                fork1.setHeldBy(null);
                fork2.setHeldBy(null);
                eatingCount++;
                System.out.println(name + " finished eating. Total times eaten: " + eatingCount);
                setStatus(THINKING, -1);
                System.out.println(name + " starts thinking.");
            }
        }).start();
    }
    private void setStatus(int newStatus,int forkNumber) {
        this.status = newStatus;
        if (uiPanel != null) {
            uiPanel.setStatus(newStatus,forkNumber);
        }
    }

    public String getName () {
        return this.name;
    }

    public String toString () {
        String statusText = switch (this.status) {
            case THINKING ->  "thinking ";
            case WAITING_FOR_FORK_1 ->  "waiting for fork " + this.fork1.getNumber();
            case WAITING_FOR_FORK_2 -> "waiting for fork " + this.fork2.getNumber();
            case EATING -> "eating ";
            default -> "";
        };
        return "Philosopher " + this.name + " is currently " + statusText
                + " (total times he ate: " + this.eatingCount + ")";
    }
}