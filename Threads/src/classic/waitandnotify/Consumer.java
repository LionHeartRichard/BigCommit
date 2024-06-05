package classic.waitandnotify;

public class Consumer implements Runnable {

	MyQueue q;

	public Consumer(MyQueue q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	@Override
	public void run() {
		while (true) {
			q.get();
		}
	}

}
