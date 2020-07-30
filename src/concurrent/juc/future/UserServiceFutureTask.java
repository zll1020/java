package concurrent.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 串行调用http接口
 * User: zhangll
 * Date: 2020-07-29
 * Time: 14:29
 */
public class UserServiceFutureTask {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public String getUserInfo(String userId) throws ExecutionException, InterruptedException {
        Callable<String> userInfoCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 1. 先从调用获取用户基础信息的http接口
                long userinfoTime = System.currentTimeMillis();
                Thread.sleep(500);
                System.out.println("userinfo-api用户基本信息接口调用时间为" + (System.currentTimeMillis() - userinfoTime));
                return "user-info";
            }
        };

        //executorService.submit(userInfoCallable);
        NeteaseFutureTask<String> userInfo = new NeteaseFutureTask<>(userInfoCallable);
        // new Thread(userInfo).start();
        executorService.submit(userInfo);

        NeteaseFutureTask<String> intergralInfoTask = new NeteaseFutureTask(() -> {
            // 2. 再调用获取用户积分信息的接口
            long integralApiTime = System.currentTimeMillis();
            Thread.sleep(1000);
            System.out.println("integral-api积分接口调用时间为" + (System.currentTimeMillis() - integralApiTime));
            return "user-integral";
        });
        //new Thread(intergralInfoTask).start();
        executorService.submit(intergralInfoTask);
        System.out.println(userInfo.get());
        System.out.println(intergralInfoTask.get());

        return (String) userInfo.get() + "////" + intergralInfoTask.get();
    }

    public static void main(String[] args) throws Exception {
        UserServiceFutureTask futureTask = new UserServiceFutureTask();
        Long start = System.currentTimeMillis();
        System.out.println(futureTask.getUserInfo("1"));
        // 1054
        System.out.println("消耗时间：" + (System.currentTimeMillis() - start));
    }

}
