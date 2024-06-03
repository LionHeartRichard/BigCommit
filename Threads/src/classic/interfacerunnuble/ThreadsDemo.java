package classic.interfacerunnuble;

public class ThreadsDemo {
	public static void main(String[] args) {
		new MyThreads();

		try {
			for (int i = 0; i < 5; ++i) {
				System.out.println("main threads - " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("main thread interrupted!!!");
		}
		System.out.println("main thread is out");
	}
}
