package gof.isaev.generative;

public class Main {

	public static void main(String[] args) {
		EnumSingleton singleton = EnumSingleton.SINGLETON.getInstance();
		singleton.setInfo("Hi, I'm singleton");
		System.out.println(singleton.getInfo());

		EnumSingleton singleton2 = EnumSingleton.SINGLETON.getInstance();
		System.out.println(singleton2.getInfo());
		singleton2.setInfo("change info - in object 2");
		System.out.println(singleton.getInfo());
		System.out.println("Objects equals: " + singleton.equals(singleton2));
	}

}
