package linkedlist;

public class AddTwoNumbers {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node addTwoNumbers(Node l1, Node l2) {
        Node l = new Node(0);
        Node p = l1, q = l2, cur = l;
        int carry = 0;
        while (p != null || q != null) {
            int sum = 0;
            if (p != null) {
                sum += p.value;
                p = p.next;
            }
            if (q != null) {
                sum += q.value;
                q = q.next;
            }
            sum += carry;
            carry = sum / 10;
            cur.next = new Node(sum % 10);
            cur = cur.next;
        }
        if (carry != 0) {
            cur.next = new Node(carry);
        }
        return l.next;
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(2);
        head1.next = new Node(4);
        head1.next.next = new Node(3);
        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(4);
        printLinkedList(addTwoNumbers(head1, head2));
    }
}