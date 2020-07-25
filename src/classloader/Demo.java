package classloader;

import java.io.File;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-24
 * Time: 10:38
 */
public class Demo {

    public static int demo(){
        return 1;
    }

    public static void main(String[] args) {
        File f = new File(Demo.class.getResource("").getPath());
        System.out.println(f);
    }
}
