package concurrent.producerandconsumer;

/**
 * Description: 使用synchronized 实现 奇偶数0-100打印
 * User: zhangll
 * Date: 2020-07-24
 * Time: 16:14
 */
public class OldEvenSyn {

    private static int count = 0;

    private static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (lock){
                        if ((count & 1) == 1){
                            System.out.println(count);
                            count++;
                        }
                    }
                }
            }
        },"偶数").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100){
                    synchronized (lock){
                        if ((count & 1) == 0){
                            System.out.println(count);
                            count++;
                        }
                    }
                }
            }
        },"奇数").start();

    }

}
