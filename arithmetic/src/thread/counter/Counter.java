package thread.counter;

public abstract class Counter {

    protected final CounterValue value;
    protected final int totalCounter;
    protected final int number;

    public Counter(CounterValue value, int totalCounter, int number) {
        this.value = value;
        this.totalCounter = totalCounter;
        this.number = number;
    }

    public abstract void count() throws Exception;
}
