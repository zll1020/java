/**
 * Description:
 * User: zhangll
 * Date: 2020-07-27
 * Time: 21:29
 */
public class All {
    /**
     * 一 Java 基础部分14
     * 1、一个"java"源文件中是否可以包括多个类(不是内部类)?有什么限制?
     * 可以有多个类，但是只能有一个public，且类名与文件名相同
     *
     * 2、Java 有没有 goto?
     * 作为Java的保留字，目前没有使用
     *
     * 3、说说&和&&的区别。
     * 都可以做逻辑与运算，&&有短路效果，多个判断条件时，如果前面的不满足后面的不会执行，s==null && s.equals()不会发生空指针
     * & 还是位运算符
     *
     * 4、switch 语句能否作用在 byte 上，能否作用在 long 上，能否作用在 String 上?
     * switch 可以使用 int，byte，char,short否可以自动转换为 int，并且支持他们的包装类，Java 7 支持String 枚举
     *
     * 5、short s1 = 1; s1 = s1 + 1;有什么错? short s1 = 1; s1 += 1;有什么错?14
     * s1 = s1 + 1 +运算符会自动向上转型，变为int，int不能隐士转换为short，编译错误， +=不会
     *
     * 6、char 型变量中能不能存贮一个中文汉字?为什么?15
     * 可以，unicode，如果是特殊的unicode不包含的不能存储
     *
     * 7、用最有效率的方法算出 2 乘以 8 等於几?  15
     * 左移3位
     *
     * 8、使用 final 关键字修饰一个变量时，是引用不能变，还是引用的对象不能变?15
     * 引用不能变
     *
     * 9、"=="和 equals 方法究竟有什么区别?15
     * ==操作符专门用来比较两个变量的值是否相等，也就是用于比较变量所对应的内存中所存储的数值是否相同，
     * 要比较两个基本类型的数据或两个引用变量是否相 等，只能用==操作符。  15
     *
     * 10、静态变量和实例变量的区别?  16
     * 静态变量属于类，实例变量属于对象，静态变量随着类加载的时候会初始化一次，实例变量每个对象创建的时候，都会创建一个
     *
     * 11、是否可以从一个 static 方法内部发出对非 static 方法的调用?  17
     *
     *
     * 12、Integer 与 int 的区别  17
     * Integer 是 int的包装类
     * Integral是对象，封装了很多方法
     *
     * 13、Mathround(11.5)等於多少? Mathround(-11.5)等於多少?17
     * 11 -12
     *
     * 14、请说出作用域 public，private，protected，以及不写时的区别  17
     * private 其他类都不能访问
     * default 本包内的可以访问
     * protected 本包内的可以访问，子类可以访问
     * public 所有类都可以访问
     *
     * 15、Overload 和 Override 的区别。Overloaded 的方法是否可以改变返回值的类型?18
     * 16、构造器 Constructor 是否可被 override?19
     * 17、接口是否可继承接口? 抽象类是否可实现(implements)接口? 抽象类是否可继 承具体类(concrete class)? 抽象类中是否可以有静态的 main 方法?  19
     * 18、写 clone()方法时，通常都有一行代码，是什么?19
     * 19、面向对象的特征有哪些方面19
     * 20、java 中实现多态的机制是什么?  20
     * 21、abstract class 和 interface 有什么区别?20
     * 22、abstract 的 method 是否可同时是 static,是否可同时是 native，是否可同时是 synchronized? 21
     * 21、String 是最基本的数据类型吗?  21
     * 22、String s = "Hello";s = s + " world!";这两行代码执行后，原始的 String 对象中的 内容到底变了没有? 22
     * 23、是否可以继承 String 类?  22
     * 24、String s = new String("xyz");创建了几个 String Object? 二者之间有什么区别?22
     * 25、String 和 StringBuffer 的区别22
     * 26、数组有没有 length()这个方法? String 有没有 length()这个方法?  23
     * 27、下面这条语句一共创建了多少个对象:String s="a"+"b"+"c"+"d";  23
     * 28、try {}里有一个 return 语句，那么紧跟在这个 try 后的 finally {}里的 code 会不 会被执行，什么时候被执行，在 return 前还是后?23

     * 39、下面的程序代码输出的结果是多少?  24
     * 40、final, finally, finalize 的区别。  26
     * 41、运行时异常与一般异常有何异同?  26
     * 42、error 和 exception 有什么区别?26
     * 43、Java 中的异常处理机制的简单原理和应用。26
     * 44、请写出你最常见到的 5 个 runtime exception。27
     * 46、sleep() 和 wait() 有什么区别?27
     * 47、同步和异步有何异同，在什么情况下分别使用他们?举例说明。  27
     * 48、多线程有几种实现方法?同步有几种实现方法? 28
     * 49、启动一个线程是用 run()还是 start()? 28
     * 50、当一个线程进入一个对象的一个 synchronized 方法后，其它线程是否可进入此 对象的其它方法?  28
     * 51、线程的基本概念、线程的基本状态以及状态之间的关系  28
     * 52、简述synchronized和javautilconcurrentlocksLock的异同 ?29
     * 53、介绍 Collection 框架的结构  29
     * 54、Collection 框架中实现比较要实现什么接口  29
     * 55、ArrayList 和 Vector 的区别29
     * 56、HashMap 和 Hashtable 的区别  30
     * 57、List 和 Map 区别?30
     * 58、List, Set, Map 是否继承自 Collection 接口?30
     * 59、List、Map、Set 三个接口，存取元素时，各有什么特点?  30
     * 60、说出 ArrayList,Vector, LinkedList 的存储性能和特性31
     * 61、去掉一个 Vector 集合中重复的元素 31
     * 62、Collection 和 Collections 的区别。32
     * 63、Set 里的元素是不能重复的，那么用什么方法来区分重复与否呢? 是用==还是 equals()? 它们有何区别?32
     * 64、你所知道的集合类都有哪些?主要方法?  32
     * 65、两个对象值相同(xequals(y) == true)，但却可有不同的 hash code，这句话对不 对?  33
     * 65、TreeSet 里面放对象，如果同时放入了父类和子类的实例对象，那比较时使用 的是父类的 compareTo 方法，还是使用的子类的 compareTo 方法，还是抛异常!33
     * 66、说出一些常用的类，包，接口，请各举 5 个34
     * 67、java 中有几种类型的流?JDK 为每种类型的流提供了一些抽象类以供继承，请 说出他们分别是哪些类? 34
     * 68、字节流与字符流的区别35
     * 68、什么是 java 序列化，如何实现 java 序列化?或者请解释 Serializable 接口的作 用。 35
     * 69、描述一下 JVM 加载 class 文件的原理机制?  35
     * 70、heap 和 stack 有什么区别。  35
     * 71、GC是什么? 为什么要有GC?36
     * 72、垃圾回收的优点和原理。并考虑 2 种回收机制。36
     * 73、垃圾回收器的基本原理是什么?垃圾回收器可以马上回收内存吗?有什么办法 主动通知虚拟机进行垃圾回收?36
     * 74、java 中会存在内存泄漏吗，请简单描述。  36
     * 75、能不能自己写个类，也叫 javalangString?36
     * 二 算法与编程37
     * 1、编写一个程序，将 atxt 文件中的单词与 btxt 文件中的单词交替合并到 ctxt 文 件中，atxt 文件中的单词用回车符分隔，btxt 文件中用回车或空格进行分隔。  37
     * 2、编写一个程序，将 d:\java 目录下的所有java 文件复制到 d:\jad 目录下，并将原 来文件的扩展名从java 改为jad。  38
     * 3、编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取 的字符串，但要保证汉字不被截取半个，如“我 ABC”，4，应该截取“我 AB”， 输入“我 ABC 汉 DEF”，6，应该输出“我 ABC”，而不是“我 ABC+汉的半个”。  40
     * 4、有一个字符串，其中包含中文字符、英文字符和数字字符，请统计和打印出各 个字符的个数。 41
     * 5、说明生活中遇到的二叉树，用 java 实现二叉树  42
     * 6、从类似如下的文本文件中读取出所有的姓名，并打印出重复的姓名和重复的次 数，并按重复次数排序: 47
     * 7、写一个 Singleton 出来。  50
     * public class Singleton{
     *     private static Singleton instance;
     *     private Singleton(){
     *
     *     }
     *     public static Singleton getInstance(){
     *         if(instance == null){
     *             synchronized(Singleton.class){
     *                 if(instance = null){
     *                 instance = new Singleton();
     *                 }
     *             }
     *         }
     *         return instance;
     *     }
     * }
     *
     * 8、递归算法题 152
     * 9、递归算法题 253
     * 10、排序都有哪几种方法?请列举。用 JAVA 实现一个快速排序。54
     * 11、有数组 a[n]，用 java 代码将数组元素顺序颠倒55
     * 12金额转换，阿拉伯数字的金额转换成中国传统的形式如:(¥1011)->(一千 零一拾一元整)输出。56
     * 三 Java web 部分  57
     * 1、Tomcat 的优化经验57
     * 2、HTTP 请求的 GET 与 POST 方式的区别  57
     * 3、解释一下什么是 servlet;57
     * 4、说一说 Servlet 的生命周期?57
     * 5、Servlet 的基本架构  58
     * 6、SERVLET API中forward() 与redirect()的区别?
     * 转发和重定向
     * 转发：内部做转发，客户端不知道
     * 重定向：告知客户端新的地址，让客户端去请求
     *
     * 7、什么情况下调用 doGet()和 doPost()?58
     *
     * 8、Request 对象的主要方法:
     * 9、forward 和 redirect 的区别
     *
     * 10 jsp 有哪些内置对象?作用分别是什么? 分别有什么方法?
     * 11 jsp 有哪些动作?作用分别是什么?60
     * 12、两种跳转方式分别是什么?有什么区别?
     * 13、JSP 和 Servlet 有哪些相同点和不同点，他们之间的联系是什么?60
     * 14、MVC 的各个部分都有那些技术来实现?如何实现?  60
     * 15、我们在 web 应用开发过程中经常遇到输出某种编码的字符，如 iso8859-1 等， 如何输出一个某种编码的字符串?  61
     * 四 数据库部分61
     * 1、用两种方式根据部门号从高到低，工资从低到高列出每个员工的信息。  61
     * order by
     *
     * 2、列出各个部门中工资高于本部门的平均工资的员工数和部门号，并按部门号排 序 61
     * select 部门，count(*) group by(部门) having 工资 > arv(工资) order by 部门编号
     *
     * 3、存储过程与触发器必须讲，经常被面试到?  62
     * 4、数据库三范式是什么?  64
     * 5、说出一些数据库优化方面的经验?  65
     * 6、union 和 union all 有什么不同?66
     * 7分页语句 67
     * limit 0,20
     * 8用一条 SQL 语句 查询出每门课都大于 80 分的学生姓名  69
     *
     * 9所有部门之间的比赛组合70
     * 10每个月份的发生额都比 101 科目多的科目70
     * 11统计每年每月的信息  72
     * 12显示文章标题，发帖人、最后回复时间  73
     * 13删除除了 id 号不同,其他都相同的学生冗余信息73
     * 14航空网的几个航班查询题:  74
     * 15查出比经理薪水还高的员工信息:  75
     * 16、求出小于 45 岁的各个老师所带的大于 12 岁的学生人数  76
     * 17求出发帖最多的人: 77
     * 18、一个用户表中有一个积分字段，假如数据库中有 100 多万个用户，若要在每年 第一天凌晨将积分清零，你将考虑什么，你将想什么办法解决?  77
     * 19、一个用户具有多个角色，请查询出该表中具有该用户的所有角色的其他用户。  78
     * 20 xxx 公司的 sql 面试  78
     * 21、注册 Jdbc 驱动程序的三种方式79
     * 22、用 JDBC 如何调用存储过程79
     * 23、JDBC 中的 PreparedStatement 相比 Statement 的好处  80
     * 24 写一个用jdbc连接并访问oracle数据的程序代码80
     * 25、ClassforName 的作用?为什么要用? 80
     * 26、大数据量下的分页解决方法。  81
     * 27、用 JDBC 查询学生成绩单, 把主要代码写出来(考试概率极大)81
     * 28、这段代码有什么不足之处?  82
     * 29、说出数据连接池的工作机制是什么?  82
     * 30、为什么要用 ORM? 和 JDBC 有何不一样? 82
     * 五 XML 部分82
     * 1、xml 有哪些解析技术?区别是什么?
     * shecema
     *
     * 2、你在项目中用到了 xml 技术的哪些方面?如何实现的?  83
     * 3、用 jdom 解析 xml 文件时如何解决中文问题?如何解析?  83
     * 4、编程用 JAVA 解析 XML 的方式84
     * 5、XML 文档定义有几种形式?它们之间有何本质区别?解析 XML 文档有哪几种 方式? 86
     * 六 设计模式86
     * 1、UML 方面86
     * 2、j2ee 常用的设计模式?说明工厂模式。
     * 3、开发中都用到了那些设计模式?用在什么场合?
     * 策略模式
     * 七 J2EE 部分  87
     * 1、BS 与 CS 的联系与区别。  87
     * 2、应用服务器与 WEB SERVER 的区别?
     * 3、应用服务器有那些?
     * 4、J2EE 是什么?  88
     * 5、J2EE是技术还是平台还是框架? 什么是J2EE
     * 平台
     * 6、请对以下在 J2EE 中常用的名词进行解释(或简单描述)
     * 八、Mybatis
     * 1 谈谈 MyBatis
     * 2 Mybatis 的优点
     * 3 Mybatis 的缺点
     * 4 什么是 ORM
     * 5 为什么说Mybatis是半自动ORM映射工具?它与全自动的区别在哪里?
     *
     * 6 JDBC 编程有哪些不足之处，MyBatis 是如何解决这些问题的?
     * 7 Mybatis 的编程步骤是什么样的?
     * 8 Mybatis 中#和$的区别?
     * 9 使用 MyBatis 的 mapper 接口调用时有哪些要求?
     *
     * 10 Mybatis 中一级缓存与二级缓存?
     *
     * 11 MyBatis 在 insert 插入操作时返回主键 ID
     * 12 Xml 映射文件中，除了常见的 select|insert|updae|delete 标签之外，还有哪些标签?
     * if where foeach
     * 13 最佳实践中，通常一个 Xml 映射文件，都会写一个 Dao 接口与之对应，请问， 这个 Dao 接口的工作原理是什么?Dao 接口里的方法，参数不同时，方法能重载 吗?
     *
     * 14 简述 Mybatis 的 Xml 映射文件和 Mybatis 内部数据结构之间的映射关系?
     *
     * 15 Mybatis 的 Xml 映射文件中，不同的 Xml 映射文件，id 是否可以重复?
     *
     * 16 Mybatis 是如何进行分页的?分页插件的原理是什么?
     *
     * 17 简述 Mybatis 的插件运行原理，以及如何编写一个插件。
     *
     * 18 Mybatis 是如何将 sql 执行结果封装为目标对象并返回的?都有哪些映射形式?
     *
     * 19 Mybatis 动态 sql 是做什么的?都有哪些动态 sql?能简述一下动态 sql 的执行原 理不?
     *
     * 20 Mybatis 能执行一对一、一对多的关联查询吗?都有哪些实现方式，以及它们之 间的区别。
     *
     * 21 Mybatis 是否支持延迟加载?如果支持，它的实现原理是什么?
     *
     * 22 Mybatis 中如何执行批处理?Mybatis 都有哪些 Executor 执行器?它们之间的区 别是什么?
     *
     * 23 Mybatis 中如何指定使用哪一种 Executor 执行器?
     *
     * 24 Mybatis 是否可以映射 Enum 枚举类?
     *
     * 25 Mybatis 映射文件中，如果 A 标签通过 include 引用了 B 标签的内容，请问，B 标签能否定义在 A 标签的后面，还是说必须定义在 A 标签的前面?
     *
     * 26 Mybatis 框架适用场合
     *
     * 37 SpringMVC 和 Struts2 的区别?
     * 十一、Spring  109
     * 1 Spring
     * 2 Spring 好处:
     *
     * 3 Spring 能帮我们做什么?
     *
     * 4 Spring 结构
     *
     * 5 Spring 核心容器(应用上下文)模块
     *
     * 6 ApplicationContext 通常的实现是什么
     * AnnotationConfigApplicationContext
     *
     * 7 什么是Springbeans?
     *
     * 8 什么是Spring的内部bean?
     *
     * 9 你怎样定义类的作用域?
     *
     * 10 什么是bean的自动装配?
     *
     * 11 一个SpringBean定义包含什么?
     *
     * 12 一个SpringBeans的定义需要包含什么?
     *
     * 13 解释 Spring 支持的几种 bean 的作用域。
     *
     * 14 简单介绍一下Springbean的生命周期
     *
     * 15 哪些是重要的bean生命周期方法?你能重载它们吗?
     *
     * 16 BeanFactory 常用的实现类有哪些?
     *
     * 17 BeanFactory 与 AppliacationContext 有什么区别
     *
     * 18 Spring 框架中的单例 bean 是线程安全的吗?
     *
     * 20 XMLBeanFactory
     *
     * 21 如何给Spring容器提供配置元数据?
     *
     * 22 Spring 配置文件
     *
     * 23 什么是SpringIOC容器?
     *
     * 24 什么是Spring的依赖注入?
     *
     * 25 SpringIOC(控制反转):
     *
     * 26 IOC 的优点是什么?
     *
     * 27 有哪些不同类型的IOC(依赖注入)方式?
     * 构造器
     * setter
     * 接口
     *
     * 28 解释不同方式的自动装配。
     *
     * 29 在Spring中如何注入一个java集合?
     *
     * 30 哪种依赖注入方式你建议使用，构造器注入，还是Setter方法注入?
     * 构造器注入要求不能有循环依赖
     *
     * 31 Spring 中的设计模式
     *
     * 32 什么是基于注解的容器配置?
     *
     * 33 怎样开启注解装配?
     *
     * 34 Spring 的常用注解
     *
     * 35 解释对象/关系映射集成模块
     *
     * 36 简单解释一下spring的AOP
     *
     * 37 AOP 底层实现方式?
     *
     * 38 在SpringAOP中，关注点和横切关注的区别是什么?
     *
     * 39 什么是目标对象?
     *
     * 40 什么是切点?
     *
     * 41 什么是连接点?
     *
     * 42 什么是织入?什么是织入应用的不同点?
     *
     * 43 什么是代理?
     *
     * 44 Spring 的通知是什么?有哪几种类型?
     *
     * 45 解释JDBC抽象和DAO模块。
     *
     * 46 解释对象/关系映射集成模块。
     *
     * 47 Spring 支持的 ORM 框架有哪些?
     *
     * 48 请描述一下Spring的事务
     *
     * 49 Spring 事务隔离级别
     *
     * 50 Spring 怎么设置隔离级别?
     *
     * 51 使用 Spring 通过什么方式访问 Hibernate?
     *
     * 52 解释SpringJDBC、SpringDAO和SpringORM
     *
     * 53 在 Spring 框架中如何更有效地使用 JDBC?
     *
     * 54 解释 WEB 模块
     *
     * 55 一个Spring的应用看起来象什么?
     *
     * 十二、SpringMVC  123
     * 1 Spring MVC
     * 2 SpringMVC 流程
     * 3 SpringMVC 工作原理
     * 4 SpringMVC 优点
     * 5 SpringMVC 主要组件
     * 6 SpringMVC 和structs2区别
     * 7SpringMVC 如何设置重定向和转发
     * 8SpringMVC 拦截器
     * 9 SpringMVC 异常处理
     * 10SpringMVC 的核心入口类是什么?
     * Struts1,Struts2 的分别是什么?
     * 11SpringMVC 的控制器是不是单例模式，如果是，有什么问题，如何解决。125
     * 12SpringMVC 的控制器的注解一般用那个，有没有别的注解可以替代?125
     * 13 SpringMVC 的@RequestMapping 注解用在类上面有什么作用?126
     * 14 SpringMVC 如何把某个请求映射到特定的方法上面?126
     * 15 SpringMVC 如果想在拦截的方法里面得到从前台传入的参数，如何得到?126
     * 16SpringMVC 中的函数的返回值是什么?126
     * 17SpringMVC 用什么对象从后台向前台传递数据的?126
     * 18SpringMVC 中有个类把视图和数据合并在一起，叫什么?126
     * 19SpringMVC 中怎么把 ModelMap 里面的数据放入Session 里面?126
     * 20SpringMVC 如何在方法里面得到 Request 或者 Session?126
     * 21 SpringMVC 常用注解都有哪些?  126
     * 22 如何开启注解处理器和适配器?127
     * 23 SpringMvc 怎么和 AJAX 相互调用的? 127
     * 24 如何解决POST请求中文乱码问题，GET的又如何处理呢?127
     * 25 如果在拦截请求中,我想拦截get方式提交的方法,怎么配置?127
     * 26 如果前台有很多个参数传入,并且这些参数都是一个对象的,那么怎么样快速得 到这个对象? 128
     * 27 当一个方法向AJAX返回特殊对象,譬如Object,List等,需要做什么处理?128
     *
     * 十三、Springboot  128
     * 1 SpringBoot  128
     * 2 SpringBoot 工程的使用特点  128
     * 3SpringBoot2x 有什么新特性?与 1x 有什么区别?128
     * 4SpringBoot 默认启动方式是什么?还有什么启动方式?129
     * 5SpringBoot 的核心配置文件有几个?它们的区别是什么?129
     * 6Bootstrap 和 application 的区别?129
     * 7SpringBoot 的配置文件有哪几种格式?它们有什么区别?129
     * 8 什么是YAML?130
     * 9 如何在自定义端口上运行SpringBoot应用程序?130
     * 10SpringBoot 的核心注解是哪个?它主要由哪几个注解组成的?130
     * 11SpringBoot 有哪几种读取配置的方式?130
     * 12 开启 SpringBoot 特性有哪几种方式?130
     * 13SpringBoot 需要独立的容器运行吗?130
     * 14 运行 SpringBoot 有哪几种方式?131
     * 15SpringBoot 自动配置原理是什么?131
     * 16 你如何理解 SpringBoot 中的 Starters?131
     * 17 如何在 SpringBoot 启动的时候运行一些特定的代码?131
     * 18SpringBoot 支持哪些日志框架?推荐和默认的日志框架是哪个?131
     * 19SpringBoot 实现热部署有哪几种方式?131
     * 20 如何重新加载 Spring Boot 上的更改，而无需重新启动服务器? 131
     * 21 你如何理解 SpringBoot 配置加载顺序?132
     * 22SpringBoot 项目jar 包打成 war 包需要什么?132
     * 23SpringBoot 怎么定义不同环境配置?132
     * 24 springboot 中常用的 starter 的组件有哪些132
     * 25 Spring Boot 中的监视器是什么?  132
     * 26 如何在SpringBoot中禁用Actuator端点安全性?133
     * 27 如何实现 Spring Boot 应用程序的安全性? 133
     * 28 如何集成 Spring Boot 和 ActiveMQ?  133
     * 29 如何使用 Spring Boot 实现分页和排序? 133
     * 30 什么是Swagger?你用SpringBoot实现了它吗?133
     * 31 springboot 与 spring 的区别 133
     * 32 springboot 项目需要兼容老项目(spring 框架)，该如何实现  133
     *
     * * 十七、Redis  147
     *      * 1 Redis 的特点? 147
     *      * 2 为什么 redis 需要把所有数据放到内存中?147
     *
     *      * 3 Redis 常见的性能问题都有哪些?如何解决?148
     *
     *      * 4 Redis 最适合的场景有哪些? 148
     *
     *      * 5 Memcache 与 Redis 的区别都有哪些?  148
     *
     *      * 6 Redis 用过 RedisNX 吗?Redis 有哪几种数据结构?148
     *
     *      * 7 Redis 的优缺点 149
     *
     *      * 8 Redis 的持久化 149
     *
     *      * 9什么是 Redis?150
     *
     *      * 10Redis 的数据类型? 150
     *
     *      * 11使用 Redis 有哪些好处?150
     *
     *      * 12Redis 相比 Memcached 有哪些优势?  151
     *
     *      * 13Memcache 与 Redis 的区别都有哪些?  151
     *
     *      * 14Redis 是单进程单线程的? 151
     *
     *      * 15一个字符串类型的值能存储最大容量是多少?  151
     *
     *      * 16Redis 的持久化机制是什么?各自的优缺点?151
     *
     *      * 17Redis 常见性能问题和解决方案: 152
     *
     *      * 18redis 过期键的删除策略?  152
     *      * 19Redis 的回收策略(淘汰策略)?  152
     *      * 20为什么 redis 需要把所有数据放到内存中?  153
     *      * 21Redis 的同步机制了解么? 153
     *      * 22Pipeline 有什么好处，为什么要用 pipeline?153
     *      * 23是否使用过 Redis 集群，集群的原理是什么?153
     *      * 24Redis 集群方案什么情况下会导致整个集群不可用?153
     *      * 25Redis 支持的 Java 客户端都有哪些?官方推荐用哪个?153
     *      * 26Jedis 与 Redisson 对比有什么优缺点?  154
     *      * 27Redis 如何设置密码及验证密码? 154
     *      * 28说说 Redis 哈希槽的概念? 154
     *      * 29Redis 集群的主从复制模型是怎样的?154
     *      * 30Redis 集群会有写操作丢失吗?为什么?154
     *      * 31Redis 集群之间是如何复制的? 154
     *      * 32Redis 集群最大节点个数是多少? 154
     *      * 33Redis 集群如何选择数据库? 154
     *      * 34怎么测试 Redis 的连通性? 155
     *      * 35怎么理解 Redis 事务? 155
     *      * 36Redis key 的过期时间和永久有效分别怎么设置?  155
     *      * 37Redis 如何做内存优化? 155
     *      * 38Redis 回收进程如何工作的? 155
     *      * 39都有哪些办法可以降低 Redis 的内存使用情况呢?155
     *      * 40Redis 的内存用完了会发生什么? 155
     *      * 41一个 Redis 实例最多能存放多少的 keys?List、Set、Sorted Set 他们最多能存放 多少元素? 156
     *      * 42MySQL 里有 2000w 数据，redis 中只存 20w 的数据，如何保证 redis 中的数据都 是热点数据? 156
     *      * 43Redis 最适合的场景? 156
     *      * 44假如 Redis 里面有 1 亿个 key，其中有 10w 个 key 是以某个固定的已知的前缀开 头的，如果将它们全部找出来?157
     *      * 45如果有大量的 key 需要设置同一时间过期，一般需要注意什么?  157
     *      * 46使用过 Redis 做异步队列么，你是怎么用的?157
     *      * 47设置缓存值的过期时间?  157
     *
     * 十四、SpringCloud  134
     * 1 SpringCloud  134
     * 2 什么是微服务?134
     * 3 使用SpringCloud有什么优势?134
     * 4 SpringCloud 如何实现服务的注册和发现  134
     * 5 Ribbon 和 Feign 的区别135
     * 6 Spring Cloud 的特性  135
     * 7 什么是SpringCloudEureka?135
     * 8 什么是负载均衡?135
     * 9 什么是服务容错保护?什么是 Spring Cloud Hystrix?136
     * 10 什么是声明式服务调用?136
     * 11 什么是api服务网关?136
     * 12 什么是SpringCloudConfig?136
     * 13 什么是SpringCloudBus?137
     * 14 什么是 Spring Cloud Stream?  137
     * 15 Spring Cloud Stream 与 Spring Cloud Bus 区别? 137
     * 16 什么是SpringCloudSecurity?137
     * 17 SpringBoot 和 SpringCloud138
     * 18 SpringCloud 断路器的作用  138
     * 19 什么是服务熔断?什么是服务降级138
     * 20 微服务的优缺点分别是什么?138
     * 21 服务注册和发现是什么意思?Spring Cloud 如何实现? 139
     * 22 Spring Cloud 核心组件，在微服务架构中，分别扮演的角色: 139
     * 23 Eureka 和 ZooKeeper 都可以提供服务注册与发现的功能,请说说两个的区别?139
     * 24 你所知道的微服务技术栈有哪些?请列举一二 141
     * 十五、SpringSecurity  142
     * 1 Spring security 的简介  142
     * 2 框架原理142
     * 3 核心功能142
     * 4 框架的核心组件142
     * 5 spring security 实现方式  143
     * 6 spring security 控制权限的几种方法  143
     * 十六、Shiro 143
     * 1 简单介绍一下Shiro框架143
     * 2 Shiro 的优点143
     * 3 简述Shiro的核心组件144
     * 4 shiro 有哪些组件?  144
     * 5 Shiro 运行原理  144
     * 6 Shiro 认证过程  145
     * 7Authentication 和 Authorization145
     * 8 Shiro 工作流程  145
     * 9 Shiro 授权过程  145
     * 10 Shiro 如何自实现认证  146
     * 11 shiro 权限认证的三种方式  146
     * 12 如何实现自实现授权146
     * 13 如何配置在 Spring 中配置使用 Shiro147
     * 14 比较SpringSecurity 和 Shiro147

     */
}
