package classic.waitandnotify;

public class Main {

	public static void main(String[] args) {
		MyQueue que = new MyQueue();
		new Producer(que);
		new Consumer(que);
	}

}
