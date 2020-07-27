package concurrent.lock.readwritelock;

/**
 * Description: 不用读写锁
 * User: zhangll
 * Date: 2020-07-27
 * Time: 15:53
 */
public class ReentrantReadWriteLockDemo1 {

    static ReentrantReadWriteLockDemo1 lock = new ReentrantReadWriteLockDemo1();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.read(Thread.currentThread());
        }).start();
        new Thread(() -> {
            lock.write(Thread.currentThread());
        }).start();

        new Thread(() -> {
            lock.read(Thread.currentThread());
        }).start();

    }

    /**
     * 读
     *
     * @param thread
     */
    public synchronized void read(Thread thread) {
        Long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在读");
        }
        System.out.println(thread.getName() + "读完");
    }

    /**
     * 写
     *
     * @param thread
     */
    public synchronized void write(Thread thread) {
        Long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在写");
        }
        System.out.println(thread.getName() + "写完");
    }

}
