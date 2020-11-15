package designpattern;

public class TemplateMethodTest {
    public static void main(String[] args) {
        hookTest();
    }

    public static void hookTest(){
        new Child().m();
    }
}

interface func{
    void f1();
    void f2();
    void f3();
}

abstract class Father implements func{
    // final 不希望被重写
    final void m(){
        f1();
        f2();
        f3();
    }

    // 实现空方法，后面自类想重写哪个就重写哪个，这里不是任何模式，只是编程技巧
    @Override
    public void f1() {}
    @Override
    public void f2() {}

    // 没有实现f3，非抽象子类必须实现，这里是TemplateMethod模式
}

class Child extends Father{
    // 重写f2
    @Override
    public void f2() {
        System.out.println("Hi");
    }
    // 实现f3
    @Override
    public void f3() {
        System.out.println("Hello");
    }

}
