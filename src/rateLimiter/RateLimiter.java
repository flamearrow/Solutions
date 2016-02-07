package rateLimiter;

//Implement a rate limiter   attribute/decoration/annotation on top of an API endpoint. 
// caps to N requests per minute with a rolling window (implement from scratch / test / compiling + working code)

// this implementation doens't work for burst rate
//  e.g |1 0 0 0 0 0 0 0 0 0 0 {1 1 1|1 1 1} 0 0 0 0 0 0 0 0 0 0 1|
// it makes sure the inside the two squares, there're only four 1's (4/14)
//  but doesn't ensure the rate between the bracket has (4/14), it's already 6/6 which is over the limit
public class RateLimiter {
	public static void main(String[] args) {
		final RateLimiter rl = new RateLimiter(1, 1);
		new Thread() {
			@Override
			public void start() {
				for (int i = 0; i < 20; i++) {
					String name = this.getName() + " : " + i;
					if (rl.shouldRequest()) {
						rl.make(name);
					} else {
						System.out.println(name + " tossed");
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			@Override
			public void start() {
				for (int i = 0; i < 20; i++) {
					String name = this.getName() + " : " + i;
					if (rl.shouldRequest()) {
						rl.make(name);
					} else {
						System.out.println(name + " tossed");
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private int mNum;
	private int mSec;
	private long mLastChecked;
	private int mRequested;

	public RateLimiter(int num, int sec) {
		// can only make up to num requests per sec second
		mNum = num;
		mSec = sec;
		mRequested = 0;
	}

	public synchronized boolean shouldRequest() {
		final long cur = System.currentTimeMillis();
		// System.out.println(Thread.currentThread().getName() +
		// " requested at "
		// + cur);
		if (mRequested == 0) {
			mLastChecked = cur;
			mRequested = 1;
			return true;
		} else {
			final long elapsed = cur - mLastChecked;
			if (elapsed < mSec * 1000) {
				if (mRequested >= mNum) {
					return false;
				} else {
					mRequested++;
					return true;
				}
			} else {
				mLastChecked = cur;
				mRequested = 1;
				return true;
			}
		}
	}

	public void make(String s) {
		new RequestClient().request(s);
	}
}

class RequestClient {
	public void request(String s) {
		System.out.println("new request made: " + s);
	}
}