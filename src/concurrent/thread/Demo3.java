package concurrent.thread;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-22
 * Time: 11:30
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000);
        //  thread.stop(); // 不推荐使用了
        thread.interrupt();
        // 真正 interrupt 在 run 方法执行后，不管加不加 synchronized
        while (thread.isAlive()) {
        }
        thread.print();
    }
}
