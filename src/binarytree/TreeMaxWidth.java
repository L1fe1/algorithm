package binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求二叉树的最大宽度
 */
public class TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxWidthUseMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		// 节点 -> 节点所在层数 map
		Map<Node, Integer> levelMap = new HashMap<>();
		queue.add(head);
		levelMap.put(head, 1);
		// 当前层数
		int curLevel = 1;
		// 当前层的节点数
		int curLevelNodes = 0;
		int max = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			// 获取当前节点所在层数
			int curNodeLevel = levelMap.get(cur);
			// 记录左右子节点层数
			if (cur.left != null) {
				queue.add(cur.left);
				levelMap.put(cur.left, curNodeLevel + 1);
			}
			if (cur.right != null) {
				queue.add(cur.right);
				levelMap.put(cur.right, curNodeLevel + 1);
			}
			if (curNodeLevel == curLevel) {
				// 统计当前层节点数
				curLevelNodes ++;
			} else {
				// 来到新层，开始结算
				max = Math.max(max, curLevelNodes);
				curLevel ++;
				curLevelNodes = 1;
			}
		}
		// 统计最后一层节点数
		max = Math.max(max, curLevelNodes);
		return max;
	}

	public static int maxWidthNoMap(Node head) {
		if (head == null) {
			return 0;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		// 当前层最后一个节点
		Node curLevelEnd = head;
		// 下一层最后一个节点
		Node nextLevelEnd = null;
		int curLevelNodes = 0;
		int max = 0;
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			// 更新下一层最后一个节点
			if (cur.left != null) {
				queue.add(cur.left);
				nextLevelEnd = cur.left;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextLevelEnd = cur.right;
			}
			// 统计当前层节点数
			curLevelNodes ++;
			if (cur == curLevelEnd) {
				// 节点为当前层最后一个节点，开始结算
				max = Math.max(max, curLevelNodes);
				// 下一次遍历会走到新层，更新新层的信息（最后一个节点、节点数）
				curLevelEnd = nextLevelEnd;
				curLevelNodes = 0;
			}
		}
		return max;
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
		int maxLevel = 10;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");

	}

}
