package concurrent.jvm;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-30
 * Time: 09:41
 */
public class Cpu100Demo1 {

    /**
     * // 处理慢导致的问题(多次jsack定位，比对)
     * // 递归或死循环计算导致的CPU占用率过高(jstack定位)
     */
    public static void main(String[] args) throws InterruptedException {
    }

    /**
     *  处理用户请求时，出现了死锁。用户无响应，多次重试，大量资源被占用（）
     */

    /**
     * 会导致程序永久等待的wait/notify
     */

    /**
     * 线程过多导致的问题(jstack定位)
     * 资源：每一个请求,业务执行需要占用多少资源，CPU * 1--> 增加资源。
     * 线程池，控制线程数量，升级更高的配置
     */

    /**
     * 个别线程占用资源过多
     */
}
