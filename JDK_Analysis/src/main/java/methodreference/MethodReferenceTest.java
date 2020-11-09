package methodreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodReferenceTest {
    public static void main(String[] args) {
        // 必须是函数式接口！
        // 自动推断类型！
        PersonInterface getName = new Person()::getName;
        Supplier<Integer> getNo = new Person()::getNo;
        Consumer<Person> print = new Person()::print;
        // apply函数接收int，返回对应大小的Person[]
        Function <Integer, Person[]> function = Person[]::new;
        Person[] people = function.apply(4);
        System.out.println(people.length);

        // new提供一个function
        Function<Integer, ArrayList<String>> arrayListFunction = ArrayList::new;
        ArrayList<String> stringArrayList = arrayListFunction.apply(5);
        // new提供一个supplier
        Supplier<ArrayList<String>> arrayListSupplier = ArrayList::new;
        // new提供一个BiFunction，apply函数接收int和String，返回对应对象
        BiFunction<Integer, String, Person> aNew = Person::new;
        Person f = aNew.apply(1, "f");


    }
}
