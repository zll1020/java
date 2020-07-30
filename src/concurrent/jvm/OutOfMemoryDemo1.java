package concurrent.jvm;

import java.util.ArrayList;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-30
 * Time: 09:46
 */
public class OutOfMemoryDemo1 {

    /**
     * // 资源占用过多或者资源未释放，内存溢出
     * // 网易碰到问题
     */
    ArrayList<Object> space = new ArrayList<Object>();

    public void demo1(String[] args) throws Exception {
        // 内存泄漏 最终会导致  内存溢出
        for (int i = 0; i < 1000; i++) {
            space.add(new byte[1024 * 1024 * 64]); // 64兆
            Thread.sleep(3000L);
        }
    }


    /**
     * // 1、 收到告警，保障可用性 （重启）
     *         // 2、 重启之前保留现场
     *
     *         // 由于参数校验不严谨 导致的问题
     *         // 问题功能描述：查询列表数据.总数据99w订单量
     *         // controller -- service  ---- rpc
     *         // 导致一次查询 10W+的数据
     *
     *         // 或者 请求过多（线程、资源)
     */

}
