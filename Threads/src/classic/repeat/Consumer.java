package classic.repeat;

public class Consumer implements Runnable {

	private MyQue q;

	public Consumer(MyQue que) {
		q = que;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			q.get();
		}
	}

}
