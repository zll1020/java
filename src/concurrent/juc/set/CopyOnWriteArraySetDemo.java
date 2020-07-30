package concurrent.juc.set;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:25
 */
public class CopyOnWriteArraySetDemo {

    public static void main(String[] args) {
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

        copyOnWriteArraySet.add("aa");
        copyOnWriteArraySet.add("ca");
        copyOnWriteArraySet.add("aa");
        copyOnWriteArraySet.add("da");

        Iterator<String> iterator = copyOnWriteArraySet.iterator();
        while (iterator.hasNext()){
            // iterator.remove();
            String next = iterator.next();
            System.out.println(next);
            copyOnWriteArraySet.remove(next);
        }
    }
}
