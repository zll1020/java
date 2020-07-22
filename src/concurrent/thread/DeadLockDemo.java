package concurrent.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-22
 * Time: 14:31
 */
public class DeadLockDemo {

    public static Object baozidian = null;

    /**
     * suspend resume 挂起 恢复线程
     * @throws Exception
     */
    public void suspendResumeTest() throws Exception {
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) {
                System.out.println("if null inner");
                Thread.currentThread().suspend();
            }
            System.out.println("if finished");
        });
        consumerThread.start();

        Thread.sleep(3000L);
        baozidian = new Object();
        consumerThread.resume();
        System.out.println("finished");
    }

    public void suspendResumeDeadLockTest() throws Exception {
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) {
                System.out.println("if null inner");

                // suspend锁不释放
                synchronized (this) {
                    System.out.println("suspend");
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("if finished = synchronized 锁 释放");
        });
        consumerThread.start();
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            consumerThread.resume();
            System.out.println("resume");
        }
        System.out.println("finished");
    }


    public void suspendResumeDeadLockTest2() throws Exception {
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) {
                System.out.println("if null inner");
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("before suspend");
                Thread.currentThread().suspend();
                System.out.println("after suspend");
            }
            System.out.println("if finished");
        });
        consumerThread.start();
        Thread.sleep(3000L);
        baozidian = new Object();
        // 提前唤醒
        consumerThread.resume();
        System.out.println("resume");
        // 无作用
        // consumerThread.join();
    }

    /**
     * wait notify
     * @throws Exception
     */
    public void waitNotifyTest() throws Exception {
        new Thread(() -> {
            synchronized (this) {
                while (baozidian == null) {
                    try {
                        System.out.println("if null wait");
                        // wait会释放锁
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("outer synchronized ");
        }).start();
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("inner synchronized notify");
        }
        System.out.println("finished");
    }

    public void waitNotifyDeadLockTest() throws Exception {
        new Thread(() -> {
            if (baozidian == null) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("run inner synchronized before wait");
                        // 等不到唤醒
                        this.wait();
                        System.out.println("run inner synchronized before wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("run finished");
        }).start();
        Thread.sleep(3000L);
        baozidian = new Object();
        // 提前唤醒
        synchronized (this) {
            this.notifyAll();
            System.out.println("synchronized notify");
        }
        System.out.println("finished");
    }

    /**
     * park unpack
     * @throws Exception
     */
    public void parkUnparkTest() throws Exception {
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("if null before park");
                LockSupport.park();
            }
            System.out.println("run finished");
        });
        consumerThread.start();
        Thread.sleep(3000L);
        baozidian = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("finished");
    }

    public void parkUnparkDeadLockTest() throws Exception {
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) {
                System.out.println("if null before synchronized");
                // 先获取锁但是不释放
                synchronized (this) {
                    System.out.println("synchronized before park");
                    LockSupport.park();
                    System.out.println("synchronized after park");
                }
            }
            System.out.println("if finished");
        });
        consumerThread.start();
        Thread.sleep(3000L);
        baozidian = new Object();
        // 获取不到锁，一直执行不到
        synchronized (this) {
            System.out.println("synchronized before unpark");
            LockSupport.unpark(consumerThread);
        }
        System.out.println("finished");
    }

    /**
     * 假的死锁demo
     * 实际不会死锁，LockSupport.park() 即使在 unpark 之后执行也可以被唤醒
     * @throws Exception
     */
    public void parkUnparkFalseDeadLockTest() throws Exception {
        Thread consumerThread = new Thread(() -> {
            if (baozidian == null) {
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("if null before synchronized");
                // 后获取锁，后执行 得不到唤醒
                synchronized (this) {
                    System.out.println("synchronized before park");
                    LockSupport.park();
                    System.out.println("synchronized after park");
                }
            }
            System.out.println("run finished");
        });
        consumerThread.start();
        Thread.sleep(1000L);
        baozidian = new Object();
        // 先获取锁，先执行 = 提前唤醒
        synchronized (this) {
            System.out.println("synchronized before unpark");
            LockSupport.unpark(consumerThread);
            System.out.println("synchronized after unpark");
        }
        System.out.println("finished");
    }

    public static void main(String[] args) throws Exception {
        DeadLockDemo deadLockDemo = new DeadLockDemo();

        /**
         * if null inner 1
         * finished 2
         * if finished  3
         * 2 3 顺序可能会变
         */
        // deadLockDemo.suspendResumeTest();

        /**
         * if null inner
         * suspend
         */
        // deadLockDemo.suspendResumeDeadLockTest();

        /**
         * if null inner
         * resume
         * before suspend
         */
        // deadLockDemo.suspendResumeDeadLockTest2();

        /**
         * if null wait 1
         * inner synchronized notify 2
         * finished 3
         * outer synchronized  4
         * 3 4 顺序可能会变
         */
        // deadLockDemo.waitNotifyTest();

        /**
         * synchronized notify
         * finished
         * run inner synchronized before wait
         */
        // deadLockDemo.waitNotifyDeadLockTest();

        /**
         * if null before park
         * finished
         * run finished
         * 2 3 顺序不一定
         */
        // deadLockDemo.parkUnparkTest();

        /**
         * if null before synchronized
         * synchronized before park
         */
        // deadLockDemo.parkUnparkDeadLockTest();


        /**
         * synchronized before unpark
         * synchronized after unpark
         * finished
         * if null before synchronized
         * synchronized before park
         * synchronized after park
         * run finished
         */
        deadLockDemo.parkUnparkFalseDeadLockTest();
    }
}
