package classic.repeat;

public class MainSyncMethods {

	public static void main(String[] args) throws Exception {
		ClassForSyncMethods target = new ClassForSyncMethods();
		SyncThread o1 = new SyncThread(target, "How are you?", "Johnny");
		SyncThread o2 = new SyncThread(target, "I'm fine are you?", "Harry");

		o1.t.join();
		o2.t.join();
	}
}
