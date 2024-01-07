package com.isaev.tasks;

import java.io.*;
import java.util.*;

public class DisciplineAlarmClock {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/Contest_Octouber_2023/resources/input.txt"));
		String s = reader.readLine();
		String str = reader.readLine();

		int count = 0, n = 0, x = 0, k = 0;

		String[] arr = s.split(" ");
		n = Integer.parseInt(arr[0]);
		x = Integer.parseInt(arr[1]);
		k = Integer.parseInt(arr[2]);

		String[] array = str.split(" ");
		int[] mas = new int[n];
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < n; i++) {
			mas[i] = Integer.parseInt(array[i]);
		}
		Arrays.sort(mas);

		for (int i : mas) {
			set.add(i);
		}

		int y = 1;
		for (int i = 1; i < k; i++) {
			y = x * i;
			for (int j = 0; j < n; j++) {
				set.add(mas[j] + y);
			}
		}

		List<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);
		count = list.get(k - 1);

		FileWriter writer = new FileWriter("/home/kein/eclipse-workspace/Contest_Octouber_2023/resources/output.txt");
		writer.write(count + "");
		writer.flush();
		reader.close();
		writer.close();
	}

}
