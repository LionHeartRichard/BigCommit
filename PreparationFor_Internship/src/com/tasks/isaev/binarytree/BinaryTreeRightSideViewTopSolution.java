package com.tasks.isaev.binarytree;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class BinaryTreeRightSideViewTopSolution {

	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		backtracking(root, 0, res);

		return res;
	}

	private void backtracking(TreeNode node, int i, List<Integer> res) {
		if (node == null)
			return;

		if (i == res.size())
			res.add(node.val);

		backtracking(node.right, i + 1, res);

		// !!!!!!!!!!!!!!!!!!!
		backtracking(node.left, i + 1, res);
		// этот шаг необходим только в том случае если у нас есть только один
		// левый потомок
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
