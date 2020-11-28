package collections;

import java.util.HashMap;
import java.util.Hashtable;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("1", "2");
        stringObjectHashMap.put("2", "3");
        stringObjectHashMap.put("3", "5");
        stringObjectHashMap.entrySet().forEach(System.out::println);

    }
}
