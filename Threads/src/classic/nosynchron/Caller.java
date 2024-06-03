package classic.nosynchron;

public class Caller implements Runnable {

	String msg;
	Callme target;
	Thread t;

	public Caller(Callme target, String message) {
		this.target = target;
		this.msg = message;
		this.t = new Thread(this);
		this.t.start();
	}

	@Override
	public void run() {
		target.call(msg);
	}

}
