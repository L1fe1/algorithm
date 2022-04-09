package binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * 1）可以用先序或者中序或者后序或者按层遍历，来实现二叉树的序列化
 * 2）用了什么方式序列化，就用什么样的方式反序列化
 */
public class SerializeAndReconstructTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Queue<String> preSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		pres(head, ans);
		return ans;
	}

	public static void pres(Node head, Queue<String> ans) {
		if (head == null) {
			ans.add(null);
		} else {
			ans.add(String.valueOf(head.value));
			pres(head.left, ans);
			pres(head.right, ans);
		}
	}

	public static Node buildByPreQueue(Queue<String> preQueue) {
		if (preQueue == null || preQueue.size() == 0) {
			return null;
		}
		return preb(preQueue);
	}

	public static Node preb(Queue<String> preQueue) {
		String value = preQueue.poll();
		if (value == null) {
			return null;
		}
		Node head = new Node(Integer.parseInt(value));
		head.left = preb(preQueue);
		head.right = preb(preQueue);
		return head;
	}

	public static Queue<String> levelSerial(Node head) {
		Queue<String> ans = new LinkedList<>();
		if (head == null) {
			ans.add(null);
		} else {
			// 添加头节点
			ans.add(String.valueOf(head.value));
			Queue<Node> queue = new LinkedList<>();
			queue.add(head);
			// 层序遍历
			while (!queue.isEmpty()) {
				Node cur = queue.poll();
				// 在节点加入队列时顺便将它加到序列化结果中去（不要忽略空）
				if (cur.left != null) {
					queue.add(cur.left);
					ans.add(String.valueOf(cur.left.value));
				} else {
					ans.add(null);
				}
				if (cur.right != null) {
					queue.add(cur.right);
					ans.add(String.valueOf(cur.right.value));
				} else {
					ans.add(null);
				}
			}
		}
		return ans;
	}

	public static Node buildByLevelQueue(Queue<String> levelQueue) {
		if (levelQueue == null || levelQueue.size() == 0) {
			return null;
		}
		Queue<Node> queue = new LinkedList<>();
		Node head = generateNode(levelQueue.poll());
		if (head != null) {
			queue.add(head);
		}
		Node cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			cur.left = generateNode(levelQueue.poll());
			cur.right = generateNode(levelQueue.poll());
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
		return head;
	}

	public static Node generateNode(String val) {
		if (val == null) {
			return null;
		}
		return new Node(Integer.parseInt(val));
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
	public static boolean isSameValueStructure(Node head1, Node head2) {
		if (head1 == null && head2 != null) {
			return false;
		}
		if (head1 != null && head2 == null) {
			return false;
		}
		if (head1 == null && head2 == null) {
			return true;
		}
		if (head1.value != head2.value) {
			return false;
		}
		return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
	}

	public static void main(String[] args) {
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			Queue<String> pre = preSerial(head);
			Queue<String> level = levelSerial(head);
			Node preBuild = buildByPreQueue(pre);
			Node levelBuild = buildByLevelQueue(level);
			if (!isSameValueStructure(preBuild, levelBuild)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
