package classic.repeat.repeat;

public class SyncMyQue {

	private int count = 0;
	private boolean latch = false;

	public synchronized int get() {
		while (!latch) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		latch = false;
		System.out.println("get message count = " + count);
		notify();
		return count;
	}

	public synchronized void push(int count) {
		while (latch) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		latch = true;
		this.count = count;
		System.out.println("    push:" + count);
		notify();
	}

}
