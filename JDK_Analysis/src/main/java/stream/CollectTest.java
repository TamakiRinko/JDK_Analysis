package stream;

import methodreference.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectTest {
    public static void main(String[] args) {
        List<Person> list = Arrays.asList(
                new Person(1, "a"),
                new Person(2, "b"),
                new Person(3, "c"));

        String reduce = Stream.of("A", "is", "a", "dog").reduce("", String::concat);

        list.stream().filter(x->x.getNo() > 1)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        list.stream().filter(x->x.getNo() > 1)
                .collect(Collectors.toCollection(() -> new ArrayList<Person>()))
                .forEach(System.out::println);

        // 提供一个Supplier
        Supplier<ArrayList<Person>> newList = ArrayList::new;
        list.stream().filter(x->x.getNo() > 1)
                .collect(Collectors.toCollection(newList))
                .forEach(System.out::println);

        list.stream().filter(x->x.getNo() > 1)
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);

        // 整数加和
        System.out.println(Stream.of(1, 3, 4).filter(x -> x > 2)
                .collect(Collectors.reducing(0, Integer::sum)));

        System.out.println(Stream.of(1, 3, 4).filter(x -> x > 2)
                .collect(Collectors.counting()));

    }
}
