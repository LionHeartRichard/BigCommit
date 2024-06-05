package classic.repeat;

public class Main {

	public static void main(String[] args) throws Exception {
		Messager m = new Messager();
		NewThread obj1 = new NewThread(m, "run", "ONE");
		NewThread obj2 = new NewThread(m, "jump", "TWO");
		NewThread obj3 = new NewThread(m, "call", "THREE");

		obj1.t.join();
		obj2.t.join();
		obj3.t.join();
	}

}
