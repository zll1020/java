package concurrent.juc.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description: 并行调用http接口
 * User: zhangll
 * Date: 2020-07-29
 * Time: 14:52
 */
public class UserServiceForkJoin {

    ForkJoinPool forkJoinPool = new ForkJoinPool();

    public String getUserInfo(String userId) throws ExecutionException, InterruptedException {

        ArrayList<String> urls = new ArrayList<>();
        urls.add("user-info");
        urls.add("integral-info");

        InterfaceHandle handle = new InterfaceHandle(urls, 0, urls.size() - 1);
        ForkJoinTask<String> task = forkJoinPool.submit(handle);
        return task.get();
    }

    public static void main(String[] args) throws Exception {
        UserServiceForkJoin userServiceForkJoin = new UserServiceForkJoin();
        Long start = System.currentTimeMillis();
        System.out.println(userServiceForkJoin.getUserInfo("1"));
        System.out.println("消耗时间：" + (System.currentTimeMillis() - start));
    }

}

class InterfaceHandle extends RecursiveTask<String> {

    ArrayList<String> urls;
    int start;
    int end;

    InterfaceHandle(ArrayList<String> urls, int start, int end) {
        this.urls = urls;
        this.start = start;
        this.end = end;
    }

    @Override
    protected String compute() {
        int count = end - start;
        if (count == 0) {
            // 只有一个任务，直接提交执行
            String url = urls.get(start);
            // TODO 如果只有一个接口调用,立刻调用
            long userinfoTime = System.currentTimeMillis();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " 接口调用完毕" + (System.currentTimeMillis() - userinfoTime) + " #" + url);
            return url;
        } else {
            // 拆分
            int x = (start + end) / 2;
            InterfaceHandle handle = new InterfaceHandle(urls, 0, x);
            handle.fork();

            InterfaceHandle handle1 = new InterfaceHandle(urls, x + 1, end);
            handle1.fork();

            String result = null;
            result += handle.join();
            result += handle1.join();
            return result;
        }
    }
}
