package collections;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.CopyOnWriteArrayList;

public class HashtableTest {
    public static void main(String[] args) {
        // this(11, 0.75f); 初始大小为11
        // 使用synchronized锁住方法
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(null, "123");

        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        ArrayList<Object> objects1 = new ArrayList<>();
    }
}
