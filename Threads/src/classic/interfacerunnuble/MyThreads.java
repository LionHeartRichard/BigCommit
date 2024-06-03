package classic.interfacerunnuble;

public class MyThreads implements Runnable {

	Thread t;

	public MyThreads() {
		t = new Thread(this, "name-thread-Number-One");
		System.out.println("Children thread is created");
		t.start();
	}

	@Override
	public void run() {
		System.out.println("*** comleted method run ***");
		try {
			for (int i = 5; i > 0; --i) {
				System.out.println(i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("*** out children thred ***");
	}

}
