package jvm;

import org.openjdk.jol.info.ClassLayout;

public class ObjectLayoutTest {
    public static void main(String[] args) {
        Object o = new Object();
        // 报错？
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//        String layout = ClassLayout.parseInstance(o).toPrintable();
//        System.out.println(layout);
    }
}
