package binarytree;

/**
 * 如何设计一个打印整棵树的打印函数
 */
public class PrintBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		// height 树高，to 节点标识，len 打印长度，节点 value 长度不够补空格
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	// 水平打印整棵树
	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		// 最上层打印右子树
		printInOrder(head.right, height + 1, "v", len);
		// 打印的实际内容
		String val = to + head.value + to;
		int leftLen = (len - val.length()) / 2;
		int rightLen = len - val.length() - leftLen;
		// 左右填充空格到 len 长度
		val = getSpace(leftLen) + val + getSpace(rightLen);
		// 填充对应高度所需的空格并打印
		System.out.println(getSpace(height * val.length()) + val);
		// 最下层打印左子树
		printInOrder(head.left, height + 1, "^", len);
	}

	// 获得需要填充的空格
	public static String getSpace(int num) {
		StringBuilder res = new StringBuilder(num);
		while (num > 0) {
			res.append(" ");
			num --;
		}
		return res.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);

		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);

	}

}
