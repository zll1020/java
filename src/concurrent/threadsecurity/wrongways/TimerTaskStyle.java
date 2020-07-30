package concurrent.threadsecurity.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description: 用定时器方式创建线程 ？ 有什么问题 ？
 * User: zhangll
 * Date: 2020-07-29
 * Time: 16:42
 */
public class TimerTaskStyle {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },1000,1000);
    }
}
