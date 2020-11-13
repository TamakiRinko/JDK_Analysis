package cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CASTest {
    public static void main(String[] args) {
        atomicReferenceTest();
    }

    public static void casTest(){
        AtomicInteger atomicInteger = new AtomicInteger(3);
        atomicInteger.compareAndSet(3, 5);
        atomicInteger.incrementAndGet();
    }

    public static void atomicReferenceTest(){
        // 注意，如果泛型是一个包装类，注意对象的引用问题！-127~128之间才会复用，其他会构造新的对象！使用equals来比较包装类！
        // 正常在业务操作，这里面比较的都是一个个对象，没问题！
        AtomicStampedReference<Integer> atomicStampedReference =
                // 初始时间戳为1
                new AtomicStampedReference<>(1, 1);
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("a1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Lock lock = new ReentrantLock(true);
            // 需要提供期望版本号和更改版本号
            // 初始值 > 128 或 < -127，compareAndSet传入的参数会新创建Integer对象，他们的引用一定不同！调用一定失败
            System.out.println("a->" + atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a2=>"+atomicStampedReference.getStamp());


            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("a3=>"+atomicStampedReference.getStamp());

        },"a").start();


        // 乐观锁的原理相同！
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获得版本号
            System.out.println("b1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 期望的版本号为stamp，线程A修改回来，stamp不匹配，失败
            System.out.println("b->" + atomicStampedReference.compareAndSet(1, 6,
                    stamp, stamp + 1));

            System.out.println("b2=>"+atomicStampedReference.getStamp());

        },"b").start();
    }
}
