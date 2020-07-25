package concurrent.stopthread;

/**
 * Description: run方法内有sleep或wait停止线程
 * User: zhangll
 * Date: 2020-07-25
 * Time: 16:46
 */
public class StopThreadWithSleep  {

    public static void main(String[] args) throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = 0;
                try {
                    while (!Thread.currentThread().isInterrupted() && num <=Integer.MAX_VALUE/2){
                        if (num % 100 == 0){
                            System.out.println(num + "是100的倍数");
                        }
                        num++;
                    }
                    // 可以监听到线程被 Interrupt 了，抛出异常停止
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

}
