import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws Exception {
        Semaphore[] forks = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
        Semaphore fork_access = new Semaphore(4);

        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i + 1, forks[i], forks[(i + 1) % 5], fork_access);
            philosophers[i].start();
        }
    }
}
