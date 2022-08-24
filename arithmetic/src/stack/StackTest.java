package stack;

public class StackTest {

    public static void main(String[] args) {


        CQueue queue = new CQueue();
        System.out.println(queue.deleteHead());
        queue.appendTail(5);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
