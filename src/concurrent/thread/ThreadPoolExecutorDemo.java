package concurrent.thread;

import java.util.List;
import java.util.concurrent.*;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-22
 * Time: 16:01
 */
public class ThreadPoolExecutorDemo {

    /**
     * 通用调用线程池 ThreadPoolExecutor 提交任务
     * @param threadPoolExecutor
     * @throws Exception
     */
    public void testCommon(ThreadPoolExecutor threadPoolExecutor) throws Exception {
        // 提交 15 个任务
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("inner run before sleep n = " + n);
                        Thread.sleep(3000L);
                        System.err.println("inner run after sleep n = " + n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("thread submit i = " + i);
        }

        // 等待 任务 submit 一部分
        Thread.sleep(500L);
        System.out.println("线程池大小：" + threadPoolExecutor.getPoolSize());
        System.out.println("队列长度：" + threadPoolExecutor.getQueue().size());

        // 等待 任务继续 submit 一部分
        Thread.sleep(15000L);
        System.out.println("线程池大小：" + threadPoolExecutor.getPoolSize());
        System.out.println("队列长度：" + threadPoolExecutor.getQueue().size());
    }

    /**
     * 主线程数 5
     * 最大可创建线程数 10
     * 没有任务执行时 能够存活时间 5 SECONDS
     * 无界的阻塞队列（队列满时，入队需等待 无界队列没有此情况，队列空时，出队阻塞）
     * 默认拒绝策略
     * result
     * * thread submit i = 0
     *      * thread submit i = 1
     *      * inner run before sleep n = 0
     *      * inner run before sleep n = 1
     *      * thread submit i = 2
     *      * inner run before sleep n = 2
     *      * thread submit i = 3
     *      * inner run before sleep n = 3
     *      * thread submit i = 4
     *      * inner run before sleep n = 4
     *      * thread submit i = 5
     *      * thread submit i = 6
     *      * thread submit i = 7
     *      * thread submit i = 8
     *      * thread submit i = 9
     *      * thread submit i = 10
     *      * thread submit i = 11
     *      * thread submit i = 12
     *      * thread submit i = 13
     *      * thread submit i = 14
     *      * 线程池大小：5  //最大可创建线程数为10，但是没有创建？
     *      * 队列长度：10  // 15 个任务全部提交了，只有5个真正在执行，其余在任务队列里
     *      * inner run after sleep n = 2
     *      * inner run after sleep n = 0
     *      * inner run after sleep n = 1
     *      * inner run after sleep n = 3 // 等待更长时间后，前五个任务渐渐执行完，开始执行队列里面的任务
     *      inner run before sleep n = 5
     *      * inner run before sleep n = 6
     *      * inner run after sleep n = 4
     *      * inner run before sleep n = 8
     *      * inner run before sleep n = 7
     *      * inner run before sleep n = 9
     *      * inner run after sleep n = 6
     *      * inner run after sleep n = 9
     *      * inner run after sleep n = 5
     *      inner run before sleep n = 11
     *      * inner run before sleep n = 10
     *      inner run after sleep n = 8
     *      * inner run after sleep n = 7  // 正在执行中的任务 不会 超过 5个
     *      * inner run before sleep n = 12
     *      * inner run before sleep n = 13
     *      * inner run before sleep n = 14
     *      * inner run after sleep n = 10
     *      * inner run after sleep n = 14
     *      * inner run after sleep n = 12
     *      * inner run after sleep n = 13
     *      * inner run after sleep n = 11
     *      * 线程池大小：5
     *      * 队列长度：0 任务全部执行完
     * @throws Exception
     */
    private void threadPoolExecutorTest1() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        testCommon(threadPoolExecutor);
    }

    /**
     *      * 主线程数 5
     *      * 最大可创建线程数 10
     *      * 没有任务执行时 能够存活时间 5 SECONDS
     *      * 有界阻塞队列 长度 3
     *      * 拒绝策略 AbortPolicy
     *      result
     *      * thread submit i = 0
     *      * inner run before sleep n = 0
     *      * thread submit i = 1
     *      * inner run before sleep n = 1
     *      * thread submit i = 2
     *      * inner run before sleep n = 2
     *      * thread submit i = 3
     *      * inner run before sleep n = 3
     *      * thread submit i = 4
     *      * thread submit i = 5
     *      * thread submit i = 6
     *      * inner run before sleep n = 4
     *      * thread submit i = 7
     *      * thread submit i = 8
     *      * inner run before sleep n = 8
     *      * thread submit i = 9
     *      * inner run before sleep n = 9
     *      * thread submit i = 10
     *      * inner run before sleep n = 10
     *      * thread submit i = 11
     *      * inner run before sleep n = 11
     *      * thread submit i = 12
     *      * 任务超过任务队列长度，拒绝
     *      * inner run before sleep n = 12
     *      * thread submit i = 13
     *      * 任务超过任务队列长度，拒绝
     *      * thread submit i = 14
     *      * 线程池大小：10 //创建了最大线程池的数量，超过了之后拒绝入队
     *      * 队列长度：3 // 任务队列最大长度 3
     *      * inner run after sleep n = 4
     *      * inner run after sleep n = 2
     *      * inner run before sleep n = 5
     *      * inner run before sleep n = 6
     *      * inner run after sleep n = 1
     *      * inner run after sleep n = 9
     *      * inner run before sleep n = 7
     *      * inner run after sleep n = 0
     *      * inner run after sleep n = 8
     *      * inner run after sleep n = 3
     *      * inner run after sleep n = 11
     *      * inner run after sleep n = 12
     *      * inner run after sleep n = 10
     *      * inner run after sleep n = 5
     *      * inner run after sleep n = 6
     *      * inner run after sleep n = 7
     *      * 线程池大小：5 // 执行结束后 线程池线程数恢复为5个
     *      * 队列长度：0
     * @throws Exception
     */
    private void threadPoolExecutorTest2() throws Exception {
        // 拒绝策略 java.util.concurrent.ThreadPoolExecutor.AbortPolicy
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("任务超过任务队列长度，拒绝");
            }
        });
        testCommon(threadPoolExecutor);
    }

    /**
     *      * 主线程数 5
     *      * 最大可创建线程数 5
     *      * 没有任务执行时 立即销毁
     *      * 无界的阻塞队列
     *      * 默认拒绝策略
     *      result
     *      * thread submit i = 0
     *      * thread submit i = 1
     *      * inner run before sleep n = 0
     *      * inner run before sleep n = 1
     *      * thread submit i = 2
     *      * inner run before sleep n = 2
     *      * thread submit i = 3
     *      * inner run before sleep n = 3
     *      * thread submit i = 4
     *      * inner run before sleep n = 4
     *      * thread submit i = 5
     *      * thread submit i = 6
     *      * thread submit i = 7
     *      * thread submit i = 8
     *      * thread submit i = 9
     *      * thread submit i = 10
     *      * thread submit i = 11
     *      * thread submit i = 12
     *      * thread submit i = 13
     *      * thread submit i = 14
     *      * 线程池大小：5
     *      * 队列长度：10
     *      * inner run after sleep n = 3
     *      * inner run after sleep n = 1
     *      * inner run before sleep n = 5
     *      * inner run after sleep n = 2
     *      * inner run before sleep n = 6
     *      * inner run after sleep n = 4inner run before sleep n = 7
     *      *
     *      * inner run after sleep n = 0
     *      * inner run before sleep n = 8
     *      * inner run before sleep n = 9
     *      * inner run after sleep n = 6
     *      * inner run after sleep n = 5
     *      * inner run before sleep n = 10
     *      * inner run after sleep n = 7
     *      * inner run after sleep n = 9
     *      * inner run before sleep n = 11
     *      * inner run after sleep n = 8
     *      * inner run before sleep n = 12
     *      * inner run before sleep n = 13
     *      * inner run before sleep n = 14
     *      * inner run after sleep n = 10
     *      * inner run after sleep n = 14
     *      * inner run after sleep n = 13
     *      * inner run after sleep n = 12
     *      * inner run after sleep n = 11
     *      * 线程池大小：5
     *      * 队列长度：0
     * @throws Exception
     */
    private void threadPoolExecutorTest3() throws Exception {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        testCommon(threadPoolExecutor);
    }

    /**
     * SynchronousQueue 队列的 size = 0 有任务提交的时候，直接创建线程，使用完毕，销毁
     * result
     * * thread submit i = 0
     *      * inner run before sleep n = 0
     *      * thread submit i = 1
     *      * inner run before sleep n = 1
     *      * thread submit i = 2
     *      * inner run before sleep n = 2
     *      * thread submit i = 3
     *      * inner run before sleep n = 3
     *      * thread submit i = 4
     *      * inner run before sleep n = 4
     *      * thread submit i = 5
     *      * inner run before sleep n = 5
     *      * thread submit i = 6
     *      * thread submit i = 7
     *      * inner run before sleep n = 6
     *      * inner run before sleep n = 7
     *      * thread submit i = 8
     *      * inner run before sleep n = 8
     *      * thread submit i = 9
     *      * inner run before sleep n = 9
     *      * thread submit i = 10
     *      * inner run before sleep n = 10
     *      * thread submit i = 11
     *      * inner run before sleep n = 11
     *      * thread submit i = 12
     *      * inner run before sleep n = 12
     *      * thread submit i = 13
     *      * inner run before sleep n = 13
     *      * thread submit i = 14
     *      * inner run before sleep n = 14
     *      * 线程池大小：15
     *      * 队列长度：0
     *      * inner run after sleep n = 0
     *      * inner run after sleep n = 1
     *      * inner run after sleep n = 3
     *      * inner run after sleep n = 2
     *      * inner run after sleep n = 4
     *      * inner run after sleep n = 5
     *      * inner run after sleep n = 8
     *      * inner run after sleep n = 9
     *      * inner run after sleep n = 7
     *      * inner run after sleep n = 13
     *      * inner run after sleep n = 11
     *      * inner run after sleep n = 12
     *      * inner run after sleep n = 10
     *      * inner run after sleep n = 6
     *      * inner run after sleep n = 14
     *      * 线程池大小：15
     *      * 队列长度：0
     *      * 70秒后线程池线程数量0
     * @throws Exception
     */
    private void threadPoolExecutorTest4() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        testCommon(threadPoolExecutor);
        Thread.sleep(70000L);
        System.out.println("70秒后线程池线程数量" + threadPoolExecutor.getPoolSize());
    }

    /**
     * ScheduledThreadPoolExecutor 延迟执行任务
     * result
     * * after submit 当前时间 ：1595407334371,线程池数量：1
     *
     *      * inner run 当前时间：1595407337375
     * @throws Exception
     */
    private void threadPoolExecutorTest5() throws Exception {
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        threadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("inner run 当前时间：" + System.currentTimeMillis());
            }
        }, 3000, TimeUnit.MILLISECONDS); // 延迟3000ms
        System.out.println(
                "after submit 当前时间 ："+ System.currentTimeMillis() + ",线程池数量：" + threadPoolExecutor.getPoolSize());
    }

    /**
     * ScheduledThreadPoolExecutor 周期性执行任务
     * result
     * scheduleWithFixedDelay inner run 当前时间：1595410482418
     * scheduleAtFixedRate inner run 当前时间：1595410482418
     * scheduleAtFixedRate inner run 当前时间：1595410485420
     * scheduleWithFixedDelay inner run 当前时间：1595410486420
     * scheduleAtFixedRate inner run 当前时间：1595410488420
     * scheduleWithFixedDelay inner run 当前时间：1595410490424
     * scheduleAtFixedRate inner run 当前时间：1595410491426
     * scheduleWithFixedDelay inner run 当前时间：1595410494426
     * scheduleAtFixedRate inner run 当前时间：1595410494426
     * @throws Exception
     */
    private void threadPoolExecutorTest6() throws Exception {
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        // 执行完上一个，如果下一个时间到了或者已经过了，会立即执行
        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("scheduleAtFixedRate inner run 当前时间：" + System.currentTimeMillis());
            }
        }, 2000, 1000, TimeUnit.MILLISECONDS);

        // 上一个执行完，即使下一个执行时间已经过了，还是需要等待一定的时间
        threadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("scheduleWithFixedDelay inner run 当前时间：" + System.currentTimeMillis());
            }
        }, 2000, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * result
     thread submit i = 0
     inner run before sleep n = 0
     thread submit i = 1
     inner run before sleep n = 1
     thread submit i = 2
     inner run before sleep n = 2
     thread submit i = 3
     inner run before sleep n = 3
     thread submit i = 4
     inner run before sleep n = 4
     thread submit i = 5
     thread submit i = 6
     thread submit i = 7
     thread submit i = 8
     inner run before sleep n = 8
     thread submit i = 9
     inner run before sleep n = 9
     thread submit i = 10
     inner run before sleep n = 10
     thread submit i = 11
     inner run before sleep n = 11
     thread submit i = 12
     拒绝 // 设置了有界队列  可以提交 执行中任务 10 + 有界队列 3
     inner run before sleep n = 12
     thread submit i = 13
     拒绝
     thread submit i = 14
     before shutdown
     after shutdown
     拒绝 // shutdown之后 拒绝新的submit
     inner run after sleep n = 0
     inner run after sleep n = 2
     inner run after sleep n = 3
     inner run after sleep n = 1
     inner run after sleep n = 4
     inner run before sleep n = 5

     inner run after sleep n = 9
     inner run after sleep n = 10
     inner run after sleep n = 8
     inner run after sleep n = 12
     inner run after sleep n = 11
     inner run before sleep n = 6

     inner run before sleep n = 7
     inner run after sleep n = 5
     inner run after sleep n = 6
     inner run after sleep n = 7
     * @throws Exception
     */
    private void threadPoolExecutorTest7() throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("拒绝");
            }
        });
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("inner run before sleep n = " + n);
                        Thread.sleep(3000L);
                        System.err.println("inner run after sleep n = " + n);
                    } catch (InterruptedException e) {
                        System.out.println("异常" + e.getMessage());
                    }
                }
            });
            System.out.println("thread submit i = " + i);
        }
        // 等待提交部分任务
        Thread.sleep(1000L);
        // 线程池关闭 shutdown 会 等待队列里所有等待的线程执行完，拒绝新的submit 任务
        System.out.println("before shutdown");
        threadPoolExecutor.shutdown();
        System.out.println("after shutdown");
        // 关闭后提交任务，不会执行
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("after shutdown then submit");
            }
        });
    }

    /**
     * result
     * thread submit i = 0
     * inner run before sleep n =0
     * thread submit i = 1
     * inner run before sleep n =1
     * thread submit i = 2
     * inner run before sleep n =2
     * thread submit i = 3
     * inner run before sleep n =3
     * thread submit i = 4
     * thread submit i = 5
     * inner run before sleep n =4
     * thread submit i = 6
     * thread submit i = 7
     * thread submit i = 8
     * inner run before sleep n =8
     * thread submit i = 9
     * inner run before sleep n =9
     * thread submit i = 10
     * inner run before sleep n =10
     * thread submit i = 11
     * inner run before sleep n =11
     * thread submit i = 12
     * inner run before sleep n =12
     * 拒绝
     * thread submit i = 13
     * 拒绝
     * thread submit i = 14
     * before shutdownnow
     * n = 11异常：sleep interrupted
     * n = 12异常：sleep interrupted
     * n = 10异常：sleep interrupted
     * n = 9异常：sleep interrupted
     * n = 0异常：sleep interrupted
     * after shutdownnow
     * n = 3异常：sleep interrupted
     * n = 4异常：sleep interrupted
     * n = 1异常：sleep interrupted
     * n = 8异常：sleep interrupted
     * n = 2异常：sleep interrupted
     * 拒绝
     * shutdownNow 返回关闭了多少任务：3
     * @throws Exception
     */
    private void threadPoolExecutorTest8() throws Exception {
        // 拒绝策略java.util.concurrent.ThreadPoolExecutor.AbortPolicy
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.err.println("拒绝");
            }
        });
        for (int i = 0; i < 15; i++) {
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("inner run before sleep n =" + n);
                        Thread.sleep(3000L);
                        System.err.println("ִinner run after sleep n =:" + n);
                    } catch (InterruptedException e) {
                        System.out.println("n = "+ n +"异常：" + e.getMessage());
                    }
                }
            });
            System.out.println("thread submit i = " + i);
        }
        // 等待部分任务提交
        Thread.sleep(1000L);
        // shutdownnow会 interrupt 所有正在执行的任务，关闭还在等待的任务，拒绝新的submit 任务
        System.out.println("before shutdownnow");
        List<Runnable> shutdownNow = threadPoolExecutor.shutdownNow();
        System.out.println("after shutdownnow");
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("after shutdownNow then submit");
            }
        });
        System.out.println("shutdownNow 返回关闭了多少任务：" + shutdownNow.size());
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutorDemo executorDemo = new ThreadPoolExecutorDemo();
        // executorDemo.threadPoolExecutorTest1();
        // executorDemo.threadPoolExecutorTest2();
        // executorDemo.threadPoolExecutorTest3();
        // executorDemo.threadPoolExecutorTest4();
        // executorDemo.threadPoolExecutorTest5();
        // executorDemo.threadPoolExecutorTest7();
         executorDemo.threadPoolExecutorTest8();
        // 有周期的执行，不会停止
        // executorDemo.threadPoolExecutorTest6();
    }
}
