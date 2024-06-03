package classic.interfacerunnuble.multiple;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		new NewThread("One");
		new NewThread("Two");
		new NewThread("Three");
		new NewThread("Four");
		new NewThread("Five");
		new NewThread("Six");
		new NewThread("Seven");
		new NewThread("Eight");
		new NewThread("Nine");
		new NewThread("Ten");
		Thread.sleep(10000);
		System.out.println("main out");
	}

}
