package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 */
public class LowestAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node lowestAncestor1(Node head, Node o1, Node o2) {
		if (head == null) {
			return null;
		}
		HashMap<Node, Node> parentMap = new HashMap<>();
		parentMap.put(head, null);
		fillParentMap(head, parentMap);
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
		return cur;
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

	public static Node lowestAncestor2(Node head, Node o1, Node o2) {
		return process(head, o1, o2).lowestAncestor;
	}

	public static class Info {
		// 最低公共祖先
		public Node lowestAncestor;
		// 是否能找到 o1
		public boolean findO1;
		// 是否能找到 o2
		public boolean findO2;

		public Info(Node n, boolean f1, boolean f2) {
			lowestAncestor = n;
			findO1 = f1;
			findO2 = f2;
		}
	}

	public static Info process(Node head, Node o1, Node o2) {
		if (head == null) {
			return new Info(null, false, false);
		}
		Info leftInfo = process(head.left, o1, o2);
		Info rightInfo = process(head.right, o1, o2);
		Node lowestAncestor = null;
		// 如果在左树或右树上找到了 o1，或者 head 就是 o1，那么 findO1 为 true
		boolean findO1 = leftInfo.findO1 || rightInfo.findO1 || head == o1;
		// 如果在左树或右树上找到了 o2，或者 head 就是 o2，那么 findO2 为 true
		boolean findO2 = leftInfo.findO2 || rightInfo.findO2 || head == o2;
		// 如果在左树上找到了最小公共祖先，那么将它赋给 lowestAncestor
		if (leftInfo.lowestAncestor != null) {
			lowestAncestor = leftInfo.lowestAncestor;
		}
		// 如果在右树上找到了最小公共祖先，那么将它赋给 lowestAncestor（与上面的条件必不可能同时成立）
		if (rightInfo.lowestAncestor != null) {
			lowestAncestor = rightInfo.lowestAncestor;
		}
		// 如果在左右子树上都没有找到最小公共祖先，而且 findO1 和 findO2 又为 true，那么头节点就是最小公共祖先
		if (lowestAncestor == null) {
			if (findO1 && findO2) {
				lowestAncestor = head;
			}
		}
		return new Info(lowestAncestor, findO1, findO2);
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

	// for test
	public static Node pickRandomOne(Node head) {
		if (head == null) {
			return null;
		}
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		int randomIndex = (int) (Math.random() * arr.size());
		return arr.get(randomIndex);
	}

	// for test
	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Node o1 = pickRandomOne(head);
			Node o2 = pickRandomOne(head);
			if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
