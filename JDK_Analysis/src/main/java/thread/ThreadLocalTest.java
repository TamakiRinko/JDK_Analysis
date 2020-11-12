package thread;

import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {
    // 初始化为initial
    static ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> "initial");
    public static void main(String[] args) {
//        simpleUseTest();
        testInheritableThreadLocal();
    }

    public static void simpleUseTest(){

        new Thread(()->{
            // 为当前线程单独分配了一个对象！
            stringThreadLocal.set("123");
            System.out.println(stringThreadLocal.get());
            // 使用后一定要remove，不然会内存泄漏
            stringThreadLocal.remove();
        }).start();
        new Thread(()->{
            // 为当前线程单独分配了一个对象！
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 返回initial
            System.out.println(stringThreadLocal.get());
            stringThreadLocal.remove();
        }).start();
    }

    private static void testInheritableThreadLocal() {
        final ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("hola");
        new Thread(() -> System.out.println("testInheritableThreadLocal = " + threadLocal.get())).start();
    }
}
