import java.util.concurrent.Semaphore;

class Philosopher extends Thread {
    private int id;
    private Semaphore left_fork;
    private Semaphore right_fork;
    private Semaphore fork_access;

    public Philosopher(int id, Semaphore left_fork, Semaphore right_fork, Semaphore fork_access) {
      this.id = id;
      this.left_fork = left_fork;
      this.right_fork = right_fork;
      this.fork_access = fork_access;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println("Philosopher " + id + " thinking " + i + " time");

                if (!fork_access.tryAcquire()) {
                    System.out.println("Philosopher " + id + " couldn't get fork access, waiting");
                    continue;
                }

                left_fork.acquire();
                System.out.println("Philosopher " + id + " took left fork");
                right_fork.acquire();
                System.out.println("Philosopher " + id + " took right fork");

                System.out.println("Philosopher " + id + " eating " + i + " time");

                right_fork.release();
                System.out.println("Philosopher " + id + " put right fork");
                left_fork.release();
                System.out.println("Philosopher " + id + " put left fork");

                fork_access.release();
            } 
        
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}