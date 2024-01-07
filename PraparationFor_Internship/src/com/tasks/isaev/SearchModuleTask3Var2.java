package com.tasks.isaev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class SearchModuleTask3Var2 {
	static int n, k;
	static int[] mas;

	public static void readCoditionTask() {
		try (BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/PraparationFor_Internship/resorces/input3.txt"))) {
			String[] arr = reader.readLine().split(" ");
			n = Integer.parseInt(arr[0]);
			k = Integer.parseInt(arr[1]);
			String[] dataMas = reader.readLine().split(" ");
			mas = new int[n];
			for (int i = 0; i < n; ++i) {
				mas[i] = Integer.parseInt(dataMas[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try (FileWriter writer = new FileWriter(
				"/home/kein/eclipse-workspace/PraparationFor_Internship/resorces/output3.txt")) {
			readCoditionTask();
			String eventStr = "";

			for (int i = 0; i < n; ++i) {
				int[] modulMas = new int[n];
				Long sum = 0l;
				for (int j = 0; j < n; ++j) {
					modulMas[j] = Math.abs(mas[i] - mas[j]);
				}
				Arrays.sort(modulMas);
				for (int j = 1; j <= k; ++j) {
					sum += modulMas[j];
				}
				eventStr += sum + " ";
			}
			writer.write(eventStr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
