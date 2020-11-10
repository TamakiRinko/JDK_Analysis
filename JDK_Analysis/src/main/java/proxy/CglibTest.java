package proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.objectweb.asm.ClassWriter;

import java.lang.reflect.Method;

public class CglibTest {

    public void hello(String name) {
        System.out.println("hello " + name);
    }

    public void hello2(String name) {
        System.out.println("hello2 " + name);
    }


    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibTest.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before execute");
                Object result = methodProxy.invokeSuper(o, objects);
                System.out.println("after execute");
                return result;
            }
        });
        CglibTest testCGLIBProxy = (CglibTest) enhancer.create();
        testCGLIBProxy.hello("Jerry");
        testCGLIBProxy.hello2("Jerry");

    }
}
