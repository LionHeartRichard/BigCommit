package classic.factorythreads;

public class SyncQueForFactory {
	private int count = 0;
	private boolean latch = false;

	public SyncQueForFactory() {
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
		System.out.println("push - " + count);

		notify();
	}

	public synchronized int get() {
		while (!latch) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("get - " + count);
		latch = false;

		notify();
		return count;
	}
}
