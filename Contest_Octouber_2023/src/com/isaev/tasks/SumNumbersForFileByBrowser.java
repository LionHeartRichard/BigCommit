package com.isaev.tasks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class SumNumbersForFileByBrowser {

	static class JsonUtils {

		public static String readLinkURL() {
			try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
				String strURL = reader.readLine();
				String port = reader.readLine();
				String a = reader.readLine();
				String b = reader.readLine();
				String link = strURL + ":" + port + "?" + "a=" + a + "&" + "b=" + b;
				return link;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public static String parseUrl(URL url) {
			if (url == null) {
				return "";
			}
			StringBuilder stringBuilder = new StringBuilder();

			try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					stringBuilder.append(inputLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return stringBuilder.toString();
		}

		public static String parseJson(String resultJson) {
			try {
				JSONParser parser = new JSONParser();
				JSONArray mas = (JSONArray) parser.parse(resultJson);
				Long sum = 0l;
				for (Object o : mas) {
					sum += Long.parseLong(o.toString());
				}
				return "" + sum;

			} catch (Exception e) {
				e.printStackTrace();
				return resultJson;
			}
		}

		public static URL createUrl(String link) {
			try {
				return new URL(link);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		String link = JsonUtils.readLinkURL();
		URL url = JsonUtils.createUrl(link);
		String resultJson = JsonUtils.parseUrl(url);
		String sum = JsonUtils.parseJson(resultJson);

		FileWriter writer = new FileWriter("output.txt");
		writer.write(sum);
		writer.flush();
		writer.close();
	}
}
