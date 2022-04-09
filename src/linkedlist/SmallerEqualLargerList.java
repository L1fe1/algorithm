package linkedlist;

/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class SmallerEqualLargerList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartitionByArr(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        // 记录链表节点数
        int size = 0;
        while (cur != null) {
            size ++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[size];
        cur = head;
        // 将链表中的节点存入数组中
        for (int i = 0; i < nodeArr.length; i ++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        // 对数组进行分区
        arrPartition(nodeArr, pivot);
        int i;
        for (i = 1; i < nodeArr.length; i ++) {
            // 连接链表
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        // smallIndex：记录小于区右边的位置，largeIndex：记录大于区左边的位置
        int smallIndex = -1, index = 0, largeIndex = nodeArr.length;
        while (index != largeIndex) {
            if (nodeArr[index].value < pivot) {
                // 将 < pivot 的节点放在小于区的最右边
                swap(nodeArr, ++ smallIndex, index ++);
            } else if (nodeArr[index].value == pivot) {
                index ++;
            } else {
                // 将 > pivot 的节点放在大于区的最左边
                swap(nodeArr, -- largeIndex, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static Node listPartitionByLink(Node head, int pivot) {
        /**
         * 小于区（< pivot）头部 sH，尾部 sT
         * 等于区（= pivot）头部 eH，尾部 eT
         * 大于区（> pivot）头部 lH，尾部 lT
         */
        Node sH = null, sT = null, eH = null, eT = null, lH = null, lT = null;
        Node cur = head;
        while (cur != null) {
            cur = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                } else {
                    sT.next = head;
                }
                sT = head;
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                } else {
                    eT.next = head;
                }
                eT = head;
            } else {
                if (lH == null) {
                    lH = head;
                } else {
                    lT.next = head;
                }
                lT = head;
            }
            head = cur;
        }
        // 如果小于区存在，将小于区尾部与等于区头部相连
        if (sH != null) {
            sT.next = eH;
            // 如果等于区不存在，将小于区尾部视作等于区尾部
            eT = eT == null ? sT : eT;
        }
        // 如果等于区存在，将等于区尾部和大于区头部相连
        if (eT != null) {
            eT.next = lH;
        }
        return sH != null ? sH : (eH != null ? eH : lH);
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(5);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartitionByArr(head1, 5);
        head1 = listPartitionByLink(head1, 5);
        printLinkedList(head1);

    }
}
