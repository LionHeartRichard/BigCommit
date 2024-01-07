package com.myutil;

import java.util.*;

import com.functional.*;

public class UtilFunctionClassForCollection {

	public static void print(Object obj) {
		System.out.print(obj.toString());
	}

	public static void print(Collection<?> items) {
		for (Object i : items) {
			System.out.print(i.toString() + " ");
		}
	}

	public static void println(Object obj) {
		System.out.println(obj.toString());
	}

	public static void println(Collection<?> items) {
		for (Object i : items) {
			System.out.println(i.toString());
		}
	}

	public static <A> void forEach(List<A> list, Action1<A> action) {
		for (A i : list) {
			action.apply(i);
		}
	}

	public static <A> List<A> filter(List<A> items, Fun1<Boolean, A> fun1) {
		List<A> result = new ArrayList<A>();
		for (A i : items) {
			if (fun1.apply(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <T, A> List<T> mirror(List<A> items, Fun1<T, A> fun1) {
		List<T> result = new ArrayList<T>();
		for (A i : items) {
			result.add(fun1.apply(i));
		}
		return result;
	}

	public static <T, A> T reduce(List<A> items, T initValue, Fun2<T, A, T> fun2) {
		T result = initValue;
		for (A i : items) {
			result = fun2.apply(i, result);
		}
		return result;
	}

}
