package concurrent.juc.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:18
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");

        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            //iterator.remove(); //IllegalStateException
            arrayList.remove(iterator.next()); //ConcurrentModificationException
        }

    }

}
