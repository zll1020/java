package concurrent.juc.set;

import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:28
 */
public class ConcurrentSkipListSetDemo {

    public static void main(String[] args) {
        // 可设置比较方式
        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>(String::compareTo);
        skipListSet.add("aa");
        skipListSet.add("ca");
        skipListSet.add("aa");
        skipListSet.add("da");


        Iterator<String> iterator = skipListSet.iterator();
        while (iterator.hasNext()) {
            // iterator.remove(); //IllegalStateException
            String next = iterator.next();
            System.out.println(next);
            skipListSet.remove(next);
        }
    }

}
