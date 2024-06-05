package classic.repeat;

public class SyncThread implements Runnable {

	private ClassForSyncMethods target;
	private String msg;
	Thread t;

	public SyncThread(ClassForSyncMethods target, String msg,
			String nameThread) {
		this.target = target;
		this.msg = msg;
		t = new Thread(this, nameThread);
		t.start();
	}

	public void run() {
		target.print(msg, t.getName());
	}

}
