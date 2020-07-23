package designpattern.singleton;

/**
 * Description: 双重检查（推荐面试使用）
 * User: zhangll
 * Date: 2020-07-23
 * Time: 10:25
 */
public class Singleton6 {

    private static Singleton6 instance;

    private Singleton6(){

    }

    public static Singleton6 getInstance(){
        if (instance == null){
            synchronized (Singleton6.class){
                if (instance == null){
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }

}
