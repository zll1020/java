package concurrent.createthread;

/**
 * Description: 同时使用 runnable 和 thread
 * User: zhangll
 * Date: 2020-07-24
 * Time: 11:16
 */
public class RunnableAndThreadStyle {

    public static void main(String[] args) {

        // 输出 thread，实际使用的是继承方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        }){
            @Override
            public void run(){
                System.out.println("thread");
            }
        }.start();
    }
}
