package future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

public class CompletableFutureTest {
    public static CompletableFuture<Integer> compute() {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        return future;
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        test1();
        completeActionTest();
//        convertTest();
//        actionTest();
    }

    public static void test1() throws InterruptedException {
        CompletableFuture<Integer> f = compute();
        class Client extends Thread {
            final CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            @Override
            public void run() {
                try {
                    // 阻塞2s，然后两个线程都被唤醒
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        TimeUnit.SECONDS.sleep(2);
        // 在本线程中调用
        f.complete(100);
        //f.completeExceptionally(new Exception());
    }

    public static void createTest(){
        // 已经设定好result的CompletableFuture
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.completedFuture(5);

        // 四种静态方法：
        // public static CompletableFuture<Void> 	runAsync(Runnable runnable)
        // public static CompletableFuture<Void> 	runAsync(Runnable runnable, Executor executor)
        // public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier)
        // public static <U> CompletableFuture<U> 	supplyAsync(Supplier<U> supplier, Executor executor)

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "str");
    }

    //---------------------------------------------------------------
    private static Random rand = new Random();

    public static int getMoreData() {
        System.out.println("begin to start compute");
        long t = System.currentTimeMillis();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }

    public static void completeActionTest() throws ExecutionException, InterruptedException {
        // public CompletableFuture<T> 	whenComplete(BiConsumer<? super T,? super Throwable> action)
        // public CompletableFuture<T> 	whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
        // public CompletableFuture<T> 	whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
        // public CompletableFuture<T>  exceptionally(Function<Throwable,? extends T> fn)

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);
        CompletableFuture<Integer> f = future.whenCompleteAsync((Integer v, Throwable e) -> {
            System.out.println(v);
            System.out.println(e);
        });
        // f.get仍为阻塞调用
        System.out.println(f.get());
        System.out.println("in completeActionTest function");


        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
            // 抛出异常
            int i = 10/0;
            return 1024;
        });

        // 获取返回值，阻塞调用
        Integer result = future2.whenComplete((t, u) -> {
            System.out.println("t=>" + t);  // 正常的返回结果，错误时为null
            System.out.println("u=>" + u);  // 错误信息，正常时为null
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;                     // 可以获取到错误的返回结果
        }).get();
        System.out.println(result);


        // public <U> CompletableFuture<U> handle(BiFunction<? super T,Throwable,? extends U> fn)
        // public <U> CompletableFuture<U> handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
        // public <U> CompletableFuture<U> handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(CompletableFutureTest::getMoreData);
        CompletableFuture<String> handle = future3
                // 有异常，无视，也算完成计算，继续向下
                .handle((t, e) -> {int m = 5 / 0;return "123";})
                .handle((t, e)->"456");
        System.out.println(handle.get());
    }
    //---------------------------------------------------------------

    public static void convertTest() throws ExecutionException, InterruptedException {
        // public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
        // public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
        // public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<String> stringCompletableFuture = integerCompletableFuture.
                thenApply(i -> i * 10 ).
                thenApply(i -> i.toString() + "abc");
        System.out.println(stringCompletableFuture.get());
    }

    //---------------------------------------------------------------

    public static void actionTest() throws ExecutionException, InterruptedException {
        // accept: consumer接口，使用之前的result，无返回值
        // public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
        // public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
        // public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)

        // run: runnable接口，不使用之前的result，也无返回值
        // public CompletableFuture<Void> thenRun(Runnable action)
        // public CompletableFuture<Void> thenRunAsync(Runnable action)
        // public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor)

        // public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        // public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
        // public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
        // public     CompletableFuture<Void> runAfterBoth(CompletionStage<?> other,  Runnable action)
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 100);
        // x: 当前CompletableFuture的result；y: 第一个参数的CompletableFuture的result
        CompletableFuture<Void> f =  future.thenAcceptBoth(
                CompletableFuture.completedFuture(10),
                (x, y) -> System.out.println(x +" "+ y));
        System.out.println(f.get());        // 无返回值
    }

    //---------------------------------------------------------------
}
