package com.tasks.isaev.worktree;

/*
 * Учитывая rootдвоичное дерево, верните его максимальную глубину. 
 * Максимальная глубина двоичного дерева  — это количество узлов на самом длинном пути 
 * от корневого узла до самого дальнего листового узла.
 */

public class CountDepthForTreeNode {

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftCount = maxDepth(root.left);
		int rightCount = maxDepth(root.right);
		return Math.max(leftCount, rightCount) + 1;
	}
}
