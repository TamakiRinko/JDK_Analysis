import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("123");
        list.add("23");
        list.forEach(System.out::println);
        Supplier<Integer> supplier = ()->new Random().nextInt();

        Function<String, Integer> function = String::length;

        Proxy.newProxyInstance(Main.class.getClassLoader(), new Class[]{}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }
}
