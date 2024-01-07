package com.myuniversaltree;

import java.util.*;
import static com.myutil.UtilFunctionClassForCollection.*;
import com.functional.*;

public class TreeNode<T, N extends TreeNode<T, N>> {
	T vertex;
	List<N> nodes;
	N source;

	public TreeNode() {
		nodes = new ArrayList<N>();
	}

	protected interface TypeAdapter<T, N> {
		N newInstance();

		boolean isSubVertex(T source, T vertex);

		boolean isTopLevel(T vertex);
	}

	public static <T, N extends TreeNode<T, N>> N makeTree(List<T> list, TypeAdapter<T, N> typeAdapter) {
		N root = typeAdapter.newInstance();
		root.nodes = new ArrayList<N>();
		for (T top : filter(list, typeAdapter::isTopLevel)) {
			root.nodes.add(extractNode(top, root, list, typeAdapter));
		}
		return root;
	}

	protected static <N extends TreeNode<T, N>, T> N extractNode(T currentVertex, N sourceNode, List<T> list,
			TypeAdapter<T, N> typeAdapter) {
		N node = typeAdapter.newInstance();
		node.source = sourceNode;
		node.vertex = currentVertex;
		List<T> subVertices = filter(list, v -> typeAdapter.isSubVertex(currentVertex, v));
		if (!subVertices.isEmpty()) {
			for (T v : subVertices) {
				node.nodes.add(extractNode(v, node, list, typeAdapter));
			}
		}
		return node;
	}

	public <R> R reduce(Fun2<R, N, R> reduceFunction, R initValue) {
		R result = reduceFunction.apply((N) this, initValue);
		if (nodes != null) {
			for (N n : nodes) {
				result = n.reduce(reduceFunction, result);
			}
		}
		return result;
	}
}
