package concurrent.threadsecurity.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 16:40
 */
public class ThreadPoolStyle {

    /**
     * 无界队列 容易造成溢出
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0 ;i<1000; i++){
            executorService.submit(new Task());
        }
    }
}

class Task implements Runnable{

    @Override
    public void run(){
        try {
            Thread.sleep(300);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
