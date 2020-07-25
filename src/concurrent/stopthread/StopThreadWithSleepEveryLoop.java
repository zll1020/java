package concurrent.stopthread;

/**
 * Description: 每次循环都会sleep，处理停止线程
 * 不需要使用!Thread.currentThread().isInterrupted() 判断线程是否已经被中断
 * User: zhangll
 * Date: 2020-07-25
 * Time: 17:33
 */
public class StopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = 0;
                try {
                    while (num <=Integer.MAX_VALUE/2){
                        if (num % 100 == 0){
                            System.out.println(num + "是100的倍数");
                        }
                        num++;
                        // 可以监听到线程被 Interrupt 了，抛出异常停止
                        Thread.sleep(10);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
