package spring.overview;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class SetInjectionTest {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        // set注入时配置文件能够拿到注入对象的类
        UserService userService = new UserService();
        // 获得域集
        Field[] declaredFields = clazz.getDeclaredFields();
        Stream.of(declaredFields).forEach((field)->{
            try {
                // 从field获得name
                String name = field.getName();
                field.setAccessible(true);
                // 得到方法名
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                String methodName = "set" + name;
                // 调用set方法注入
                clazz.getMethod(methodName, UserService.class).invoke(userController, userService);
                System.out.println(userController.getUserService());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
