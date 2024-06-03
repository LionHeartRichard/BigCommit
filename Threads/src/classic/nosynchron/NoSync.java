package classic.nosynchron;

public class NoSync {

	public static void main(String[] args) throws Exception {
		Callme target = new Callme();
		Caller obj1 = new Caller(target, "Добро пожаловать");
		Caller obj2 = new Caller(target, "в синхронизированный");
		Caller obj3 = new Caller(target, "мир!");

		obj1.t.join();
		obj2.t.join();
		obj3.t.join();
	}

}
