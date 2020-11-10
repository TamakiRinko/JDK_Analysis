package callback;

import java.util.concurrent.TimeUnit;

interface CallBack {
    /**
     * 一个需要回调的函数
     *
     * @param object 接收对象
     */
    void accept(Object object);
}

/**
 * 调用者，实现CallBack接口
 */
class Invoker implements CallBack{
    private final Handler handler;
    public Invoker(Handler handler){
        this.handler=handler;
    }
    public void invoke(final int num){
        // 异步调用
        new Thread(() -> handler.handlerData(this,num)).start();
    }
    @Override
    public void accept(Object object) {
        System.out.println("回调函数:" + object);
    }
}


class Handler {
    /**
     * @param callBack 回调接口，CallBack类型，只能调用accept方法
     * @param number 接收数据值
     */
    public void handlerData(CallBack callBack, final int number) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double v = Math.pow(number, 2);
        // 回调CallBack函数
        callBack.accept(v);
    }
}



public class CallBackTest {
    public static void main(String[] args) {
        Handler handler=new Handler();
        Invoker invoker=new Invoker(handler);
        invoker.invoke(4);
        System.out.println("Main线程执行");
    }
}
