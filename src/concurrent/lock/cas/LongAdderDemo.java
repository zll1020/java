package concurrent.lock.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Description: 测试用例： 同时运行2秒，检查谁的次数最多
 * User: zhangll
 * Date: 2020-07-27
 * Time: 17:59
 */
public class LongAdderDemo {

    private long count = 0;

    // 同步代码块的方式
    public void testSync() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - starttime < 2000) { // 运行两秒
                    synchronized (this) {
                        ++count;
                    }
                }
                long endtime = System.currentTimeMillis();
                System.out.println("SyncThread spend:" + (endtime - starttime) + "ms" + " v" + count);
            }).start();
        }
    }

    // Atomic方式
    private AtomicLong acount = new AtomicLong(0L);

    public void testAtomic() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - starttime < 2000) { // 运行两秒
                    acount.incrementAndGet(); // acount++;
                }
                long endtime = System.currentTimeMillis();
                System.out.println("AtomicThread spend:" + (endtime - starttime) + "ms" + " v-" + acount.incrementAndGet());
            }).start();
        }
    }

    // LongAdder 方式
    private LongAdder lacount = new LongAdder();
    public void testLongAdder() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                long starttime = System.currentTimeMillis();
                while (System.currentTimeMillis() - starttime < 2000) { // 运行两秒
                    lacount.increment();
                }
                long endtime = System.currentTimeMillis();
                System.out.println("LongAdderThread spend:" + (endtime - starttime) + "ms" + " v-" + lacount.sum());
            }).start();
        }
    }

    /**
     * SyncThread spend:2000ms v24216190
     * SyncThread spend:2000ms v24216190
     * SyncThread spend:2000ms v24216190
     * LongAdderThread spend:2000ms v-90847835
     * AtomicThread spend:2000ms v-64789538
     * AtomicThread spend:2000ms v-64789539
     * AtomicThread spend:2000ms v-64789537
     * LongAdderThread spend:2000ms v-90966415
     * LongAdderThread spend:2000ms v-91248525
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        LongAdderDemo demo = new LongAdderDemo();
        demo.testSync();
        demo.testAtomic();
        demo.testLongAdder();
    }

}
