package com.functionstile.myutils;

import java.util.*;

import static com.functionstile.myutils.FunctionStileOperationByCollection.*;

public class Main {

	public static void main(String[] args) {
		List<Client> clients = new ArrayList<Client>();
		clients.add(new Client("Alex", 23, true));
		clients.add(new Client("Sergey", 234521, true));
		clients.add(new Client("Alexander", 4379, true));
		clients.add(new Client("Ruslan", 45, false));
		clients.add(new Client("Elizaveta", 99999999, true));
		clients.add(new Client("Vladimer", 22332, true));
		clients.add(new Client("Petr", 11, true));
		clients.add(new Client("Ivan", 123435, false));
		clients.add(new Client("Irina", 500, false));
		clients.add(new Client("Kerill", 1000, false));
		clients.add(new Client("Timofiy", 225, true));
		clients.add(new Client("Vika", 555, true));
		clients.add(new Client("Goga", 12, false));
		clients.add(new Client("Roma", -1233, true));
		clients.add(new Client("Dmitriy", 55555, false));
		clients.add(new Client("Avelina", -34, false));
		clients.add(new Client("Fill", -230, false));
		clients.add(new Client("Artem", -10000, true));

		println(filter(clients, c -> c.getMoney() > 0, j -> j.isActivite() == true));
	}

}
