package concurrent.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: 自己实现futureTask -- park/unpark
 * User: zhangll
 * Date: 2020-07-29
 * Time: 14:06
 */
public class TonyFutureTask <T> implements Runnable {

    static LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue();

    Callable<T> callable ;

    T result;

    // task执行状态
    String state = "new";

    public TonyFutureTask(Callable<T> callable){
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            // 获取结果
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 改变状态
        state = "end";
        while (true){
            // 唤醒等待的线程 通知结果已经拿到
            Thread waiter = waiters.poll();
            // 没有等待的线程，跳出
            if (waiter == null){
                break;
            }
            // 唤醒
            LockSupport.unpark(waiter);
        }

    }

    public T getResult(){
        // 将当前线程放入等待队列
        Thread waiter = Thread.currentThread();
        waiters.add(waiter);
        System.out.println(Thread.currentThread().getName() + " 消费者进入等待");

        while (!state.equals("end")){
            // 结果还没有拿到，等待
            LockSupport.park(waiter);
        }
        return result;
    }
}
