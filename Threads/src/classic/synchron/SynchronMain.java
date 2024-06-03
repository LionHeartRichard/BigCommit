package classic.synchron;

public class SynchronMain {

	public static void main(String[] args) throws Exception {
		Person p = new Person();
		NewThread obj1 = new NewThread("Jump", p);
		NewThread obj2 = new NewThread("Run", p);
		NewThread obj3 = new NewThread("Callme", p);
		obj1.t.join();
		obj2.t.join();
		obj3.t.join();
	}
}
