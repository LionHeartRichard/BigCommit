package classic.interfacerunnuble.multiple;

public class NewThread implements Runnable {

	String name;
	Thread t;

	public NewThread(String threadName) {
		this.name = threadName;
		t = new Thread(this, threadName);
		System.out.println(t + "   CREAT  ");
		t.start();

	}

	@Override
	public void run() {
		try {
			for (int i = 10; i > 0; --i) {
				System.out.println(name + "   " + i);
				Thread.sleep(250);
			}
		} catch (InterruptedException e) {
			System.out.println(name + " interrupted!!!!");
		}
		System.out.println(name + " ----- OUT");
	}

}
