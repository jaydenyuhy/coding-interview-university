package thread.counter;

import java.util.concurrent.CountDownLatch;

public class CounterStrategy {

    public enum Type {
        SYNCHRONIZED,
        REENTRANT_LOCK,
        THREAD_YIELD,
        BLOCKING_QUEUE,
        CYCLIC_BARRIER,
        SEMAPHORE
    }

    private final Type type;
    private final int totalCounter;
    private final CounterValue counterValue;

    public CounterStrategy(Type type, int target, int totalCounter) {
        this.type = type;
        this.totalCounter = totalCounter;
        this.counterValue = new CounterValue(target);
    }

    public void count() {
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(totalCounter);
        for (int i = totalCounter - 1; i >= 0; i--) {
            new CounterThread(createCounter(i), countDownLatch).start();
        }
        try{
            countDownLatch.await();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Total cost : " + (System.currentTimeMillis() - startTime));
    }

    public Counter createCounter(int number) {
        switch (type) {
            case SYNCHRONIZED:
                return new SynchronizedCounter(counterValue, totalCounter, number);
            case REENTRANT_LOCK:
                return new ReentrantLockCounter(counterValue, totalCounter, number);
            case THREAD_YIELD:
                return new ThreadYieldCounter(counterValue, totalCounter, number);
            case BLOCKING_QUEUE:
                return new BlockingQueueCounter(counterValue, totalCounter, number);
            case CYCLIC_BARRIER:
                return new CyclicBarrierCounter(counterValue, totalCounter, number);
            case SEMAPHORE:
                return new SemaphoreCounter(counterValue, totalCounter, number);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
