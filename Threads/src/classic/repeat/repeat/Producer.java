package classic.repeat.repeat;

public class Producer implements Runnable {

	SyncMyQue que;

	public Producer(SyncMyQue que) {
		this.que = que;
		new Thread(this).start();
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			que.push(count++);
		}
	}

}
