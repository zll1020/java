package concurrent.thread;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-22
 * Time: 14:19
 */
public class Demo5 {

    public static String content = "时间";

    public static void main(String[] args) {
        new Thread(() -> {
            while (true){
                try {
                    content = String.valueOf(System.currentTimeMillis());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true){
                try {
                    System.out.println(content);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
