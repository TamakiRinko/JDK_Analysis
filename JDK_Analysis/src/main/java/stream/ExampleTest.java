package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExampleTest {
    public static void main(String[] args) {
        exm1();
    }


    /**
     * 现在有5个用户！筛选：
     * 1、ID 必须是偶数
     * 2、年龄必须大于23岁
     * 3、用户名转为大写字母
     * 4、用户名字母倒着排序
     * 5、只输出一个用户！
     */
    public static void exm1(){
        User u1 = new User(1,"a",21);
        User u2 = new User(2,"b",22);
        User u3 = new User(3,"c",23);
        User u4 = new User(4,"d",24);
        User u5 = new User(6,"e",25);
        // 集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream().filter(u->u.getId()%2==0)
                .filter(u->u.getAge()>23)
                // .sorted(Comparator.comparing(a->a.getName()))             但是无法调用reversed()?
                // .sorted((a, b)-> b.getName().compareTo(a.getName()))

                // Comparator.comparing接收一个Function f，对流中每两个元素A，B应用f得到a，b
                // 对a，b调用a.compareTo(b)
                .sorted(Comparator.comparing(User::getName).reversed())
                .limit(1)
                // 非终结的forEach
                .peek(user -> user.setName(user.getName().toUpperCase()))
                .forEach(System.out::println);
    }

    public static List<Integer> sort(int[] sourceArray){
        // 对 arr 进行拷贝，不改变参数内容
        List<Integer> arr = Arrays.stream(sourceArray).boxed().collect(Collectors.toList());
        // Arrays.stream(sourceArray)返回的是IntStream，必须boxed返回stream
        Arrays.stream(sourceArray).boxed().collect(Collectors.toList());
        Stream.of(sourceArray).collect(Collectors.toList());
        return new ArrayList<>();
    }
}
