package thread.counter;

public class SynchronizedCounter extends Counter {

    private static final Object lock = new Object();

    public SynchronizedCounter(CounterValue value, int totalCounter, int number) {
        super(value, totalCounter, number);
    }

    @Override
    public void count() throws InterruptedException{
        synchronized (lock) {
            while (value.number < value.target) {
                if (value.number % totalCounter == number) {
                    value.number++;
                    value.print();
                    lock.notifyAll();
                } else {
                    lock.wait();
                }
            }
        }
    }

}
