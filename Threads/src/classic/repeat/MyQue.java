package classic.repeat;

public class MyQue {

	private int number;
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
		System.out.println("get message - " + number);

		notify();

		return number;
	}

	public synchronized void put(int number) {
		while (latch) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		latch = true;
		this.number = number;
		System.out.println("put message - " + number);

		notify();
	}

}
