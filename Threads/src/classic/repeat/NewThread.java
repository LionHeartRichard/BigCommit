package classic.repeat;

public class NewThread implements Runnable {

	private Messager target;
	Thread t;
	private String msg;
	private String name;

	public NewThread(Messager target, String msg, String nameThread) {
		this.target = target;
		this.msg = msg;
		this.t = new Thread(this, nameThread);
		this.name = nameThread;
		t.start();
	}

	@Override
	public void run() {
		target.message(msg + " " + name);
	}

}
