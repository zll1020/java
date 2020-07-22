package concurrent.thread;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-22
 * Time: 15:56
 */
public class ThreadLocalDemo {

    public static ThreadLocal<String> value = new ThreadLocal<>();

    /**
     * @throws Exception
     */
    public void threadLocalTest() throws Exception {

        value.set("123");
        String v = value.get();
        System.out.println("before all main value = " + v);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String v = value.get();
                // null 拿不到main线程set操作 123
                System.out.println("inner run start and before set thread value = " + v);

                value.set("456");

                v = value.get();
                System.out.println("inner run after set thread value = " + v);
                System.out.println("finished");
            }
        }).start();

        // 等待 run 执行完
        Thread.sleep(5000L);

        v = value.get();
        System.out.println("after all main value = " + v);

    }

    /**
     * before all main value = 1233
     * inner run start and before set thread value = null
     * inner run after set thread value = 456
     * finished
     * after all main value = 1233
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new ThreadLocalDemo().threadLocalTest();
    }
}
