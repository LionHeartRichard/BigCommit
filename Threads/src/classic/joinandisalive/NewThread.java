package classic.joinandisalive;

public class NewThread implements Runnable {

	String name;
	Thread t;

	public NewThread(String nameThread) {
		name = nameThread;
		t = new Thread(this, name);
		System.out.println("new thred - " + name);
		t.start();
	}

	@Override
	public void run() {
		try {
			for (int i = 5; i >= 0; --i) {
				System.out.println(i + " / " + name);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " - Thread OUT!!!");

	}

}
