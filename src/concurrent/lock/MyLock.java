package concurrent.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: 自己实现锁
 * User: zhangll
 * Date: 2020-07-27
 * Time: 11:43
 */
public class MyLock implements Lock {

    /**
     * 当前锁的拥有者
     */
    volatile AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 等待队列
     */
    volatile LinkedBlockingQueue waiters = new LinkedBlockingQueue();


    @Override
    public boolean tryLock() {
        // 尝试将 当前线程拥有者 从 null修改为 当前线程
        return owner.compareAndSet(null,Thread.currentThread());
    }

    @Override
    public void lock() {
        // 是否需要加入等待队列
        boolean addWaiters = true;
        while (!tryLock()){
            // 获取锁失败，需要加入等待队列（从未加入过等待队列的情况）
            if (addWaiters){
                // 加入等待队列
                waiters.add(Thread.currentThread());
                // 已经加入过，以后仍失败的情况下，不需要重复加入
                addWaiters = false;
            }else {
                // 获取锁失败，被阻塞，等待唤醒
                LockSupport.park();
            }
        }
        // 获取成功，将当前线程从等待怒队列中移除
        // 如果第一次就获取成功，从来没加入过等待队列，是否会报错:不会，remove方法又返回值，删除成功返回true，失败，返回false
        waiters.remove(Thread.currentThread());

    }


    @Override
    public void unlock() {
        // 将 当前线程拥有者从 当前线程 修改为 null
        if (owner.compareAndSet(Thread.currentThread(),null)){
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()){
                // 获取等待队列 下一个等待的线程 进行唤醒
                Thread waiter = iterator.next();
                LockSupport.unpark(waiter);
            }
        }
    }

    public static void main(String[] args) {
        MyLock lock = new MyLock();
        int i = 0;
        lock.lock();
        i++;
        lock.unlock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
