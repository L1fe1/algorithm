package binarytree;

import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的大小
 */
public class MaxSubBSTSize {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int getBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return 0;
			}
		}
		return arr.size();
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	public static int maxSubBSTSize1(Node head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
	}

	public static int maxSubBSTSize(Node head) {
		if (head == null) {
			return 0;
		}
		return process(head).maxSubBSTSize;
	}

	public static class Info {
		// 是否为二叉搜索树
		public boolean isBST;
		// 最大二叉搜索子树的大小
		public int maxSubBSTSize;
		// 树中节点最小值
		public int min;
		// 树中节点最大值
		public int max;

		public Info(boolean isBST, int maxSubBSTSize, int min, int max) {
			this.isBST = isBST;
			this.maxSubBSTSize = maxSubBSTSize;
			this.min = min;
			this.max = max;
		}
	}

	public static Info process(Node head) {
		if (head == null) {
			return null;
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int max = head.value;
		int min = head.value;
		int maxSubBSTSize = 0;
		if (leftInfo != null) {
			max = Math.max(max, leftInfo.max);
			min = Math.min(min, leftInfo.min);
			maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
		}
		if (rightInfo != null) {
			max = Math.max(max, rightInfo.max);
			min = Math.min(min, rightInfo.min);
			maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
		}
		boolean isBST = false;
		// 有两种情况：
		// 1. maxSubBSTSize 与 head 无关，那么 maxSubBSTSize 为左右子树 maxSubBSTSize 的较大值
		// 2. maxSubBSTSize 与 head 有关，那么必然整棵树都为二叉搜索树， maxSubBSTSize 为左树的 maxSubBSTSize + 右树的 maxSubBSTSize + 1
		if ((leftInfo == null || (leftInfo.isBST && leftInfo.max < head.value)) &&
				(rightInfo == null || (rightInfo.isBST && rightInfo.min > head.value))) {
			isBST = true;
			maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
					(rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
		}
		return new Info(isBST, maxSubBSTSize,min, max);
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
			if (maxSubBSTSize1(head) != maxSubBSTSize(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
