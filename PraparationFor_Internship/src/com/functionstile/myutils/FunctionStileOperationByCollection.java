package com.functionstile.myutils;

import java.util.*;
import com.functionstile.inter.*;

public class FunctionStileOperationByCollection {

	public static void println(Collection<?> items) {
		for (Object i : items) {
			System.out.println(i.toString());
		}
	}

	public static void println(Object item) {
		System.out.println(item.toString());
	}

	public static <A> void forEach(List<A> list, Action1<A> function) {
		for (A i : list) {
			function.apply(i);
		}
	}

	public static <T, A> List<T> mirror(List<A> list, Fun1<T, A> mirrorFunction) {
		List<T> result = new ArrayList<T>();
		for (A i : list) {
			result.add(mirrorFunction.apply(i));
		}
		return result;
	}

	public static <T, A> T mirror(A item, Fun1<T, A> mirrorFunction) {
		return mirrorFunction.apply(item);
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filterFunction) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filterFunction.apply(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.apply(i) && filter2.apply(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filter(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.apply(i) && filter2.apply(i) && filter3.apply(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterOr(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.apply(i) || filter2.apply(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> List<A> filterOr(List<A> list, Fun1<Boolean, A> filter1, Fun1<Boolean, A> filter2,
			Fun1<Boolean, A> filter3) {
		List<A> result = new ArrayList<A>();
		for (A i : list) {
			if (filter1.apply(i) || filter2.apply(i) || filter3.apply(i)) {
				result.add(i);
			}
		}
		return result;
	}

	public static <A> A find(List<A> list, Fun1<Boolean, A> findFunction) {
		for (A i : list) {
			if (findFunction.apply(i)) {
				return i;
			}
		}
		return null;
	}

	public static <T, A> T reduce(List<A> list, T initializeValue, Fun2<T, A, T> reduceFunction) {
		T result = initializeValue;
		for (A i : list) {
			result = reduceFunction.apply(i, result);
		}
		return result;
	}
}
