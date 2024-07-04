package com.tasks.isaev.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.util.*;

// Имея на входе главный узел дерева вернуть правую сторону дерева

public class BinaryTreeRightSideView {

	public List<Integer> rightSideView(TreeNode root) {

		List<Integer> result = new ArrayList<Integer>();

		if (root == null)
			return result;

		Queue<TreeNode> que = new ArrayDeque<TreeNode>();
		que.offer(root);

		int size = que.size();
		while (size != 0) {
			size = que.size();
			for (int i = 0; i < size; ++i) {
				TreeNode current = que.poll();
				if (i == size - 1)
					result.add(current.val);
				if (current.left != null)
					que.offer(current.left);
				if (current.right != null)
					que.offer(current.right);
			}
		}

		return result;
	}

	@Test
	public void test1() {
		TreeNode subLeftLeft = new TreeNode();
		TreeNode subLeftRight = new TreeNode(5);
		TreeNode subRightLeft = new TreeNode();
		TreeNode subRightRight = new TreeNode(4);
		TreeNode left = new TreeNode(2, subLeftLeft, subLeftRight);
		TreeNode right = new TreeNode(3, subRightLeft, subRightRight);
		TreeNode root = new TreeNode(1, left, right);

		List<Integer> actual = rightSideView(root);

		Integer[] output = {1, 3, 4};
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(output));

		assertEquals(expected, actual);
	}
}
