package classic.waitandnotify;

public class Producer implements Runnable {

	MyQueue q;

	public Producer(MyQueue q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	@Override
	public void run() {
		int n = 0;
		while (true) {
			q.put(n++);
		}
	}

}
