package concurrent.threadsecurity.deadlock;

/**
 * Description: 必定发生死锁的情况
 * User: zhangll
 * Date: 2020-07-29
 * Time: 09:46
 */
public class MustDeadLock implements Runnable {

    boolean flag = true;

    public MustDeadLock(boolean flag){
        this.flag = flag;
    }
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    public static void main(String[] args) {
        new Thread(new MustDeadLock(true)).start();
        new Thread(new MustDeadLock(false)).start();
    }

    @Override
    public void run() {
        if (this.flag){
            synchronized (lock1){
                try {
                    System.out.println(Thread.currentThread().getName() + "get lock1");
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + "get lock2");
                }
            }
        }else {
            synchronized (lock2){
                try {
                    System.out.println(Thread.currentThread().getName() + "get lock2");
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + "get lock1");
                }
            }
        }
    }
}
