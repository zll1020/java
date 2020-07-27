package concurrent.lock.readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-27
 * Time: 15:57
 */
public class ReentrantReadWriteLockDemo2 {

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLockDemo2 lock = new ReentrantReadWriteLockDemo2();

    public static void main(String[] args) {
        for (int i = 0;i<3;i++){
            new Thread(() -> {
                lock.read(Thread.currentThread());
            }).start();
        }

        new Thread(() -> {
            lock.write(Thread.currentThread());
        }).start();

    }

    /**
     * 读
     *
     * @param thread
     */
    public void read(Thread thread) {
        readWriteLock.readLock().lock();
        Long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在读");
        }
        System.out.println(thread.getName() + "读完");
        readWriteLock.readLock().unlock();
    }

    /**
     * 写
     *
     * @param thread
     */
    public void write(Thread thread) {
        readWriteLock.writeLock().lock();
        Long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在写");
        }
        System.out.println(thread.getName() + "写完");
        readWriteLock.writeLock().unlock();
    }

}
