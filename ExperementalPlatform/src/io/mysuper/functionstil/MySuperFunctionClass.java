package io.mysuper.functionstil;

import java.util.*;

public class MySuperFunctionClass {

	static class Client {
		String name;
		long money;
		boolean activite;
		int ageClient;
		int ageWork;

		public Client() {
		}

		public Client(String name, long money, boolean activite, int ageClient, int ageWork) {
			super();
			this.name = name;
			this.money = money;
			this.activite = activite;
			this.ageClient = ageClient;
			this.ageWork = ageWork;
		}

		@Override
		public String toString() {
			return name + " " + money + " " + activite + " " + ageClient + " " + ageWork;
		}

	}

	interface Action1<A> {
		void applay(A arg1);
	}

	interface Fun1<T, A> {
		T applay(A arg1);
	}

	interface Fun2<T, A, B> {
		T applay(A arg1, B arg2);
	}

	public static <A> void forEach(List<A> list, Action1<A> action) {
		for (A i : list) {
			action.applay(i);
		}
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filter1) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i) && filter2.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i) && filter2.applay(i) && filter3.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3, Fun1<Boolean, A> filter4) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i) && filter2.applay(i) && filter3.applay(i) && filter4.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterOr(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i) || filter2.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterOr(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i) || filter2.applay(i) || filter3.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterOr(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3, Fun1<Boolean, A> filter4) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.applay(i) || filter2.applay(i) || filter3.applay(i) || filter4.applay(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterAndOr(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3, Fun1<Boolean, A> filter4) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if ((filter1.applay(i) && filter2.applay(i)) || (filter3.applay(i) && filter4.applay(i))) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterOrAnd(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3, Fun1<Boolean, A> filter4) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if ((filter1.applay(i) || filter2.applay(i)) && (filter3.applay(i) || filter4.applay(i))) {
				result.add(i);
			}
		}
		return result;
	}

	public static void println(Collection<?> items) {
		for (Object i : items) {
			System.out.println(i.toString());
		}
	}

	public static void print(Collection<?> items) {
		for (Object i : items) {
			System.out.print(" " + i.toString());
		}
	}

	public static void main(String[] args) {
		List<Client> clients = new ArrayList<Client>();
		clients.add(new Client("Alex", 23, true, 20, 1));
		clients.add(new Client("Sergey", 234521, true, 35, 15));
		clients.add(new Client("Alexander", 4379, true, 35, 15));
		clients.add(new Client("Ruslan", 45, false, 20, 1));
		clients.add(new Client("Elizaveta", 99999999, true, 50, 10));
		clients.add(new Client("Vladimer", 22332, true, 25, 10));
		clients.add(new Client("Petr", 11, true, 19, 0));
		clients.add(new Client("Ivan", 123435, false, 23, 3));
		clients.add(new Client("Irina", 500, false, 16, 2));
		clients.add(new Client("Kerill", 1000, false, 15, 1));
		clients.add(new Client("Timofiy", 225, true, 12, 0));
		clients.add(new Client("Vika", 555, true, 32, 2));
		clients.add(new Client("Goga", 12, false, 25, 1));
		clients.add(new Client("Roma", -1233, true, 54, 2));
		clients.add(new Client("Dmitriy", 55555, false, 10, 0));
		clients.add(new Client("Avelina", -34, false, 15, 0));
		clients.add(new Client("Fill", -230, false, 40, 20));
		clients.add(new Client("Artem", -10000, true, 42, 10));

		println(filter(clients, i -> i.money > 0, j -> j.activite == true, a -> a.ageClient > 17, b -> b.ageWork > 1));
	}

}
