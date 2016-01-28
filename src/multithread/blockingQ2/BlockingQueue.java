package multithread.blockingQ2;


/**
 * Created by flamearrow on 1/25/16.
 */
public class BlockingQueue<T> {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> b = new BlockingQueue<>();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(b.dequeue());
                    } catch (InterruptedException e) {
                        System.out.println("interrupted in dequeue");
                    }
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        b.enqueue(i);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted in enqueue");
                    }
                }
            }
        }.start();
    }


    private Object[] mArray;
    private int mSize;
    private int mHead, mTail;
    private static final int DEFAULT_SIZE = 10;

    public BlockingQueue() {
        this(DEFAULT_SIZE);
    }

    public BlockingQueue(int size) {
        mSize = size;
        mArray = new Object[mSize];
    }

    public synchronized void enqueue(T object) throws InterruptedException {
        if (isFull()) {
            System.out.println("full in enqueue, block");
            wait();
        }
        mArray[mTail] = object;
        mTail = (mTail + 1) % mSize;
        System.out.println("added: " + object);
        notify();
    }

    public synchronized T peek() throws InterruptedException {
        if (isEmpty()) {
            wait();
        }
        return (T) mArray[mHead];
    }

    public synchronized T dequeue() throws InterruptedException {
        if (isEmpty()) {
            System.out.println("empty in dequeue, block");
            wait();
        }
        T ret = (T) mArray[mHead];
        mHead = (mHead + 1) % mSize;
        System.out.println("removed: " + ret);
        notify();
        return ret;
    }

    public boolean isEmpty() {
        return mTail == mHead;
    }

    public boolean isFull() {
        return (mTail + 1) % mSize == mHead;
    }

    public int size() {
        return isEmpty() ? 0 : mTail < mHead ? mTail + mArray.length - mHead : mTail - mHead;
    }

    public int capacity() {
        return mArray.length;
    }
}
