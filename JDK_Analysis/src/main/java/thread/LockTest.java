package thread;


import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
//        reentrantSynchronizedTest();
//        reentrantLockTest();
//        spinLockTest();
        reentrantSpinLockTest();
    }

    public static void fairTest(){
        ReentrantLock reentrantLock = new ReentrantLock();
        // 公平锁
        ReentrantLock reentrantLock1 = new ReentrantLock(true);
    }

    public static void reentrantSynchronizedTest(){
        Phone phone = new Phone();
        /*
          A:pick
          A:call
          B:pick
          B:call
         */
        new Thread(phone::pick,"A").start();
        new Thread(phone::pick,"B").start();
    }

    public static void reentrantLockTest(){
        PhoneLock phone = new PhoneLock();
        /*
          A:pick
          A:call
          B:pick
          B:call
         */
        new Thread(phone::pick,"A").start();
        new Thread(phone::pick,"B").start();
    }

    public static void spinLockTest() {
        // 自旋锁测试
        SpinLock spinLock = new SpinLock();
        new Thread(()-> {
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unLock();
            }

        },"A").start();

        new Thread(()-> {
            try {
                // 保证A先申请到锁
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unLock();
            }

        },"B").start();
    }

    public static void reentrantSpinLockTest() {
        // 可重入自旋锁测试
        ReentrantSpinLock spinLock = new ReentrantSpinLock();
        new Thread(()-> {
            // 申请两次，可重入！
            spinLock.lock();
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unLock();
                spinLock.unLock();
            }

        },"A").start();

        new Thread(()-> {
            try {
                // 保证A先申请到锁
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinLock.unLock();
            }

        },"B").start();
    }
}


class Phone{

    public synchronized void pick(){
        System.out.println(Thread.currentThread().getName() + ":pick");
        // call的锁在pick的锁拿到后自动拿到，在call调用后才会释放
        call();
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName() + ":call");
    }
}

class PhoneLock{
    Lock lock = new ReentrantLock();
    public void pick(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":pick");
            // call的锁在pick的锁拿到后自动拿到，在call调用后才会释放
            call();
        }finally {
            lock.unlock();
        }
    }

    public void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":call");
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 使用CAS来实现自旋锁，忙等待！
 * 无法实现可重入
 */
class SpinLock{

    // 初始值为null，初始时间戳为1，没必要！lock只有null和有人使用两个状态，不需要时间戳
    //AtomicStampedReference<Thread> lock = new AtomicStampedReference<>(null, 1);
    AtomicReference<Thread> lock = new AtomicReference<>(null);

    public void lock(){
        System.out.println(Thread.currentThread().getName() + "==> Lock");
        // 期待lock中为null，是的话就把它置为自己的线程，占用；否则忙等待申请
        while (!lock.compareAndSet(null, Thread.currentThread())){
            // 忙等待
        }
    }

    public void unLock(){
        System.out.println(Thread.currentThread().getName() + "==> Unlock");
        // 期待lock中为本线程，是的话就把它置为null，放弃锁
        lock.compareAndSet(Thread.currentThread(), null);
    }
}

@AllArgsConstructor
class TwoTuple<A> {

    public final A first;
    public volatile Integer second;

    public synchronized void add(){
        second++;
    }

    public synchronized void minus(){
        second--;
    }

    public String toString(){
        return "(" + first + ", " + second + ")";
    }

}

/**
 * 可重入？
 */
class ReentrantSpinLock{

    AtomicReference<TwoTuple<Thread>> lock = new AtomicReference<>(null);

    public void lock(){
        System.out.println(Thread.currentThread().getName() + "==> Lock");
        // 期待lock中为null或是自己，是的话就把它置为自己的线程，stamp+1，占用；否则忙等待申请
        while (!lock.compareAndSet(null, new TwoTuple<>(Thread.currentThread(), 1))){
            // 忙等待
            if(lock.get().first == Thread.currentThread()){
                lock.get().add();
                break;
            }
        }
    }

    public void unLock(){
        System.out.println(Thread.currentThread().getName() + "==> Unlock");
        // 计数-1
        if(lock.get() == null){
            throw new RuntimeException("More unLock");
        }
        lock.get().minus();
        if(lock.get().second == 0){
            // 计数为0，该线程所有申请的锁都已经释放
            lock.set(null);
        }
    }
}