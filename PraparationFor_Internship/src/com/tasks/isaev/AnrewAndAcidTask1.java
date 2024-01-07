package com.tasks.isaev;

import java.io.*;
import java.util.Arrays;

public class AnrewAndAcidTask1 {

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/PraparationFor_Internship/resorces/input1.txt"));
				FileWriter writer = new FileWriter(
						"/home/kein/eclipse-workspace/PraparationFor_Internship/resorces/output1.txt")) {

			int n = Integer.parseInt(reader.readLine());
			int[] mas = new int[n];

			String str = reader.readLine();
			String[] arr = str.split(" ");
			boolean flag = true;

			int i = 0;
			for (String s : arr) {
				mas[i] = Integer.parseInt(s);
				if (i > 0) {
					if (mas[i] < mas[i - 1]) {
						flag = false;
						break;
					}
				}
				++i;
			}

			if (flag) {
				int min = Arrays.stream(mas).min().getAsInt();
				int max = Arrays.stream(mas).max().getAsInt();
				max = max - min;
				writer.write(max + "");
			} else {
				writer.write("-1");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
