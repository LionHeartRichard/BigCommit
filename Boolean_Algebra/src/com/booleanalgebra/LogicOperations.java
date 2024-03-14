package com.booleanalgebra;

public class LogicOperations {

	public static void main(String[] args) {

		boolean[] arr = { true, true, true, false, false, false };

		System.out.println("Unarnay logic operations:");
		System.out.println("-true:" + NO(true));
		System.out.println("-false:" + NO(false));

		boolean value = true;
		int i = 0;

		System.out.println("Binarnay logic operations:");
		while (i < arr.length) {
			value = OR(arr[i], arr[i + 1]);
			System.out.println("OR:" + value);
			value = XOR(arr[i], arr[i + 1]);
			System.out.println("XOR:" + value);
			value = AND(arr[i], arr[i + 1]);
			System.out.println("AND:" + value);
			System.out.println("------------------------");
			i += 2;
		}
		i = 0;

		System.out.println("***********-----Secondary logic operations:------************");

		while (i < arr.length) {
			value = materialConditional(arr[i], arr[i + 1]);
			System.out.println("The materal conditional: " + value);
			value = equivalence(arr[i], arr[i + 1]);
			System.out.println("Equivalence: " + value);
			value = exclusiveOR(arr[i], arr[i + 1]);
			System.out.println("exclusive_OR: " + value);
			System.out.println("***********************************");
			i += 2;
		}
	}

	private static boolean NO(boolean value) {
		return !value;
	}

	public static boolean OR(boolean value1, boolean value2) {
		return value1 || value2;
	}

	public static boolean XOR(boolean value1, boolean value2) {
		return value1 ^ value2;
	}

	public static boolean AND(boolean value1, boolean value2) {
		return value1 && value2;
	}

	public static boolean materialConditional(boolean value1, boolean value2) {
		return !value1 || value2;
	}

	public static boolean equivalence(boolean value1, boolean value2) {
		return (value1 && value2) || (!value1 && !value2);
	}

	public static boolean exclusiveOR(boolean value1, boolean value2) {
		return (value1 || value2) && (!value1 || !value2);
	}

}
