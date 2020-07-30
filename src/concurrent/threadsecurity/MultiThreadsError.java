package concurrent.threadsecurity;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 运行结果出错
 * User: zhangll
 * Date: 2020-07-29
 * Time: 17:16
 */
public class MultiThreadsError implements Runnable {

    static  MultiThreadsError instance = new MultiThreadsError();
    int index = 0;
    final boolean[] marked = new boolean[10000000];

    static AtomicInteger real = new AtomicInteger();
    static AtomicInteger wrong = new AtomicInteger();

    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(instance.index);
        System.out.println("真正运行的次数：" + real.get());
        System.out.println("错误次数：" + wrong.get());
    }
    @Override
    public void run() {
        /*while (index < 10000){
            index++;
        }*/
        marked[0] = true;
        for (int i = 0 ;i<10000;i++){
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            real.incrementAndGet();
            synchronized (instance){
                if (marked[index] && marked[index-1]){
                    System.out.println("错误：" + index);
                    wrong.incrementAndGet();
                }
                marked[index] = true;
            }

        }
    }
}
