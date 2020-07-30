package concurrent.juc.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:23
 */
public class HashSetDemo {

    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet();

        hashSet.add("aa");
        hashSet.add("ca");
        hashSet.add("aa");
        hashSet.add("da");

        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()){
            // 同Array List 报错
            // iterator.remove();
            // hashSet.remove(iterator.next());
            System.out.println(iterator.next());
        }
    }


}
