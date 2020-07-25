package concurrent.producerandconsumer;

/**
 * Description: 使用 wait notify 实现 奇偶数0-100打印
 * User: zhangll
 * Date: 2020-07-24
 * Time: 16:20
 */
public class OldEvenWaitNotify {

    private static int count = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        Producer producer = new Producer();
        // 一个奇数打印，一个偶数打印
        new Thread(producer).start();
        new Thread(producer).start();
    }

    static class Producer implements Runnable{

        @Override
        public void run() {
            while (count <= 100){
                synchronized (lock){
                    System.out.println(count);
                    count ++;
                    // java synchronized基于管程MESA模型实现，当A线程唤醒B线程，两个线程执行顺序：A执行完，B再执行
                    // 当B实际执行时，可能条件变成曾经以满足
                    lock.notify();
                    if (count <= 100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}


