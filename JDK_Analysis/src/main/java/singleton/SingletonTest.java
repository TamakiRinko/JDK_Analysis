package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class SingletonTest {
    private static boolean flag = false;

    private SingletonTest(){
        // 先上锁
        synchronized (SingletonTest.class){
            // 用一个特定的flag，可以一定程度上防止反射
            if (!flag){
                flag = true;
            }else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
    }

    // volatile 防止指令重排
    private volatile static SingletonTest lazyMan;

    // 双重检测锁模式的 懒汉式单例  DCL懒汉式
    public static SingletonTest getInstance(){
        if (lazyMan==null){
            synchronized (SingletonTest.class){
                if (lazyMan==null){
                    lazyMan = new SingletonTest(); // 不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }

    // 反射！
    public static void main(String[] args) throws Exception {
//        LazyMan instance = LazyMan.getInstance();

        // 如果知道了这个flag，依然可以进行破解！
        Field flag = SingletonTest.class.getDeclaredField("flag");
        flag.setAccessible(true);


        Constructor<SingletonTest> declaredConstructor = SingletonTest.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        // 分析源码可知，newInstance不能作用于Enum对象！
        SingletonTest instance = declaredConstructor.newInstance();

        flag.set(instance,false);

        SingletonTest instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }
}
