package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public interface LogService {
    public void Log();
}

interface WarnService {
    public void warn();
}

class Client implements LogService, WarnService {
    public void Log() {
        System.out.println("Log");
    }
    public void warn() {
        System.out.println("warn");
    }
}

class LogHandler implements InvocationHandler{
    Object target;  // 被代理的对象，实际的方法执行者

    public LogHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);  // 调用 target 的 method 方法
        after();
        return result;  // 返回方法的执行结果
    }
    // 调用invoke方法之前执行
    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }
    // 调用invoke方法之后执行
    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}

class WarnHandler implements InvocationHandler{
    Object target;  // 被代理的对象，实际的方法执行者

    public WarnHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);  // 调用 target 的 method 方法
        after();
        return result;  // 返回方法的执行结果
    }
    // 调用invoke方法之前执行
    private void before() {
        System.out.println("Warning start!");
    }
    // 调用invoke方法之后执行
    private void after() {
        System.out.println("Warning over!");
    }
}

class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        // Client实现两个接口
        Client client = new Client();
        ClassLoader classLoader = client.getClass().getClassLoader();
        Class[] interfaces = client.getClass().getInterfaces();
        // log接口
        InvocationHandler logHandler = new LogHandler(client);
        LogService logService = (LogService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        logService.Log();
        // Warn接口
        InvocationHandler warnHandler = new WarnHandler(client);
        WarnService warnService = (WarnService) Proxy.newProxyInstance(classLoader, interfaces, warnHandler);
        warnService.warn();

    }
}
