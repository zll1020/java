package concurrent.sync;

/**
 * Description: 可重入
 * User: zhangll
 * Date: 2020-07-27
 * Time: 10:50
 */
public class ObjectSyncDemo2 {

    public synchronized void test(Object object){
        System.out.println("开始："+object);

        if (object == null){
            test(new Object());
        }

        System.out.println("结束："+object);
    }

    /**
     * 开始：null
     * 开始：java.lang.Object@61bbe9ba
     * 结束：java.lang.Object@61bbe9ba
     * 结束：null
     * @param args
     */
    public static void main(String[] args) {
        new ObjectSyncDemo2().test(null);
    }
}
