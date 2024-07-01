package com.bfs;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LadderLengthTop {

	public int ladderLength(String begin, String end, List<String> words) {

		Set<String> primary = new HashSet<>(words);
		if (!primary.contains(end))
			return 0;

		Set<String> forward = new HashSet<String>();
		Set<String> backward = new HashSet<String>();

		forward.add(begin);
		backward.add(end);

		primary.remove(begin);
		primary.remove(end);

		return transform(forward, backward, primary);
	}

	private int transform(Set<String> forward, Set<String> backward,
			Set<String> primary) {

		Set<String> carry = new HashSet<String>();
		for (String fs : forward) {

			char[] word = fs.toCharArray();

			for (int i = 0; i < word.length; ++i) {
				for (int ch = 'a'; ch <= 'z'; ++ch) {// begin = 97 to end = 122

					char temp = word[i];
					word[i] = (char) ch;

					String target = String.valueOf(word);
					if (backward.contains(target))
						return 2;

					if (primary.contains(target) && !forward.contains(target)) {
						primary.remove(target);
						carry.add(target);
					}

					word[i] = temp;
				}
			}
		}

		if (carry.size() == 0)
			return 0;
		forward = carry;

		int result = forward.size() > backward.size()
				? transform(backward, forward, primary)
				: transform(forward, backward, primary);

		return result == 0 ? 0 : result + 1;
	}
}
