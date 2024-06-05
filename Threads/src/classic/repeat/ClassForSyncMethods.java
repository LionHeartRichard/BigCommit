package classic.repeat;

public class ClassForSyncMethods {
	public synchronized void print(String msg, String nameThread) {
		try {
			System.out.println("Run thread " + nameThread);
			for (int i = 3; i >= 0; --i) {
				System.out.print(msg + " " + i + " / ");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Out thread " + nameThread);
	}

}
