package com.myuniversaltree;

import java.util.*;

public class Main {

	static class Data {
		int id;
		String name;
		int parentId;

		Data(int id, String name, int parentId) {
			this.id = id;
			this.name = name;
			this.parentId = parentId;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	static class DataNode extends TreeNode<Data, DataNode> {

		@Override
		public String toString() {
			return vertex.name;
		}

	}

	public static void main(String[] args) {
		List<Data> items = new ArrayList<>();

		items.add(new Data(1, "Соки", 0));
		items.add(new Data(2, "Манго", 1));
		items.add(new Data(3, "Виноградный", 1));
		items.add(new Data(4, "Яблочный", 1));
		items.add(new Data(5, "Газировка", 0));
		items.add(new Data(6, "Кола", 5));
		items.add(new Data(7, "Кола 0.5л", 6));
		items.add(new Data(8, "Кола 1.5л", 6));
		items.add(new Data(9, "Минералка", 5));
		items.add(new Data(10, "Лимонад", 5));

		DataNode tree = DataNode.makeTree(items, new TreeNode.TypeAdapter<Data, DataNode>() {

			@Override
			public DataNode newInstance() {
				return new DataNode();
			}

			@Override
			public boolean isSubVertex(Data source, Data vertex) {
				return source.id == vertex.parentId;
			}

			@Override
			public boolean isTopLevel(Data vertex) {
				return vertex.id == 0;
			}

		});

		System.out.println("@@@@@@@@@@@@@@@");
		System.out.println(tree.toString());
	}
}
