package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTraversalBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> ans = new ArrayList<>();
		if (root != null) {
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			int nodes;
			while (!queue.isEmpty()) {
				// 记录当前层中的节点数
				nodes = queue.size();
				List<Integer> levelNodes = new ArrayList<>();
				// 将当前层中的所有节点加入到 levelNodes 中，并将下一层的节点加入到 queue 中
				while (nodes > 0) {
					Node cur = queue.poll();
					if (cur.left != null) {
						queue.add(cur.left);
					}
					if (cur.right != null) {
						queue.add(cur.right);
					}
					levelNodes.add(cur.value);
					nodes --;
				}
				ans.add(levelNodes);
			}
		}
		return ans;
	}

	public static void level(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.print(cur.value + " ");
			// 利用队列先进先出的特性，将节点按层从左到右打印
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		level(head);
		System.out.println("========");
	}

}
