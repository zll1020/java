package concurrent.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 可重入
 * User: zhangll
 * Date: 2020-07-27
 * Time: 15:08
 */
public class ReentrantDemo {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println("1");
            System.out.println("当前线程获取锁的次数："+lock.getHoldCount());
            lock.lock();
            System.out.println("2");
            System.out.println("当前线程获取锁的次数："+lock.getHoldCount());
        }finally {
            lock.unlock();
            lock.unlock();
        }

        // 如果不释放，此时其他线程是拿不到锁的
        new Thread(() -> {
            System.out.println(Thread.currentThread() + " 期望抢到锁");
            lock.lock();
            System.out.println(Thread.currentThread() + " 线程拿到了锁");
        }).start();
    }


}
