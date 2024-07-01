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

	public int minMutation(String start, String end, String[] bank) {

		int n = bank.length;
		int mask = 0;
		int steps = 0;
		Queue<Integer> qq = new LinkedList<>();
		qq.add(-1);
		while (!qq.isEmpty()) {
			int size = qq.size();
			while (size-- > 0) {
				int front = qq.poll();
				String str = front == -1 ? start : bank[front];
				if (str.equals(end))
					return steps;
				for (int i = 0; i < n; i++) {
					if (((mask >> i) & 1) == 1)
						continue;
					int cnt = 0;
					for (int ii = 0; ii < 8; ii++)
						if (str.charAt(ii) != bank[i].charAt(ii))
							cnt++;
					if (cnt == 1) {
						qq.add(i);
						mask |= (1 << i);
					}
				}
			}
			steps++;
		}
		return -1;
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
