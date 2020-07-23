package designpattern.singleton;

/**
 * Description: 懒汉式（线程不安全）（不推荐）
 * User: zhangll
 * Date: 2020-07-22
 * Time: 18:20
 */
public class Singleton5 {

    private static Singleton5 instance;

    private Singleton5(){

    }

    public static Singleton5 getInstance(){
        if (instance == null){
            synchronized (Singleton5.class){
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
