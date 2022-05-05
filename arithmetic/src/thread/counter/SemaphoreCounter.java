package thread.counter;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter extends Counter {

    private static Semaphore semaphore = new Semaphore(1);

    public SemaphoreCounter(CounterValue value, int totalCounter, int number) {
        super(value, totalCounter, number);
    }

    @Override
    public void count() throws InterruptedException {
        while (value.number < value.target) {
            if (value.number % totalCounter == number) {
                semaphore.acquire();
                value.number = value.number + 1;
                value.print();
                semaphore.release();
            }
        }

    }
}
