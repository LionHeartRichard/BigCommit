package classic.waitandnotify;

public class MyQueue {

	private int n;
	private boolean valueSet = false;

	public synchronized int get() {
		while (!valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		valueSet = false;
		System.out.println("Получено: " + n);

		notify();

		return n;
	}

	public synchronized void put(int n) {
		while (valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		valueSet = true;
		this.n = n;
		System.out.println("Отправленно: " + n);

		notify();
	}
}
