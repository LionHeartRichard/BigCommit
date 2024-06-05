package classic.repeat.repeat;

public class Consumer implements Runnable {

	SyncMyQue que;

	public Consumer(SyncMyQue que) {
		this.que = que;
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			que.get();
		}
	}

}
