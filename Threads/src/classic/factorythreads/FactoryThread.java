package classic.factorythreads;

public class FactoryThread implements Runnable {

	private Thread t;

	public static FactoryThread getThread() {
		FactoryThread obj = new FactoryThread();
		obj.t = new Thread(obj);
		obj.t.start();
		return obj;
	}

	@Override
	public void run() {
		for (int i = 10; i >= 0; --i) {
			System.out.println("Factory thred counter: " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void myJoin() {
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
