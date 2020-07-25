package concurrent.stopthread;

/**
 * Description: volatile 设置boolean标记位 停止线程，看似可行
 * User: zhangll
 * Date: 2020-07-25
 * Time: 11:26
 */
public class WrongWayVolatile implements Runnable{


    private volatile static boolean cancel = false;


    public static void main(String[] args) {
        Thread thread = new Thread(new WrongWayVolatile());
        thread.start();
        try {
            // 线程执行到一半的时候，将cancle标志进行更改
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cancel = true;
    }

    @Override
    public void run() {
        int count = 0;
        while (!cancel && count < Integer.MAX_VALUE/2){
            if (count%100 == 0){
                System.out.println("100的整数:"+count);
            }
            count++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
