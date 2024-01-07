package com.tasks.isaev;

import java.io.*;
import java.util.*;

public class AirlineSeatTask2 {
	static class Condition {
		String row;
		boolean direction;
		int begin;
		int end;
	}

	static int n;
	static String[] masRows;
	static int m;
	static List<Condition> listCondition = new ArrayList<Condition>();
	static final String emptyStr = "..._...";
	static final String[] seats = { "A", "B", "C", "_", "D", "E", "F" };

	static void readSourceFileTask() {
		try (BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/PraparationFor_Internship/resorces/input2a.txt"))) {
			n = Integer.parseInt(reader.readLine());
			masRows = new String[n];
			for (int i = 0; i < n; ++i) {
				masRows[i] = reader.readLine();
			}
			m = Integer.parseInt(reader.readLine());
			String[] tasks = new String[m];

			for (int i = 0; i < m; ++i) {
				tasks[i] = reader.readLine();

				String[] arr = tasks[i].split(" ");
				int k = Integer.parseInt(arr[0]);
				Condition condition = new Condition();
				if (arr[1].equals("left")) {
					condition.direction = false;
					if (arr[2].equals("aisle")) {
						int end = 3;
						int begin = end - k;
						condition.row = emptyStr.substring(begin, end);
						condition.begin = begin;
						condition.end = end;
					} else {
						int begin = 0;
						int end = k;
						condition.row = emptyStr.substring(begin, end);
						condition.begin = 0;
						condition.end = end;
					}
				} else {
					condition.direction = true;
					if (arr[2].equals("aisle")) {
						int begin = 4;
						int end = begin + k;
						condition.row = emptyStr.substring(begin, end);
						condition.begin = begin;
						condition.end = end;
					} else {
						int end = 7;
						int begin = end - k;
						condition.row = emptyStr.substring(begin, end);
						condition.begin = begin;
						condition.end = end;
					}
				}
				listCondition.add(condition);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try (FileWriter writer = new FileWriter(
				"/home/kein/eclipse-workspace/PraparationFor_Internship/resorces/output2.txt")) {

			readSourceFileTask();

			int numberRow = -1;
			for (Condition condition : listCondition) {
				numberRow = getNumberRow(condition);
				int printNumberRow = numberRow + 1;
				if (numberRow != -1) {
					String eventStr = "Passengers can take seats:";
					for (int i = condition.begin; i < condition.end; ++i) {
						eventStr += " " + printNumberRow + seats[i];
					}
					System.out.println(eventStr);
					for (int i = 0; i < n; ++i) {
						if (i == numberRow) {
							System.out.println(masRows[i]);
							masRows[i] = masRows[i].replace('X', '#');
						} else {
							System.out.println(masRows[i]);
						}
					}
				} else {
					System.out.println("Cannot fulfill passengers requirements");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int getNumberRow(Condition condition) {
		for (int i = 0; i < n; ++i) {
			String subStr = masRows[i].substring(condition.begin, condition.end);
			int index = subStr.indexOf(condition.row);
			if (index != -1) {
				String xxx = condition.row.replace('.', 'X');
				String prefix = masRows[i].substring(0, condition.begin);
				String postfix = masRows[i].substring(condition.end);
				masRows[i] = prefix + xxx + postfix;
				return i;
			}
		}
		return -1;
	}
}
