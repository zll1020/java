package concurrent.juc.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 14:20
 */
public class NeteaseFutureTask<T> implements Runnable, Future {

    T result;
    String state = "new";
    Callable<T> callable;
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    public NeteaseFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        state = "end";

        Thread waiter = waiters.poll();
        while (waiter != null) {
            LockSupport.unpark(waiter);
            waiter = waiters.poll();
        }
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        if ("end".equals(state)){
            return result;
        }
        Thread waiter = Thread.currentThread();
        waiters.add(waiter);
        while (!"end".equals(state)){
            LockSupport.park(waiter);
        }
        return result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
