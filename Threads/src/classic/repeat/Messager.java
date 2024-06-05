package classic.repeat;

public class Messager {
	public void message(String msg) {
		try {
			for (int i = 5; i > 0; --i) {
				System.out.println(msg + " " + i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
