package concurrent.sync;

/**
 * Description: synchronized 使用方式
 * 方法(静态/非静态),代码块(对象/类)
 * User: zhangll
 * Date: 2020-07-27
 * Time: 10:26
 */
public class ObjectSyncDemo1 {

    public void testSyncClass(){
        synchronized (ObjectSyncDemo1.class){
            System.out.println("开始");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束");
        }
    }

    public void testSyncObject(ObjectSyncDemo1 objectSyncDemo1){
        synchronized (objectSyncDemo1){
            System.out.println("开始");
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束");
        }
    }

    public static synchronized void testStaticSync(){
        System.out.println("开始");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }

    public synchronized void testSync(){
        System.out.println("开始");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }

    public static void main(String[] args) throws InterruptedException{
        new Thread(()-> {new ObjectSyncDemo1().testSyncClass();}).start();
        // 让线程1 先执行
        Thread.sleep(1000L);
        new Thread(()->{new ObjectSyncDemo1().testSyncClass();}).start();
    }
}
