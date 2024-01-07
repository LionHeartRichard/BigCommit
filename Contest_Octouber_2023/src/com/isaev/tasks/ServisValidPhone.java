package com.isaev.tasks;

//доделать!!!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class ServisValidPhone {

//	{
//		  "normalized": "+7-982-000-0000",
//		  "status": true
//	}

	public static String readPhoneNumber() {
		try (BufferedReader reader = new BufferedReader(
				new FileReader("/home/kein/eclipse-workspace/Contest_Octouber_2023/resources/phone.txt"))) {
			String[] mas = new String[5];
			for (int i = 0; i < 5; ++i) {
				mas[i] = reader.readLine();
			}
			return mas[2];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String validation(String phone) {
		phone = validSizePhone(phone);
		StringBuilder builder = new StringBuilder(phone);
		if (phone.length() == 11) {
			return validNumber(phone);
		}
		if (phone.length() > 11) {
			return builder.toString();
		}
		return builder.toString();
	}

	private static String validNumber(String phone) {
		StringBuilder builder = new StringBuilder(phone);
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < 10; ++i) {
			set.add(i);
		}
		for (int i = 0; i < phone.length(); ++i) {
			if (!set.contains(builder.charAt(i) - '0')) {
				return "";
			}
		}
		return builder.toString();
	}

	private static String validSizePhone(String phone) {
		if (phone.length() >= 11) {
			return phone;
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		StringBuilder query = new StringBuilder(readPhoneNumber());
		String strQuery = query.toString();
		String phone = strQuery.substring(strQuery.indexOf(61) + 1);
		System.out.println(phone);
		String validPhone = validation(phone);
		System.out.println(validPhone.length());
		System.out.println(validPhone);
	}

}
