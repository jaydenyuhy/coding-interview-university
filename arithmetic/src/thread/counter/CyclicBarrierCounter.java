package thread.counter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 用于多个线程(大于等于3个时不准确，会存在多个线程修改值问题)
 */
public class CyclicBarrierCounter extends Counter {

    private static CyclicBarrier cyclicBarrier;

    public CyclicBarrierCounter(CounterValue value, int totalCounter, int number) {
        super(value, totalCounter, number);
        if(cyclicBarrier == null){
            cyclicBarrier = new CyclicBarrier(totalCounter);
        }
    }

    @Override
    public void count() throws BrokenBarrierException, InterruptedException {
        boolean await = false;
        while (value.number < value.target) {
            while (value.number % totalCounter != number){
                //空转
            }
            value.number = value.number + 1;
            value.print();
            cyclicBarrier.await();
            if (value.number % totalCounter == number) {
                await = false;
                value.number = value.number + 1;
                value.print();
            } else {
                await = true;
                cyclicBarrier.await();
            }
        }
        if(!await){
            cyclicBarrier.await();
        }
    }
}
