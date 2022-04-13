package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中是不是完全二叉树
 */
public class IsCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isCBT11(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// 标识是否遇到了左右孩子不双全的节点，如果遇到了，那么后续所有节点都必须是叶子结点
		boolean shouldLeaf = false;
		// 层序遍历
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			// 如果当前节点右孩子不为空而左孩子为空，那么必不是完全二叉树
			if (cur.right != null && cur.left == null) {
				return false;
			}
			// 如果遇到了左右孩子不双全的节点而且当前节点不是叶子节点，那么必不是完全二叉树
			if (shouldLeaf && cur.left != null) {
				return false;
			}
			// 如果遇到了左右孩子不双全的节点，将 shouldLeaf 标识为 true
			if (cur.left == null || cur.right == null) {
				shouldLeaf = true;
			}
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return true;
	}

	public static boolean isCBT1(Node head) {
		if (head == null) {
			return true;
		}
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
				// 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
					(leaf && !(l == null && r == null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	public static boolean isCBT2(Node head) {
		if (head == null) {
			return true;
		}
		return process(head).isCBT;
	}

	public static Info process(Node head) {
		if (head == null) {
			return new Info(true, true, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		// 左树为满，右树也为满，且左右两树高度一致，那么整树为满
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		boolean isCBT = false;
		// 出现如下三种情况，那么该树为完全二叉树
		// 1.满二叉树必是完全二叉树
		// 2.左树是完全二叉树，右树是满二叉树，且左树高度比右树高度大 1，那么是完全二叉树
		// 3.左树是满二叉树，右树是完全二叉树，且左右两树高度一致，那么是完全二叉树
		if (isFull || (leftInfo.isCBT && rightInfo.isFull &&
				leftInfo.height == rightInfo.height + 1)  ||
				(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height)) {
			isCBT = true;
		}
		return new Info(isFull, isCBT, height);
	}

	public static class Info {
		// 是否为满二叉树
		public boolean isFull;
		// 是否为完全二叉树
		public boolean isCBT;
		// 树高
		public int height;

		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT2(head)) {
				isCBT2(head);
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
