package concurrent.threadsecurity.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 用tryLock来避免死锁
 * User: zhangll
 * Date: 2020-07-29
 * Time: 09:58
 */
public class TryLockDeadlock implements Runnable {

    boolean flag = true;

    public TryLockDeadlock(boolean flag){
        this.flag = flag;
    }
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();


    /**
     线程1get lock1
     线程2get lock2
     线程2get lock1 fail
     线程1get lock2
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new TryLockDeadlock(true),"线程1").start();
        new Thread(new TryLockDeadlock(false),"线程2").start();
    }


    @Override
    public void run() {
        if (flag){
            try {
                if (lock1.tryLock(500, TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName() +"get lock1");
                    Thread.sleep(600);
                    if (lock2.tryLock(500,TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName() +"get lock2");
                        lock2.unlock();
                        lock1.unlock();
                    }else {
                        System.out.println(Thread.currentThread().getName() +"get lock2 fail");
                        lock1.unlock();
                    }
                }else {
                    System.out.println(Thread.currentThread().getName() +"get lock1 fail");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            try {
                if (lock2.tryLock(500, TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName() +"get lock2");
                    if (lock1.tryLock(500,TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName() +"get lock1");
                        lock1.unlock();
                        lock2.unlock();
                    }else {
                        System.out.println(Thread.currentThread().getName() +"get lock1 fail");
                        lock2.unlock();
                    }
                }else {
                    System.out.println(Thread.currentThread().getName() +"get lock2 fail");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
