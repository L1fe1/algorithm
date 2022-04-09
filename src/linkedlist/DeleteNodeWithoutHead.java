package linkedlist;

/**
 * 能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉？
 */
public class DeleteNodeWithoutHead {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void deleteNodeWithoutHead(Node node) {
        /**
         * 将下一个节点的值覆盖到当前节点，然后删除下一个节点
         * 这样做其实并没有删除当前节点，这样做有几个坏处：
         * 1.如果此处的节点是服务器，需要下线服务器，导致下一台正常提供服务器下掉了
         * 2.如果在现实中节点中的值特别复杂，连拷贝函数和构造函数都无法调用，那就无法完成了
         * 3.这种方式永远无法删除链表中最后一个节点
         */
        Node next = node.next;
        node.value = next.value;
        node.next = next.next;
    }
}
