package concurrent.thread;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-21
 * Time: 15:10
 */
public class Demo2 {

    public static Thread thread1;
    public static Demo2 obj;

    public static void main(String[] args) throws InterruptedException{

        System.out.println("-------------------------------------------------");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("inner run method thread1 state ：" + Thread.currentThread().getState().toString());
                System.out.println("thread1 结束");
            }
        });
        System.out.println("before start thread1 state:" + thread1.getState().toString());
        thread1.start();
        // 重复start 会抛出异常  IllegalThreadStateException
        //thread1.start();
        System.out.println("after start thread1 state:" + thread1.getState().toString());

        Thread.sleep(2000L);
        System.out.println("thread1 sleep state :" + thread1.getState().toString());
        System.out.println("-------------------------------------------------");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("inner run method thread2 state ：" + Thread.currentThread().getState().toString());
                System.out.println("thread2 结束");
            }
        });
        System.out.println("before start thread2 state:" + thread2.getState().toString());
        thread2.start();
        System.out.println("after start thread2 state:" + thread2.getState().toString());
        Thread.sleep(200L);
        System.out.println("thread2 sleep state:" + thread2.getState().toString());
        Thread.sleep(3000L);
        System.out.println("thread2 sleep state:" + thread2.getState().toString());
        System.out.println("-------------------------------------------------");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Demo2.class) {
                    System.out.println("inner run method thread3 state:" + Thread.currentThread().getState().toString());
                    System.out.println("thread3 结束");
                }
            }
        });

        synchronized (Demo2.class) {
            System.out.println("before start inner synchronized thread3 state:" + thread3.getState().toString());
            thread3.start();
            System.out.println("after start inner synchronized thread3 state:" + thread3.getState().toString());
            Thread.sleep(200L);
            System.out.println("thread3 sleep inner synchronized state:" + thread3.getState().toString());
        }

        Thread.sleep(3000L);
        System.out.println("outer synchronized state:" + thread3.getState().toString());

    }
}
