package concurrent.threadlifecycle;

/**
 * Description: 展示Blocked, Waiting, TimedWaiting
 * User: zhangll
 * Date: 2020-07-25
 * Time: 11:12
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    /**
     * thread1:TIMED_WAITING
     * thread2:BLOCKED
     * thread1WAITING
     * thread2:TIMED_WAITING
     * @param args
     */
    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 期望一个是blocking，一个是timedWaiting(thread1先start，thread1是timedWaiting可能性大)
        System.out.println("thread1:" + thread1.getState());
        System.out.println("thread2:" + thread2.getState());

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程休眠1300ms,等待打thread sleep 1000ms结束，进入wait, 印出WAITING状态
        System.out.println("thread1:" + thread1.getState());
        // thread1 进入wait，释放锁，thread2开始执行，进入timedWaiting
        System.out.println("thread2:" + thread2.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
