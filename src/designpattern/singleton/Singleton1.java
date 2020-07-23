package designpattern.singleton;

/**
 * Description: 饿汉式(静态常量) 可用
 * User: zhangll
 * Date: 2020-07-22
 * Time: 18:08
 */
public class Singleton1 {

    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
