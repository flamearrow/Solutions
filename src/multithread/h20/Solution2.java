package multithread.h20;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by flamearrow on 1/21/16.
 */
public class Solution2 {
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            if (r.nextBoolean()) {
                new H().start();
            } else {
                new O().start();
            }
        }
    }
}

class H extends Thread {
    @Override
    public void run() {
        H2OProducer.produceH();
    }
}

class O extends Thread {
    @Override
    public void run() {
        H2OProducer.produceO();
    }
}

class H2OProducer {
    private static int index = 0;

    static Semaphore sh = new Semaphore(0);
    static Semaphore so = new Semaphore(0);

    public static void produceH() {
        so.release(1);
        try {
            System.out.println(Thread.currentThread().getName() + " blocked on H");
            sh.acquire();
        } catch (InterruptedException e) {
            System.out.println("interruped h");
        }
        System.out.println(Thread.currentThread().getName() + " exists on H");
    }

    public static void produceO() {
        try {
            System.out.println(Thread.currentThread().getName() + " blocked on O");
            so.acquire(2);
        } catch (InterruptedException e) {
            System.out.println("interruped o");
        }
        sh.release(2);
        System.out.println(Thread.currentThread().getName() + " exists on O");
        produceWater();
    }

    public static void produceWater() {
        System.out.println("water created : " + index++);
    }
}
