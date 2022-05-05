package thread.counter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueCounter extends Counter {

    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);
    static {
        try {
            queue.put(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BlockingQueueCounter(CounterValue value, int totalCounter, int number) {
        super(value, totalCounter, number);
    }

    @Override
    public void count() throws InterruptedException {
        while (value.number < value.target) {
            if (value.number % totalCounter == number) {
                queue.take();
//                if(value.number < value.target) {
                    value.number = value.number + 1;
                    value.print();
//                }
                queue.put(0);
            }
        }

    }
}
