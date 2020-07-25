package concurrent.stopthread;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Description:陷入阻塞时，volatile是无法停止线程的
 * * 生产者生产很快，消费者消费很慢
 * * 队列满了之后，生产者阻塞，等待消费者消费
 * User: zhangll
 * Date: 2020-07-25
 * Time: 11:26
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException{
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        Producer producer = new Producer(blockingQueue);
        new Thread(new Producer(blockingQueue)).start();
        // 休眠一段时间，让生产者生产一会
        Thread.sleep(1000);
        Consumer consumer = new Consumer();
        while (consumer.random()){
            System.out.println(blockingQueue.take() + "被消费了");
            Thread.sleep(100);
        }

        System.out.println("消费者不需要数据了");
        producer.cancel = true;
    }


}

class Producer implements Runnable {

    public volatile boolean cancel = false;

    private BlockingQueue blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count = 0;
        // 阻塞了之后，cancel即使被更改，也获取不到，一直阻塞，不会结束
        while (!cancel && count < Integer.MAX_VALUE / 2) {
            if (count % 100 == 0) {
                System.out.println("100的整数倍：" + count);
                try {
                    blockingQueue.put(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count ++;
        }
    }
}

class Consumer{

    private boolean cancel = false;

    public boolean random(){
        if (Math.random() > 0.95){
            return false;
        }
        return true;
    }
}

