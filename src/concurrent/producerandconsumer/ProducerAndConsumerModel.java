package concurrent.producerandconsumer;

import java.util.Date;
import java.util.LinkedList;

/**
 * Description: 生产者消费者模式
 * User: zhangll
 * Date: 2020-07-24
 * Time: 14:53
 */
public class ProducerAndConsumerModel {

    /**
     * 怎么改都达不到重复的生产和消费，即使使用while（true），程序也会终止（在两个线程都经历一个wait之后）？
     * @param args
     */
    public static void main(String[] args) {
        Event event = new Event();
        new Thread(new Producer(event)).start();
        new Thread(new Consumer(event)).start();
    }

}

class Consumer implements Runnable {

    Event event;

    public Consumer(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
       while (true){
           event.consumer();
           try {
               Thread.sleep(500);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}

class Producer implements Runnable {

    Event event;

    public Producer(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        while (true){
            event.producer();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Event {
    int maxNum;
    private LinkedList<Date> storage;

    public Event() {
        this.maxNum = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void consumer() {
        while (storage.size() == 0) {
            try {
                storage.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者消费：" + storage.poll() + "，剩余：" + storage.size());
        notifyAll();
    }

    public synchronized void producer() {
        while (storage.size() == maxNum) {
            try {
                storage.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("生产者生产，剩余：" + storage.size());
        notifyAll();
    }
}
