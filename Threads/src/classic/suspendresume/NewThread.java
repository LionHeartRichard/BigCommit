package classic.suspendresume;

public class NewThread implements Runnable {

	private String name;
	Thread t;
	private boolean flagSuspend;

	public NewThread(String threadName) {
		name = threadName;
		t = new Thread(this, name);
		System.out.println("Thread - " + name + " run!!!");
		flagSuspend = false;
		t.start();
	}

	@Override
	public void run() {
		try {
			for (int i = 15; i >= 0; --i) {
				System.out.println(name + ":" + i);
				Thread.sleep(200);
				synchronized (this) {
					while (flagSuspend) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("   " + name + " is out");
	}

	public synchronized void suspendThread() {
		flagSuspend = true;
	}

	public synchronized void resumeThread() {
		flagSuspend = false;
		notify();
	}

}
