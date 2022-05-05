package thread;

import thread.counter.*;

public class ThreadCounter {

    public static void main(String[] args) {
        ThreadCounter threadCounter = new ThreadCounter();
        threadCounter.test();
    }

    public void test() {
        int target = 1000;
        int totalCounter = 3;
//        new CounterStrategy(CounterStrategy.Type.BLOCKING_QUEUE, target, totalCounter).count();

        //不可以用CYCLIC_BARRIER
        new CounterStrategy(CounterStrategy.Type.REENTRANT_LOCK, target, totalCounter).count();
    }

}
