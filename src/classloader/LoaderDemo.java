package classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Description: 指定class，加载
 * User: zhangll
 * Date: 2020-07-23
 * Time: 16:08
 */
public class LoaderDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.home"));

        System.out.println(File.separator);
        URL classUrl = new URL("/Users/zhangll/develop/project/java/out/production/project/classloader/Demo.class");//jvm 类放在位置

        // 测试双亲委派机制
        // 如果使用此加载器作为父加载器,则下面的热更新会失效,因为双亲委派机制,HelloService实际上是被这个类加载器加载的;
        // URLClassLoader parentLoader = new URLClassLoader(new URL[]{classUrl});
        URLClassLoader parentLoader = new URLClassLoader(new URL[]{classUrl});
        while (true) {
            // if(loader == null) break;
            // 创建一个新的类加载器
            URLClassLoader loader = new URLClassLoader(new URL[]{classUrl}, parentLoader);

            // 创建一个新的类加载器，它的父加载器为上面的parentLoader
            // URLClassLoader loader = new URLClassLoader(new URL[]{classUrl}, LoaderDemo.class.getClassLoader());

            // 问题：静态块触发
            Class clazz = loader.loadClass("Demo");
            System.out.println("Demo所使用的类加载器：" + clazz.getClassLoader());

            Object newInstance = clazz.newInstance();
            Object value = clazz.getMethod("demo").invoke(newInstance);
            System.out.println("调用demo获得的返回值为：" + value);

            Thread.sleep(3000L); // 1秒执行一次
            System.out.println();

            //  help gc  -verbose:class
            newInstance = null;
            loader = null;
        }


    }
}
