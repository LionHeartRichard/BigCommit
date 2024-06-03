package classic.extendsthread;

public class MyThread extends Thread {
	public MyThread() {
		super("DemoChildrenThread");
		System.out.println(this);
		start();
	}

	public void run() {
		try {
			for (int i = 10; i > 0; --i) {
				System.out.println(i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Children thred OUT");
	}
}
