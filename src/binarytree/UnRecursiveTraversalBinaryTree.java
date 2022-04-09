package binarytree;

import java.util.Stack;

/**
 * 1）任何递归函数都可以改成非递归
 * 2）通过设计压栈的过程来实现
 */
public class UnRecursiveTraversalBinaryTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			// 头节点入栈，保证之后每次都先将头节点弹出
			stack.push(head);
			while (!stack.empty()) {
				Node cur = stack.pop();
				System.out.print(cur.value + " ");
				// 先将右节点入栈，再将左节点入栈，保证出栈时为先左后右
				if (cur.right != null) {
					stack.push(cur.right);
				}
				if (cur.left != null) {
					stack.push(cur.left);
				}
			}
		}
		System.out.println();
	}

	public static void in(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<>();
			while (!stack.empty() || head != null) {
				if (head != null) {
					// 整条左边界依次入栈
					stack.push(head);
					head = head.left;
				} else {
					// 弹出打印，确保先打印左节点
					Node pop = stack.pop();
					System.out.print(pop.value + " ");
					// 来到右树，如果右树为空，那么相当于回到该子树的头节点打印；否则进入右树打印。这样就保证了左->头->右的中序打印顺序
					head = pop.right;
				}
			}
		}
		System.out.println();
	}

	public static void posWithTwoStack(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			// 临时栈，用于构建 stack2
			Stack<Node> stack1 = new Stack<>();
			// 以根->右->左的顺序入栈，用于完成左->右->根的后序打印
			Stack<Node> stack2 = new Stack<>();
			stack1.push(head);
			while (!stack1.empty()) {
				Node cur = stack1.pop();
				// 根节点入栈
				stack2.push(cur);
				// 先入左节点，再入右节点，以便在出栈时以先右后左的顺序压入 stack2 中
				if (cur.left != null) {
					stack1.push(cur.left);
				}
				if (cur.right != null) {
					stack1.push(cur.right);
				}
			}
			// stack2 中的节点依次出栈完成后序打印
			while (!stack2.empty()) {
				System.out.print(stack2.pop().value + " ");
			}
		}
		System.out.println();
	}

	public static void pos(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<>();
			stack.push(h);
			Node c;
			while (!stack.empty()) {
				// 当前到达的节点
				c = stack.peek();
				if (c.left != null && c.left != h && c.right != h) {
					// 如果左树不为空而且上次打印的节点不是当前节点的左/右节点，说明左树还没处理，左节点入栈
					stack.push(c.left);
				} else if (c.right != null && c.right != h) {
					// 如果右树不为空而且上次打印的节点不是当前节点的右节点，说明右树还没处理，右节点入栈
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					// 使用 h 记录上一次打印的节点
					h = c;
				}
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

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		posWithTwoStack(head);
		System.out.println("========");
		pos(head);
		System.out.println("========");
	}

}
