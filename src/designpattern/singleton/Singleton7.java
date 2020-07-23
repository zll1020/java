package designpattern.singleton;

/**
 * Description: 静态内部类方式，可用
 * User: zhangll
 * Date: 2020-07-23
 * Time: 14:40
 */
public class Singleton7 {

    private Singleton7(){

    }

    private static class SingletonInstance{
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
