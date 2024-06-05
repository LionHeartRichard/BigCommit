package classic.suspendresume;

public class MainSuspendAndResumeThread {
	public static void main(String[] args) throws InterruptedException {
		NewThread obj1 = new NewThread("OneThread");
		NewThread obj2 = new NewThread("TwoThread");

		Thread.sleep(1000);
		obj1.suspendThread();
		System.out.println("*******************suspend - " + obj1.t.getName());
		Thread.sleep(1000);
		System.out.println("*******************resume- " + obj1.t.getName());
		obj1.resumeThread();

		System.out.println(
				"suspend------------------------- " + obj2.t.getName());
		obj2.suspendThread();
		Thread.sleep(1000);
		System.out.println(
				"resume-----------------------------" + obj2.t.getName());
		obj2.resumeThread();

		obj1.t.join();
		obj2.t.join();

		System.out.println("Thread main - is out");
	}
}
