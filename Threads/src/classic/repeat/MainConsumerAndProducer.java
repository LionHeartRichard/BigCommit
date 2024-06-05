package classic.repeat;

public class MainConsumerAndProducer {

	public static void main(String[] args) {

		MyQue que = new MyQue();
		new Producer(que);
		new Consumer(que);

	}

}
