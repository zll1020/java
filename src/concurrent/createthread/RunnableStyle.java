package concurrent.createthread;

/**
 * Description: 实现Runnable接口创建线程
 * User: zhangll
 * Date: 2020-07-24
 * Time: 11:03
 */
public class RunnableStyle implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口创建线程");
    }

    public static void main(String[] args) {
        new Thread(new RunnableStyle()).start();
    }
}
