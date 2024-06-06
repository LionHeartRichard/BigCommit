package classic.factorythreads;

public class Consumer implements Runnable {
	private SyncQueForFactory que;
	private Thread t;

	public Consumer(SyncQueForFactory que) {
		this.que = que;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (true) {
			que.get();
		}
	}
}
