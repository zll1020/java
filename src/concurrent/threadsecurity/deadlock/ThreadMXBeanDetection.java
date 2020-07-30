package concurrent.threadsecurity.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Description: 用ThreadMXBean检测死锁
 * User: zhangll
 * Date: 2020-07-29
 * Time: 09:50
 */
public class ThreadMXBeanDetection implements Runnable{

    boolean flag = true;
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public ThreadMXBeanDetection(boolean flag){
        this.flag = flag;
    }

    public static void main(String[] args) throws InterruptedException{
        new Thread(new ThreadMXBeanDetection(true)).start();
        new Thread(new ThreadMXBeanDetection(false)).start();
        Thread.sleep(5);

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadThreads = threadMXBean.findDeadlockedThreads();
        if (deadThreads != null && deadThreads.length > 0) {
            for (int i = 0; i < deadThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadThreads[i]);
                System.out.println("发现死锁" + threadInfo.getThreadName());
            }
        }

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
