package concurrent.juc.countdownlatch;

import concurrent.sync.SynchronizedRecursion;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 调用http接口
 * User: zhangll
 * Date: 2020-07-29
 * Time: 14:29
 */
public class UserServiceCountLatch {
    ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 查询多个系统的数据，合并返回
     *
     * @param userId
     * @return
     * @throws InterruptedException
     */
    public String getUserInfo(String userId) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList<String> values = new ArrayList<>();

        executorService.submit(() -> {
            // 1. 先从调用获取用户基础信息的http接口
            long userinfoTime = System.currentTimeMillis();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("userinfo-api用户基本信息接口调用时间为" + (System.currentTimeMillis() - userinfoTime));
            values.add("user-info");
            countDownLatch.countDown();
        });
        executorService.submit(() -> {
            // 2. 再调用获取用户积分信息的接口
            long integralApiTime = System.currentTimeMillis();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("integral-api积分接口调用时间为" + (System.currentTimeMillis() - integralApiTime));
            values.add("user-integral");
            countDownLatch.countDown();
        });

        // 等待计数器归零
        countDownLatch.await();

        return values.toString();
    }

    public static void main(String[] args) throws Exception{
        UserServiceCountLatch demo = new UserServiceCountLatch();
        Long start = System.currentTimeMillis();
        System.out.println(demo.getUserInfo("1"));
        // 1053
        System.out.println("消耗时间：" + (System.currentTimeMillis() - start));
    }

}
