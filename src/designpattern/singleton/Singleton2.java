package designpattern.singleton;

/**
 * Description: 饿汉式（静待代码块） 可用
 * User: zhangll
 * Date: 2020-07-22
 * Time: 18:14
 */
public class Singleton2 {

    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2(){

    }

    public static Singleton2 getInstance(){
        return INSTANCE;
    }
}
