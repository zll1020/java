package designpattern.singleton;

/**
 * Description: 懒汉式（线程安全）（不推荐）
 * User: zhangll
 * Date: 2020-07-22
 * Time: 18:18
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4(){

    }

    public static synchronized Singleton4 getInstance(){
        if (instance == null){
            instance = new Singleton4();
        }
        return instance;
    }

}
