package jvm;

public class ObjectCreateTest {
    volatile int i;
    public void m(){
        System.out.println("Hello JVM!");
    }
    public static void main(String[] args) {
        // 半初始化状态
        Object o = new Object();
    }
}
