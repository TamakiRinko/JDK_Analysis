package spring.overview;

import java.lang.reflect.Field;
import java.util.stream.Stream;

public class AutowiredTest {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        // 获得域集
        Field[] declaredFields = clazz.getDeclaredFields();
        Stream.of(declaredFields).forEach((field)->{
            try {
                // 从field获得name
                field.setAccessible(true);
                Autowired_ autowired_ = field.getAnnotation(Autowired_.class);
                // 有Autowired_注解，需要注入
                if(autowired_ != null){
                    // 获得域类型
                    Class<?> type = field.getType();
                    // 构造对象并注入
                    Object o = type.getConstructor().newInstance();
                    field.set(userController, o);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println(userController.getUserService());
    }
}
