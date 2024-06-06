package classic.factorythreads;

public class FactoryMain {

	public static void main(String[] args) {
		FactoryThread factoryThread = FactoryThread.getThread();
		factoryThread.myJoin();
		System.out.println("method MAIN - is out!!!!");
	}

}
