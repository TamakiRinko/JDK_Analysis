package singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

enum EnumSingleton {

    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }

}

public class EnumTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.
                // 没有无参构造器！
                getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingleton instance2 = declaredConstructor.newInstance("INSTANCE", 0);

        // 若使用getDeclaredConstructor()获取无参构造器，则错误并非Constructor中的
        // throw new IllegalArgumentException("Cannot reflectively create enum objects");
        // 而是：NoSuchMethodException: com.kuang.single.EnumSingle.<init>()
        // 为何？肯定是没有无参构造器！
        // 反编译，idea和javap结果都失败，使用jad反编译可得存在String和int两个参数的构造器
        System.out.println(instance1);
        System.out.println(instance2);

    }
}
