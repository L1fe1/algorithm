package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
 * 返回整棵二叉树的最大距离
 */
public class MaxDistance {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance1(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = getPreList(head);
		HashMap<Node, Node> parentMap = getParentMap(head);
		int max = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
			}
		}
		return max;
	}

	public static ArrayList<Node> getPreList(Node head) {
		ArrayList<Node> arr = new ArrayList<>();
		fillPreList(head, arr);
		return arr;
	}

	public static void fillPreList(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPreList(head.left, arr);
		fillPreList(head.right, arr);
	}

	public static HashMap<Node, Node> getParentMap(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		map.put(head, null);
		fillParentMap(head, map);
		return map;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
		HashSet<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		Node lowestAncestor = cur;
		cur = o1;
		int distance1 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance1++;
		}
		cur = o2;
		int distance2 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance2++;
		}
		return distance1 + distance2 - 1;
	}

	public static int maxDistance(Node head) {
		return process(head).maxDistance;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}
	}

	public static Info process(Node head) {
		if (head == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		// 有两种情况：
		// 1. maxDistance 与 head 无关，那么 maxDistance 为左右子树中 maxDistance 的较大值
		// 2. maxDistance 与 head 有关，那么 maxDistance 为左树的高度 + 右树的高度 + 1（算上 head）
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height + 1) ;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info(maxDistance, height);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxDistance1(head) != maxDistance(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
