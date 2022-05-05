package thread.counter;

import java.util.concurrent.CountDownLatch;

public final class CounterThread extends Thread {

    private final Counter counter;
    private final CountDownLatch countDownLatch;

    public CounterThread(Counter counter, CountDownLatch countDownLatch) {
        setName("Counter " + (counter.number + 1));
        this.counter = counter;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        super.run();
        try {
            this.counter.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }
}
