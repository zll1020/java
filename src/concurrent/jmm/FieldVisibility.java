package concurrent.jmm;

/**
 * Description: 演示可见性带来的问题
 * User: zhangll
 * Date: 2020-07-29
 * Time: 17:43
 */
public class FieldVisibility {

    volatile int a = 1;
    volatile int b = 2;

    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }

    /**
     * b=2;a=1 a=3还没有开始执行
     * b=3;a=3 a=3 b=a都执行完毕
     * b=2;a=3 a=3 执行完，b=a 还没执行
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            final FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }

    }

}
