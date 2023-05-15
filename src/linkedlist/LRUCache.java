package linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；
 * 如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该逐出最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class LRUCache {
    public static class Node {
        public int key;
        public int value;
        public Node pre;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class DoubleLinkedList {
        public Node head;
        public Node tail;

        // 将节点添加到链表尾部
        public void addNode(Node node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.pre = tail;
            }
            tail = node;
        }

        public Node removeHead() {
            if (head == null) {
                return null;
            }
            Node preHead = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node newHead = head.next;
                newHead.pre = null;
                head.next = null;
                head = newHead;
            }
            return preHead;
        }

        public void moveNodeToTail(Node node) {
            if (node == null || tail == node) {
                return;
            }
            if (head == node) {
                head = node.next;
                head.pre = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            node.pre = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }
    }

    public Map<Integer, Node> nodeMap;
    public int capacity;
    public DoubleLinkedList doubleLinkedList;

    public LRUCache(int capacity) {
        this.nodeMap = new HashMap<>();
        this.capacity = capacity;
        this.doubleLinkedList = new DoubleLinkedList();
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        Node node = nodeMap.get(key);
        doubleLinkedList.moveNodeToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            // 更新节点
            Node node = nodeMap.get(key);
            node.value = value;
            doubleLinkedList.moveNodeToTail(node);
        } else {
            // 插入节点
            Node node = new Node(key, value);
            if (nodeMap.size() == capacity) {
                // 容量满了，移除头节点
                Node head = doubleLinkedList.removeHead();
                nodeMap.remove(head.key);
            }
            // 将节点添加到尾部
            nodeMap.put(key, node);
            doubleLinkedList.addNode(node);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.put(4, 4);
        cache.get(1);
        cache.get(3);
        cache.get(4);
    }
}
