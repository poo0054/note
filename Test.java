import java.util.concurrent.SynchronousQueue;

/**
 * @author zhangzhi
 */
public class Test {
    public static void t1() {
        SynchronousQueue<Object> objects = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "a");
                objects.put("a");
                System.out.println(Thread.currentThread().getName() + "b");
                objects.put("b");
                System.out.println(Thread.currentThread().getName() + "c");
                objects.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "->"
                        + objects.take());
                System.out.println(Thread.currentThread().getName() + "->"
                        + objects.take());
                System.out.println(Thread.currentThread().getName() + "->"
                        + objects.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "b").start();
    }
}
