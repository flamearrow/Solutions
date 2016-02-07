package rateLimiter;

// a token bucket rate limiter implementation
// the idea is to generate tokens based on time elapsed since last check, add new generated tokens to allowance
public class RateLimiter2 {
	public static void main(String[] args) {
		final RateLimiter2 rl = new RateLimiter2(10, 1);
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
						Thread.sleep(90);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		// new Thread() {
		// @Override
		// public void start() {
		// for (int i = 0; i < 20; i++) {
		// String name = this.getName() + " : " + i;
		// if (rl.shouldRequest()) {
		// rl.make(name);
		// } else {
		// System.out.println(name + " tossed");
		// }
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		// }.start();
	}

	private int mNum, mSec;
	private int mAllowance;
	private long mLastChecked;

	// up to num request per sec second
	public RateLimiter2(int num, int sec) {
		mNum = num;
		mSec = sec;
		mAllowance = num;
		mLastChecked = 0;
	}

	public boolean shouldRequest() {
		final long now = System.currentTimeMillis();
		if (mLastChecked == 0) {
			mAllowance = mNum - 1;
			mLastChecked = now;
			return true;
		} else {
			final long elapsed = now - mLastChecked;
			long toAdd = elapsed * mNum / mSec / 1000;
			System.out.println("toAdd: " + toAdd);
			mAllowance += toAdd;
			if (toAdd > 0) {
				// note we only update mLastChecked if toAdd is positive
				//  if toAdd is zero, it means no allowance is added, elapsed is not enough to generate new tokens
				//  we should keep using the old mLastChcked so that next time we can have a bigger elapsed to check newly generated tokens
				mLastChecked = now;
			}
			if (mAllowance > mNum) {
				mAllowance = mNum;
			}
			if (mAllowance > 1) {
				mAllowance--;
				return true;
			} else {
				return false;
			}
		}
	}

	public void make(String s) {
		new RequestClient().request(s);
	}
}
