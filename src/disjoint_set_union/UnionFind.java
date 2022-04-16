package disjoint_set_union;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFind {

	public static class Node<V> {
		V value;

		public Node(V v) {
			value = v;
		}
	}

	public static class UnionSet<V> {
		// value -> node 节点信息
		public HashMap<V, Node<V>> nodes;
		// node -> parent node 节点的父节点信息
		public HashMap<Node<V>, Node<V>> parents;
		// head node -> size 节点集合大小
		public HashMap<Node<V>, Integer> sizeMap;

		public UnionSet(List<V> values) {
			values.forEach(value -> {
				Node<V> node = new Node<>(value);
				nodes.put(value, node);
				parents.put(node, node);
				sizeMap.put(node, 1);
			});
		}

		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> stack = new Stack<>();
			// 通过 parents 找到父（代表）节点
			while (cur != parents.get(cur)) {
				stack.push(cur);
				cur = parents.get(cur);
			}
			// 将节点路径上的所有节点的父指向找到的父节点
			while (!stack.isEmpty()) {
				parents.put(stack.pop(), cur);
			}
			return cur;
		}

		public boolean isSameSet(V a, V b) {
			// 节点 a 或 b 未注册，返回 false
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return false;
			}
			// 代表节点相同说明是同一个集合
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}

		public void union(V a, V b) {
			// 节点 a 或 b 未注册，返回
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return;
			}
			Node<V> aHead = findFather(nodes.get(a));
			Node<V> bHead = findFather(nodes.get(b));
			// 如果节点不在一个集合里，进行合并
			if (aHead != bHead) {
				int aSize = sizeMap.get(a);
				int bSize = sizeMap.get(b);
				// 将小集合挂在大集合下面
				if (aSize >= bSize) {
					parents.put(bHead, aHead);
					// 更新集合大小
					sizeMap.put(aHead, aSize + bSize);
					// 移除不再是代表节点的元素
					sizeMap.remove(bHead);
				} else {
					parents.put(aHead, bHead);
					sizeMap.put(bHead, aSize + bSize);
					sizeMap.remove(aHead);
				}
			}
		}
	}

}
