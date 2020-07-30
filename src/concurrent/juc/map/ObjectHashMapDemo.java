package concurrent.juc.map;

import java.util.HashMap;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-29
 * Time: 10:47
 */
public class ObjectHashMapDemo {
    public static void main(String[] args) {
        HashMap<User, String> map = new HashMap<>();
        User user = new User("tony");
        map.put(user, "test");
        System.out.println(map.get(user)); // 1、 输出什么  test
        User user1 = new User("tony");
        map.put(user1, "tony");
        System.out.println(map.get(user)); // 1、 输出什么  重写了 equals 和 hashcode  tony，不重写 test
        System.out.println(map.get(user1));  // 2、 输出什么 tony
    }
}

class User {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((User)obj).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
