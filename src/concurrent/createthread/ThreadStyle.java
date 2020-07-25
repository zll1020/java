package concurrent.createthread;

/**
 * Description: 继承Thread方式 创建线程
 * User: zhangll
 * Date: 2020-07-24
 * Time: 11:01
 */
public class ThreadStyle extends Thread {

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run(){
        System.out.println("继承Thread创建线程");
    }
}
