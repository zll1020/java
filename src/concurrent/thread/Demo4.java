package concurrent.thread;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-22
 * Time: 14:11
 */
public class Demo4 {

    private static volatile boolean flag = true;

    /**
     * inner
     * inner
     * inner
     * outer
     * 例外线程同时执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException{

        new Thread( () -> {
            try {
                while (flag){
                    System.out.println("inner");
                    Thread.sleep(1000L);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000L);
        flag = false;
        System.out.println("outer");

    }
}
