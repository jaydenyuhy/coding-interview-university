package thread.counter;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter extends Counter {

    private static final ReentrantLock lock = new ReentrantLock(true);

    public ReentrantLockCounter(CounterValue value, int totalCounter, int number) {
        super(value, totalCounter, number);
    }

    @Override
    public void count() {
        while (value.number < value.target) {
            if (value.number % totalCounter == number) {
                lock.lock();
                try {
                    if(value.number < value.target) {
                        value.number = value.number + 1;
                        value.print();
                    }
                } finally {
                    lock.unlock();
                }
            }

        }

    }
}
