package binarytree;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
 */
public class IsBalanced {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanced1(Node head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	public static int process1(Node head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public static boolean isBalanced(Node head) {
		return process(head).isBalanced;
	}

	public static class Info {
		// 是否平衡
		public boolean isBalanced;
		// 树的高度
		public int height;

		public Info(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}

	public static Info process(Node head) {
		if (head == null) {
			// 空树是平衡的，树高为 0
			return new Info(true, 0);
		}
		Info leftInfo = process(head.left);
		Info rightInfo = process(head.right);
		// 只有该节点的左树和有树都是平衡的且左右子树高度差不超过1，以当前节点为头的树才是平衡的
		boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced &&
				Math.abs(leftInfo.height - rightInfo.height) <= 1;
		// 当前节点的树高为最右子树高度中较大的值 + 1（算上当前节点）
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info(isBalanced, height);
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
			if (isBalanced1(head) != isBalanced(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
