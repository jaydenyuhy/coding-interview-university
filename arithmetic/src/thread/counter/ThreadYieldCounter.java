package thread.counter;

public class ThreadYieldCounter extends Counter {

    public ThreadYieldCounter(CounterValue value, int totalCounter, int number) {
        super(value, totalCounter, number);
    }

    @Override
    public void count() {
        //用ThreadYield没法保证原子性操作，这里+1会不符合预期结果
        while (value.number < value.target) {
            if (value.number % totalCounter == number) {
                value.number = value.number + 1;
                value.print();
            } else {
                Thread.yield();
            }
        }

    }
}
