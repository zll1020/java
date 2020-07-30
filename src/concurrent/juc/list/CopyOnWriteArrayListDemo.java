package concurrent.juc.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:20
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            // iterator.remove();// UnsupportedOperationException
             arrayList.remove(iterator.next()); // 复制一个array进行操作
        }
    }
}
