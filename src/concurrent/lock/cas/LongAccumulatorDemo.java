package concurrent.lock.cas;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * Description: LongAdder增强版，处理累加之外，可以自行定义其他计算
 * User: zhangll
 * Date: 2020-07-28
 * Time: 14:20
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAccumulator accumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                // 返回最大值，这就是自定义的计算
                return left > right ? left : right;
            }
        }, 0);

        // 1000个线程
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            new Thread(() -> {
                accumulator.accumulate(finalI); // 此处实际就是执行上面定义的操作
            }).start();
        }

        Thread.sleep(2000L);
        System.out.println(accumulator.longValue()); // 打印出结果
    }

}
