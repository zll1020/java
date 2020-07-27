package concurrent.waitandnotify;

/**
 * Description: wait 和 notify基本用法
 *  * 研究代码执行过程
 * User: zhangll
 * Date: 2020-07-27
 * Time: 09:58
 */
public class Wait {

    private static final Object lock = new Object();

    /**
     * wait 开始执行
     * notify 开始执行
     * notify 结束执行
     * wait 结束执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("wait 开始执行");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait 结束执行");
                }
            }
        }).start();

        Thread.sleep(500);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("notify 开始执行");
                    lock.notify();
                    System.out.println("notify 结束执行");
                }
            }
        }).start();

    }
}
