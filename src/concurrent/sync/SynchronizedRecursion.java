package concurrent.sync;

/**
 * Description: 可重入
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:09
 */
public class SynchronizedRecursion {

    /**
     * null
     * 3
     * --------------------------
     * 1
     * 3
     * --------------------------
     * 1
     * t4
     * @param args
     */
    public static void main(String[] args) {
        SynchronizedRecursion synchronizedRecursion = new SynchronizedRecursion();
        synchronizedRecursion.t1(null);

        System.out.println("--------------------------");
        synchronizedRecursion.t2(1);

        System.out.println("--------------------------");

        synchronizedRecursion.t3(1);


    }

    /**
     * 同一个方法可重入
     *
     * @param a
     */
    public synchronized void t1(Object a) {
        System.out.println(a);
        // 必须有个不递归的条件，否则栈溢出
        if (a == null){
            t1(3);
        }
    }

    /**
     * 不同的方法可重入
     *
     * @param a
     */
    public synchronized void t2(int a) {
        System.out.println(a);
        t1(3);
    }

    /**
     * 不同类的方法可重入
     *
     * @param a
     */
    public synchronized void t3(int a) {
        System.out.println(a);
        SynchronizedRecursionCopy copy = new SynchronizedRecursionCopy();
        copy.t4();
    }


}
