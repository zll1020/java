package concurrent.jvm;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-30
 * Time: 09:44
 */
public class FullGCDemo1 {
    /**
     * // 频繁调用system.gc导致fullgc次数过多
     * // 使用server模式运行 开启GC日志
     * // -Xmx512m -server -verbose:gc -XX:+PrintGCDetails -Xloggc:filepath -XX:+HeapDumpOnOutOfMemoryError
     *
     * // 很多人都会建议的规避System.gc带来的fullgc风险  -XX:+DisableExplicitGC 禁止程序显示调用gc方法
     */


}
