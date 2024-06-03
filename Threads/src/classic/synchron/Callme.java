package classic.synchron;

public class Callme {
	public synchronized void call(String msg) throws Exception {
		System.out.print("[" + msg);
		Thread.sleep(1000);
		System.out.println("]");
	}
}
