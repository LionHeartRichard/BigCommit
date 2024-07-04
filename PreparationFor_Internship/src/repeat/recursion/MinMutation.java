package repeat.recursion;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.*;

public class MinMutation {

	/*
	 * Генная строка может быть представлена ​​8-символьной длинной строкой с
	 * возможностью выбора из 'A', 'C', 'G'и 'T'. Предположим, нам нужно
	 * исследовать мутацию генной строки startGeneв генную строку, endGeneгде
	 * одна мутация определяется как изменение одного символа в генной строке.
	 * Например, "AACCGGTT" --> "AACCGGTA"есть одна мутация. Существует также
	 * банк генов - bank, в котором регистрируются все действительные мутации
	 * генов. Ген должен присутствовать в bank чтобы сделать его допустимой
	 * генной строкой. Учитывая две генные строки startGeneи endGeneбанк генов
	 * bank, верните минимальное количество мутаций, необходимых для мутации от
	 * startGene до endGene. Если такой мутации нет, верните -1. Обратите
	 * внимание: предполагается, что отправная точка действительна, поэтому она
	 * может не быть включена в банк.
	 */

	public int minMutation(String begin, String end, String[] bank) {
		Set<String> primary = new HashSet<>(Arrays.asList(bank));
		if (!primary.contains(end)) {
			return -1;
		}

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
				for (char ch = 'A'; ch <= 'Z'; ++ch) {

					char temp = word[i];
					word[i] = (char) ch;
					String target = String.valueOf(word);

					if (backward.contains(target))
						return 1;

					if (primary.contains(target) && !forward.contains(target)) {
						primary.remove(target);
						carry.add(target);
					}

					word[i] = temp;
				}
			}
		}

		if (carry.size() == 0)
			return -1;
		forward = carry;

		int result = forward.size() > backward.size()
				? transform(backward, forward, primary)
				: transform(forward, backward, primary);
		return result == -1 ? -1 : result + 1;
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

	@Test
	public void test3() {
		String startGene = "AACCTTGG", endGene = "AATTCCGG";
		String[] bank = new String[]{"AATTCCGG", "AACCTGGG", "AACCCCGG",
				"AACCTACC"};
		int result = minMutation(startGene, endGene, bank);
		assertEquals(-1, result);
	}

}
