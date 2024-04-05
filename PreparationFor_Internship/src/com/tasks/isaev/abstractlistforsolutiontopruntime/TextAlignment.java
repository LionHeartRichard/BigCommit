package com.tasks.isaev.abstractlistforsolutiontopruntime;

import static org.junit.Assert.assertEquals;

import java.util.*;

import javax.accessibility.AccessibilityProvider;

import org.junit.jupiter.api.Test;

/*
 * Учитывая массив строк wordsи ширину maxWidth, отформатируйте текст так, чтобы каждая строка
 * содержала ровно maxWidthсимволы и была полностью (влево и вправо) выровнена. Вам следует 
 * жадно упаковывать свои слова; то есть упакуйте как можно больше слов в каждую строку. 
 * При необходимости добавьте дополнительные пробелы ' ', чтобы в каждой строке было ровно 
 * столько maxWidth символов. Лишние пробелы между словами должны быть распределены 
 * максимально равномерно. Если количество пробелов в строке не делится поровну между словами, 
 * пустым местам слева будет назначено больше пробелов, чем местам справа. Последняя строка 
 * текста должна быть выровнена по левому краю, между словами не должно быть лишних пробелов. 
 * 
 * Примечание:
 * 
 * Слово определяется как последовательность символов, состоящая только из символов, 
 * не являющихся пробелами. Длина каждого слова гарантированно больше 0 и не превышает maxWidth.
 * Входной массив words содержит хотя бы одно слово.
 */

public class TextAlignment {

	private int maxLetters = 0;

	public List<String> fullJustify(String[] words, int maxWidth) {

		maxLetters = maxWidth;

		List<String> res = new AbstractList<String>() {

			List<String> result = null;

			public String get(int index) {
				if (result == null) {
					result = alignment(words, maxWidth);
				}
				return result.get(index);
			}

			@Override
			public int size() {
				if (result == null) {
					result = alignment(words, maxWidth);
				}
				return result.size();
			}

		};
		return res;
	}

	private List<String> alignment(String[] words, int maxWidth) {

		List<String> currentRes = new ArrayList<String>();

		if (words.length == 1 && words[0].length() == maxWidth) {
			currentRes.add(words[0]);
			return currentRes;
		}

		int count = maxWidth;
		int i = 0, begin = 0, end = -1;

		while (i < words.length) {
			if (count >= words[i].length()) {
				end = i;
				if (count > words[i].length()) {
					count -= words[i].length() + 1;
				} else {
					count = 0;
				}
				if (i == words.length - 1) {
					currentRes.add(countSpace(words, begin, end));
				}
				++i;
			} else {
				if (end != -1) {
					currentRes.add(countSpace(words, begin, end));
				}
				count = maxWidth;
				begin = i;
			}
		}

		return currentRes;
	}

	private String countSpace(String[] words, int begin, int end) {

		int letters = 0, wordCount = end - begin;
		for (int i = begin; i <= end; ++i) {
			letters += words[i].length();
		}

		int count = maxLetters - letters;
		int k = 0;
		if (wordCount != 0) {
			k = count / wordCount;
		}

		if (end == words.length - 1 || end == begin) {
			String endLetters = concatinationEndLetters(words, begin, end,
					count - wordCount);

			System.out.println(endLetters + "|length=" + endLetters.length()
					+ " max=" + maxLetters);

			return endLetters;
		}

		System.out.println("letters = " + letters + " count= " + count
				+ " word= " + wordCount + " k=" + k);

		String currentStr = concat(words, begin, end, wordCount, k, letters);

		System.out.println(currentStr + "|length=" + currentStr.length()
				+ " max=" + maxLetters);

		return currentStr;
	}

	private String concatinationEndLetters(String[] words, int begin, int end,
			int count) {
		StringBuilder builder = new StringBuilder();
		while (count > 0) {
			builder.append(" ");
			--count;
		}
		String space = builder.toString();
		builder = new StringBuilder();

		for (int i = begin; i <= end; ++i) {
			if (i != end) {
				builder.append(words[i]);
				builder.append(" ");
			} else {
				builder.append(words[i]);
				builder.append(space);
			}
		}

		return builder.toString();
	}

	private String concat(String[] words, int begin, int end, int wordCount,
			int k, int letters) {
		StringBuilder builder = new StringBuilder();
		String space = "";

		for (int i = 0; i < k; ++i) {
			builder.append(" ");
		}
		space = builder.toString();
		builder = new StringBuilder();
		int count = maxLetters - letters - (k * (end - begin));

		for (int i = begin; i <= end; ++i) {
			if (i != end) {
				builder.append(words[i]);
				builder.append(space);
				if (count > 0) {
					builder.append(" ");
					--count;
				}
			} else {
				builder.append(words[i]);
			}
		}

		return builder.toString();
	}

	@Test
	public void test0() {
		String[] words = {"word", "word", "word", "word", "wordword"};
		int maxWeigth = 24;
		List<String> expected = Arrays.asList("word   word   word  word",
				"wordword                ");
		List<String> actual = fullJustify(words, maxWeigth);
		assertEquals(expected, actual);
	}

	@Test
	public void test1() {
		String[] words = {"This", "is", "an", "example", "of", "text",
				"justification."};
		int maxWidth = 16;

		List<String> expexted = Arrays.asList("This    is    an",
				"example  of text", "justification.  ");
		List<String> actual = fullJustify(words, maxWidth);
		assertEquals(expexted, actual);
	}

	@Test
	public void test2() {
		String[] words = {"What", "must", "be", "acknowledgment", "shall",
				"be"};
		int maxWidth = 16;

		List<String> expected = Arrays.asList("What   must   be",
				"acknowledgment  ", "shall be        ");
		List<String> actual = fullJustify(words, maxWidth);
		assertEquals(expected, actual);
	}
	@Test
	public void test3() {
		String[] words = {"Science", "is", "what", "we", "understand", "well",
				"enough", "to", "explain", "to", "a", "computer.", "Art", "is",
				"everything", "else", "we", "do"};
		int maxWidth = 20;
		List<String> expected = Arrays.asList("Science  is  what we",
				"understand      well", "enough to explain to",
				"a  computer.  Art is", "everything  else  we",
				"do                  ");
		List<String> actual = fullJustify(words, maxWidth);
		assertEquals(expected, actual);
	}

	@Test
	public void test4() {
		String[] words = {"What", "must", "be", "shall", "be."};
		int maxWeidth = 5;
		List<String> expected = Arrays.asList("What ", "must ", "be   ",
				"shall", "be.  ");
		List<String> actual = fullJustify(words, maxWeidth);
		assertEquals(expected, actual);
	}
}
