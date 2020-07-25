package classloader;

/**
 * Description: 查看类的加载器实例
 * User: zhangll
 * Date: 2020-07-23
 * Time: 15:31
 */
public class ClassLoaderView {

    /**
     * 核心类库加载器null
     * 拓展类库加载器sun.misc.Launcher$ExtClassLoader@5e2de80c
     * 应用程序加载器sun.misc.Launcher$AppClassLoader@18b4aac2
     * 应用程序加载器的父类：sun.misc.Launcher$ExtClassLoader@5e2de80c
     * 拓展类库加载器null
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("核心类库加载器" +
                ClassLoaderView.class.getClassLoader().loadClass("java.lang.String").getClassLoader());
        System.out.println("拓展类库加载器" +
                ClassLoaderView.class.getClassLoader().loadClass("com.sun.nio.zipfs.ZipCoder").getClassLoader());
        System.out.println("应用程序加载器" +
                ClassLoaderView.class.getClassLoader());

        System.out.println("应用程序加载器的父类：" + ClassLoaderView.class.getClassLoader().getParent());
        System.out.println("拓展类库加载器" + ClassLoaderView.class.getClassLoader().getParent().getParent());
    }
}
