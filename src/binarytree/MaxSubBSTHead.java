package binarytree;

import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点 head，
 * 返回这颗二叉树中最大的二叉搜索子树的头节点
 */
public class MaxSubBSTHead {

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

	public static Node maxSubBSTHead1(Node head) {
		if (head == null) {
			return null;
		}
		if (getBSTSize(head) != 0) {
			return head;
		}
		Node leftAns = maxSubBSTHead1(head.left);
		Node rightAns = maxSubBSTHead1(head.right);
		return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
	}

	public static Node maxSubBSTHead(Node head) {
		if (head == null) {
			return null;
		}
		return process(head).maxSubBSTHead;
	}

	public static class Info {
		// 最大二叉搜索子树头节点
		public Node maxSubBSTHead;
		// 最大二叉搜索子树节点数
		public int maxSubBSTSize;
		// 子树节点最小值
		public int min;
		// 子树节点最大值
		public int max;

		public Info(Node h, int size, int mi, int ma) {
			maxSubBSTHead = h;
			maxSubBSTSize = size;
			min = mi;
			max = ma;
		}
	}

	public static Info process(Node head) {
		if (head == null) {
			return null;
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		int maxSubBSTSize = 0;
		int min = head.value;
		int max = head.value;
		Node maxSubBSTHead = null;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
			max = Math.max(max, leftInfo.max);
			maxSubBSTHead = leftInfo.maxSubBSTHead;
			maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
			max = Math.max(max, rightInfo.max);
			// 如果右树中的最大二叉搜索子树大小更大，更新 maxSubBSTHead 和 maxSubBSTSize
			if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
				maxSubBSTHead = rightInfo.maxSubBSTHead;
				maxSubBSTSize = rightInfo.maxSubBSTSize;
			}
		}
		// 通过子树上的最大二叉搜索子树头节点是否就是子树头节点就能判断该子树整体是否为二叉搜索树
		if ((leftInfo == null || (leftInfo.maxSubBSTHead == head.left && head.value > leftInfo.max)) &&
				(rightInfo == null || rightInfo.maxSubBSTHead == head.right && head.value < rightInfo.min)) {
			maxSubBSTHead = head;
			maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
					(rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
		}
		return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
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
			if (maxSubBSTHead1(head) != maxSubBSTHead(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
