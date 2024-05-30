package classic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WriteBigArray {

	public static final int length = 5000000;

	public static void main(String[] args) {
		String fileName = "/home/kein/Doc/data_for_algorithm/BigArray.txt";
		try {
			writeBigArray(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeBigArray(String fileName) throws IOException {
		FileWriter writer = new FileWriter(fileName);

		Random numRandom = new Random();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; ++i) {
			int num = numRandom.nextInt(0, 99);
			builder.append(num + ",");
		}

		writer.write(builder.toString());

		writer.flush();
		writer.close();
	}

}
