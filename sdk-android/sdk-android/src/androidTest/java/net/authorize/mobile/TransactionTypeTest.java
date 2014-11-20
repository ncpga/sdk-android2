package net.authorize.mobile;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedList;

public class TransactionTypeTest extends TestCase {

    private class ProducerConsumer {
        private LinkedList<TransactionType> list = new LinkedList<TransactionType>();
        private final int LIMIT = 10;
        private Object mLock = new Object();

        public void produce() throws InterruptedException {
            int value = 0;
            while (true) {
                synchronized (mLock) {
                    while (list.size() == LIMIT) {
                        System.out.println("producer waits");
                        mLock.wait();
                    }
                    list.add(TransactionType.MOBILE_DEVICE_LOGIN);
                    System.out.println("producing");
                    mLock.notify();
                }
            }
        }

        public void consume() throws InterruptedException {
            while (true) {
                synchronized (mLock) {
                    while (list.size() == 0) {
                        System.out.println("consumer waits");
                        mLock.wait();
                    }
                    TransactionType m = list.removeFirst();
                    System.out.println("consuming");
                    mLock.notify();
                }
            }
        }
    }
    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    @Test
    public void testTransactionTypeNoInterrupt() throws Exception {
        final ProducerConsumer cp = new ProducerConsumer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cp.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cp.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}