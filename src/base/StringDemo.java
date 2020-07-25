package base;

import classloader.Demo;

/**
 * Description: String demo
 * User: zhangll
 * Date: 2020-07-23
 * Time: 15:01
 */
public class StringDemo {

    public static void internTest(){
        String s1 = "abc";
        String s2 = new String("abc");
        String s3 = new String("abc").intern();
        System.out.println(s1 == s2); // false
        System.out.println(s2 == s3); // false
        System.out.println(s1 == s3); // true
    }


    public static void main(String[] args) {
        internTest();
    }

}
