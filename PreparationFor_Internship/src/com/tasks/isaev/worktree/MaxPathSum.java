package com.tasks.isaev.worktree;

/*
 * Путь в бинарном дереве — это последовательность узлов, 
 * в которой каждая пара соседних узлов в последовательности имеет соединяющее их ребро. 
 * Узел может появиться в последовательности не более одного раза. 
 * Обратите внимание, что путь не обязательно должен проходить через корень.
 * Сумма путей пути — это сумма значений узлов в пути.
 * Учитывая root двоичное дерево, верните максимальную сумму путей любого непустого пути .
 */

public class MaxPathSum {

	private int maxSum;

	public int maxPathSum(TreeNode root) {
		maxSum = Integer.MIN_VALUE;
		backtracking(root);
		return maxSum;
	}

	private int backtracking(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int leftSum = Math.max(0, backtracking(node.left));
		int rightSum = Math.max(0, backtracking(node.right));

		maxSum = Math.max(leftSum + rightSum + node.val, maxSum);

		return Math.max(leftSum, rightSum) + node.val;
	}

}
