package concurrent.sync;

/**
 * Description: 锁消除
 * User: zhangll
 * Date: 2020-07-27
 * Time: 10:59
 */
public class ObjectSyncDemo4 {

    public void test3(Object arg) {
        StringBuilder builder = new StringBuilder();
        builder.append("a");
        builder.append(arg);
        builder.append("c");
        System.out.println(arg.toString());
    }

    public void test2(Object arg) {
        String a = "a";
        String c = "c";
        System.out.println(a + arg + c);
    }


    public void test1(Object arg) {
        // jit 优化, 消除了锁
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a");
        stringBuffer.append(arg);
        stringBuffer.append("c");
        // System.out.println(stringBuffer.toString());
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            new ObjectSyncDemo4().test1("123");
        }
    }
}
