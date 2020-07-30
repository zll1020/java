package concurrent.threadsecurity.deadlock;

/**
 * Description: 演示哲学家就餐问题导致的死锁
 * User: zhangll
 * Date: 2020-07-28
 * Time: 14:41
 */
public class DiningPhilosophers {


    static class Philosopher implements Runnable {

        private Object leftChopstick;

        private Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction(Thread.currentThread() + ":thinking");
                    synchronized (leftChopstick) {
                        doAction(Thread.currentThread() + ":tack left");
                        synchronized (rightChopstick) {
                            doAction(Thread.currentThread() + ":tack right");
                        }
                        doAction(Thread.currentThread() + ":put down right");
                    }
                    doAction(Thread.currentThread() + ":put down left");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " " + action);
            Thread.sleep((long) (Math.random() * 10));
        }
    }

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[philosophers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            // 一个圆圈
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
            if (i == philosophers.length - 1) {
                // 故意制造死锁
                // philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号").start();

        }
    }

}
