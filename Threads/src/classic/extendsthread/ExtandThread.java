package classic.extendsthread;

public class ExtandThread {

	public static void main(String[] args) {
		new MyThread();

		try {
			for (int i = 5; i > 0; --i) {
				System.out.println(i);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("****** main is out!!!!!!!!!!!");
	}

}
