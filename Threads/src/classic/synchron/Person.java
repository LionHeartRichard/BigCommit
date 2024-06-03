package classic.synchron;

public class Person {
	public void action(String msg) {
		System.out.println("Begin action " + msg);
		try {
			for (int i = 5; i > 0; --i) {
				System.out.println(i + " " + msg);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End action " + msg);
	}
}
