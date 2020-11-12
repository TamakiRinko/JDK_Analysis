package reference;

import methodreference.Person;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FourReferenceTest {
    public static void main(String[] args) throws InterruptedException {
//        softReferenceTest();
        weakReferenceTest();
    }

    public static void softReferenceTest(){
        ReferenceQueue<Person> personReferenceQueue = new ReferenceQueue<>();
        Person person = new Person(1, "123");
        SoftReference<Person> personSoftReference = new SoftReference<>(person, personReferenceQueue);

        // person与personSoftReference都指向堆中对象
        // person指向null，personSoftReference依然指向原对象，且不会被GC回收(因为内存没有满)
        person = null;
//        System.out.println(person);
        System.out.println(personSoftReference.get());
        System.out.println(personReferenceQueue.poll());
    }

    public static void weakReferenceTest() throws InterruptedException {
        ReferenceQueue<Person> personReferenceQueue = new ReferenceQueue<>();
        Person person = new Person(1, "123");
        WeakReference<Person> personWeakReference = new WeakReference<>(person, personReferenceQueue);

        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            people.add(new Person(4, i + ""));
        }
        people.clear();

//        Person person1 = new Person(4, "2");
//        person1 = null;

//        person = null;
//        person = new Person(2, "222");

        // person与personSoftReference都指向堆中对象
        // person指向其他地方，personWeakReference依然指向原对象，但GC时一定会被回收
        // person不指向其他地方，GC时personWeakReference不一定会被回收！应该说是一般来讲不会被回收！
        System.gc();
        System.out.println(personWeakReference.get());
        System.out.println(personReferenceQueue.poll());
    }
}
