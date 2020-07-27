package concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 两个线程，对 i 变量进行递增操作
 * User: zhangll
 * Date: 2020-07-27
 * Time: 11:13
 */
public class LockDemo {

    public volatile int i = 0;

    AtomicInteger ii = new AtomicInteger(0);

    Lock lock = new ReentrantLock();

    public void add(){
        // 10293
        // volatile 14754 volatile能保证可见性，不能保证原子性 i++实际有三个操作
        // i++;

        // 20000 原子类
        ii.incrementAndGet();
    }

    public void add1(){
        synchronized (this){
            i++;
        }
    }

    public void add2(){
        lock.lock();
        try {
            // TODO  很多业务操作
            i++;
        }finally {
            // 解锁一定会执行
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo ld = new LockDemo();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.ii);

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ld.add1();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);

        ld.i = 0;

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    ld.add2();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);
    }

}
