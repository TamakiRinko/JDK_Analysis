package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(2);
    public static void main(String[] args) {
        // 自增
        atomicInteger.getAndIncrement();
    }
}
