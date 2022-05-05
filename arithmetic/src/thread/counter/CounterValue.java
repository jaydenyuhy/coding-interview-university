package thread.counter;

public class CounterValue {
    public volatile int number = 0;
    public final int target;

    public CounterValue(int target) {
        this.target = target;
    }

    public void print(){
//        System.out.print(number + ",");
        System.out.println(Thread.currentThread().getName() + " handle " + number);
    }
}
