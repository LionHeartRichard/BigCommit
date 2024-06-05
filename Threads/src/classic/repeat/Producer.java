package classic.repeat;

public class Producer implements Runnable {

	private MyQue q;

	public Producer(MyQue que) {
		q = que;
		new Thread(this).start();
	}

	@Override
	public void run() {
		int number = 0;
		while (true) {
			q.put(number++);
		}
	}

}
