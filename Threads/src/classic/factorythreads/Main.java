package classic.factorythreads;

public class Main {

	public static void main(String[] args) {
		SyncQueForFactory que = new SyncQueForFactory();
		new Producer(que);
		new Consumer(que);
	}

}
