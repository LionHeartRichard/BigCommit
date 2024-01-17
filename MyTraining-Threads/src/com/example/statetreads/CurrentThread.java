package com.example.statetreads;

public class CurrentThread {

	public static void main(String[] args) {

		Thread t = Thread.currentThread();

		System.out.println("Текущий поток исполнения" + t);

		t.setName("newNameForCurrentThreadMain");

		System.out.println("После изменения имени потока" + t);

		try {
			for (int i = 5; i > 0; --i) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Главный поток прерван!!!");
		}
	}

}
