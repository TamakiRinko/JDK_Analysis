package future;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FutureTaskTest {
    public static void main(String[] args){
        System.out.println(computeTime(new FutureTaskTest()::task));
    }

    // 操作
    public Integer task(){
        //input2生成， 需要耗费3秒
        FutureTask<Integer> input2_futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 5;
        });
        //input1生成，需要耗费2秒
        FutureTask<Integer> input1_futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 3;
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(input1_futureTask);
        executorService.submit(input2_futureTask);
        executorService.shutdown();

        Integer integer1 = 0;
        Integer integer2 = 0;
        try {
            integer1 = input1_futureTask.get();
            integer2 = input2_futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return algorithm(integer1, integer2);
    }

    //这是我们要执行的算法
    public static int algorithm(int input, int input2) {
        return input + input2;
    }

    // 计算时间
    static long computeTime(Supplier<Integer> f){
        long startTime = System.currentTimeMillis();
        System.out.println(f.get());
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
