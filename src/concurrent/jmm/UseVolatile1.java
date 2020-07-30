package concurrent.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: volatile适用的情况1 不涉及操作 直接赋值情况
 * User: zhangll
 * Date: 2020-07-30
 * Time: 09:38
 */
public class UseVolatile1 implements Runnable{

    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r =  new UseVolatile1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((UseVolatile1) r).done);
        System.out.println(((UseVolatile1) r).realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }
}
