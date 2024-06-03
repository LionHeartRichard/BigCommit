package classic.synchron;

public class NewThread implements Runnable {

	String msg;
	Person person;
	Thread t;

	public NewThread(String nameAction, Person person) {
		this.msg = nameAction;
		this.person = person;
		this.t = new Thread(this);
		this.t.start();
	}

	@Override
	public void run() {
		synchronized (person) {
			this.person.action(msg);
		}
	}

}
