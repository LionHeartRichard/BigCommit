package classic.factorythreads;

public class Producer implements Runnable {

	private Thread t;
	private SyncQueForFactory que = new SyncQueForFactory();

	public Producer(SyncQueForFactory que) {
		this.que = que;
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			que.push(count++);
		}
	}

}
