package concurrent.juc.future;

/**
 * Description: 串行调用http接口
 * User: zhangll
 * Date: 2020-07-29
 * Time: 14:26
 */
public class UserService {

    /**
     * 查询多个系统的数据，合并返回
     */
    public Object getUserInfo(String userId) throws InterruptedException {
        // 其他例子, 查数据库的多个表数据,分多次查询

        // 1. 先从调用获取用户基础信息的http接口
        long userinfoTime = System.currentTimeMillis();
        // 模拟接口调用耗时
        Thread.sleep(100);
        System.out.println("userinfo-api用户基本信息接口调用时间为" + (System.currentTimeMillis() - userinfoTime));

        // 2. 再调用获取用户积分信息的接口
        long integralApiTime = System.currentTimeMillis();
        Thread.sleep(200);

        System.out.println("integral-api积分接口调用时间为" + (System.currentTimeMillis() - integralApiTime));

        // 3、 在调用一个接口 +n秒

        // 3. 合并为一个json对象
        return new Object();
    }
}
