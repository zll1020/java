package concurrent.stopthread;

/**
 * Description: run方法内没有sleep或wait停止线程
 * User: zhangll
 * Date: 2020-07-25
 * Time: 16:31
 */
public class StopThreadWithoutSleep implements Runnable{

    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(new StopThreadWithoutSleep());
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }

    @Override
    public void run() {

        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE/2){
            if (num % 10000 == 0){
                System.out.println("10000 的整数" + num);
            }
            num ++;
        }
        System.out.println("线程结束了");
    }
}
