package com.tasks.isaev.recursive.classicbacktracking;

import static org.junit.Assert.assertEquals;
import java.util.*;

import org.junit.jupiter.api.Test;

/*
 * Генная строка может быть представлена ​​8-символьной длинной строкой с возможностью 
 * выбора из 'A', 'C', 'G'и 'T'. 
 * Предположим, нам нужно исследовать мутацию генной строки startGeneв генную строку, 
 * endGeneгде одна мутация определяется как изменение одного символа в генной строке.
 * Например, "AACCGGTT" --> "AACCGGTA"есть одна мутация.
 * Существует также банк генов - bank, в котором регистрируются все действительные 
 * мутации генов. Ген должен присутствовать в bank чтобы сделать 
 * его допустимой генной строкой. 
 * Учитывая две генные строки startGeneи endGeneбанк генов bank, 
 * верните минимальное количество мутаций, необходимых для мутации от 
 * startGene до endGene. Если такой мутации нет, верните -1.
 * Обратите внимание: предполагается, что отправная точка действительна, 
 * поэтому она может не быть включена в банк.
 */

public class MinMutation {

	public int minMutation(String startGene, String endGene, String[] bank) {
		int steps = 0;
		Set<String> set = new HashSet<>();
		LinkedList<String> carry = new LinkedList<>();
		set.add(startGene);
		carry.add(startGene);

		while (!carry.isEmpty()) {

			int size = carry.size();
			for (int i = 1; i <= size; ++i) {

				String current = carry.remove();

				if (current.equals(endGene))
					return steps;

				for (int j = 0; j < bank.length; ++j) {
					String reference = bank[j];
					if (set.contains(reference))
						continue;
					if (isValid(current, reference)) {
						set.add(reference);
						carry.add(reference);						
					}
				}
			}
			++steps;

		}

		return -1;
	}

	public boolean isValid(String current, String reference) {
		int count = 0;
		for (int i = 0; i < 8; ++i) {
			if (current.charAt(i) != reference.charAt(i))
				++count;
		}
		if (count == 1)
			return true;
		return false;
	}

	@Test
	public void test1() {
		String startGene = "AACCGGTT", endGene = "AACCGGTA";
		String[] bank = new String[]{"AACCGGTA"};
		assertEquals(1, minMutation(startGene, endGene, bank));
	}

	@Test
	public void test2() {
		String startGene = "AACCGGTT", endGene = "AAACGGTA";
		String[] bank = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
		assertEquals(2, minMutation(startGene, endGene, bank));
	}
}
