package binarytree;

public class RecursiveTraversalBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 先序、中序、后序都可以在递归序的基础上加工出来
	 * 第一次到达一个节点就打印就是先序、第二次到达打印即中序、第三次到达打印即后序
	 * @param head 头节点
	 */
	public static void recursiveOrder(Node head) {
		if (head == null) {
			return;
		}
		recursiveOrder(head.left);
		recursiveOrder(head.right);
	}

	/**
	 * 先序：任何子树的处理顺序都是，先头节点、再左子树、然后右子树
	 * @param head 头节点
	 */
	public static void pre(Node head) {
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		pre(head.left);
		pre(head.right);
	}

	/**
	 * 中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
	 * @param head 头节点
	 */
	public static void in(Node head) {
		if (head == null) {
			return;
		}
		in(head.left);
		System.out.print(head.value + " ");
		in(head.right);
	}

	/**
	 * 中序：任何子树的处理顺序都是，先左子树、再头节点、然后右子树
	 * @param head 头节点
	 */
	public static void pos(Node head) {
		if (head == null) {
			return;
		}
		pos(head.left);
		pos(head.right);
		System.out.print(head.value + " ");

	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println();
		System.out.println("========");
		in(head);
		System.out.println();
		System.out.println("========");
		pos(head);
		System.out.println();
		System.out.println("========");

	}

}
