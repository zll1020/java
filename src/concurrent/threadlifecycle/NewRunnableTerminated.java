package concurrent.threadlifecycle;

/**
 * Description: 展示线程的NEW、RUNNABLE、Terminated状态。即使是正在运行，也是Runnable状态，而不是Running。
 * User: zhangll
 * Date: 2020-07-25
 * Time: 11:04
 */
public class NewRunnableTerminated implements Runnable{

    /**
     * before start:NEW
     * after start:RUNNABLE
     * 0
     * 1
     * 2
     * 3
     * ...
     * 738
     * 739
     * sleep 10ms:RUNNABLE
     * 740
     * 741
     * 742
     * ...
     * 997
     * 998
     * 999
     * sleep 100ms:TERMINATED
     * @param args
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        System.out.println("before start:" + thread.getState());
        thread.start();
        System.out.println("after start:"+ thread.getState());

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep 10ms:"+thread.getState());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep 100ms:"+thread.getState());
    }


    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
