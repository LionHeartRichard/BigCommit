package classic.repeat.repeat;

public class Main {

	public static void main(String[] args) {
		SyncMyQue que = new SyncMyQue();
		new Producer(que);
		new Consumer(que);
	}

}
