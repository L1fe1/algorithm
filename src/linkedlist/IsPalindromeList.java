package linkedlist;

import java.util.Stack;

/**
 * 给定一个单链表的头节点 head，请判断该链表是否为回文结构。
 */
public class IsPalindromeList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isPalindromeWithStack(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 借助栈先进后出的特性判断是否为回文
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean isPalindromeWithStackBetter(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 借助快慢指针将空间复杂度降为第一种方法的一半
        Stack<Node> stack = new Stack<>();
        Node cur = slow.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            // 借助栈先进后出的特性判断是否为回文
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean isPalindromeWithReverseList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head, n2 = head;
        Node n3 = null;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // 走到中点，然后反转后半部分链表
        n2 = n1.next;
        n1.next = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        // 记录链表最后一个节点
        n3 = n1;
        n2 = head;
        // 判断是否为回文
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 还原链表
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindromeWithStack(head) + " | ");
        System.out.print(isPalindromeWithStackBetter(head) + " | ");
        System.out.println(isPalindromeWithReverseList(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
