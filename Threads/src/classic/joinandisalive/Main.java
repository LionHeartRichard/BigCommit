package classic.joinandisalive;

public class Main {

	public static void main(String[] args) {
		NewThread obj1 = new NewThread("One");
		NewThread obj2 = new NewThread("Two");
		NewThread obj3 = new NewThread("Three");

		System.out.println(
				"Thread name = " + obj1.name + " run = " + obj1.t.isAlive());
		System.out.println(
				"Thread name = " + obj2.name + " run = " + obj2.t.isAlive());
		System.out.println(
				"Thread name = " + obj3.name + " run = " + obj3.t.isAlive());

		try {
			System.out.println(
					"-----  Expect completion streams execution  ------");
			obj1.t.join();
			obj2.t.join();
			obj3.t.join();
			System.out.println(
					"--------------------------------------------------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(
				"Thread name = " + obj1.name + " run = " + obj1.t.isAlive());
		System.out.println(
				"Thread name = " + obj2.name + " run = " + obj2.t.isAlive());
		System.out.println(
				"Thread name = " + obj3.name + " run = " + obj3.t.isAlive());

		System.out.println("Thread main out!!!!!!!!!!!!!!!!!!");
	}

}
