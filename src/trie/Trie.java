package trie;

import java.util.Arrays;

public class Trie {
    public static class Node {
        // 该节点通过了几次
        public int pass;
        // 有多少个字符串，以该节点结尾
        public int end;
        // 孩子节点
        public Node[] children;

        public Node() {
            pass = 0;
            end = 0;
            children = new Node[26];
        }
    }

    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        char[] chars = word.toCharArray();
        int node;
        Node cur = root;
        // root pass 加 1
        cur.pass ++;
        for (char c : chars) {
            // 节点值（int）
            node = c - 'a';
            // 如果节点不存在，建出节点
            if (cur.children[node] == null) {
                cur.children[node] = new Node();
            }
            // 节点 pass 加 1
            cur = cur.children[node];
            cur.pass++;
        }
        // 最后一个节点 end 加 1
        cur.end ++;
    }

    public void erase(String word) {
        // 如果前缀数中包含 word
        if (countWordsEqualTo(word) != 0) {
            char[] chars = word.toCharArray();
            int node;
            Node cur = root;
            // root pass 减 1
            cur.pass --;
            for (char c : chars) {
                node = c - 'a';
                // 节点 pass 减 1，如果节点 pass 为 0，将节点置空，它的所有孩子节点都会被自动回收掉
                if (--cur.children[node].pass == 0) {
                    cur.children[node] = null;
                    return;
                }
                cur = cur.children[node];
            }
            // word 最后一个节点 end 减 1
            cur.end --;
        }
    }

    public int countWordsEqualTo(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        char[] chars = word.toCharArray();
        int node;
        Node cur = root;
        for (char c : chars) {
            // 节点值（int）
            node = c - 'a';
            // 如果节点不存在，说明前缀树中不包含 word
            if (cur.children[node] == null) {
                return 0;
            }
            cur = cur.children[node];
        }
        // word 中最后一个节点的 end 值即为 word 出现的次数
        return cur.end;
    }

    public int countWordsStartingWith(String pre) {
        if (pre == null || pre.length() == 0) {
            return 0;
        }
        char[] chars = pre.toCharArray();
        int node;
        Node cur = root;
        for (char c : chars) {
            // 节点值（int）
            node = c - 'a';
            // 如果节点不存在，说明前缀树中不包含 pre
            if (cur.children[node] == null) {
                return 0;
            }
            cur = cur.children[node];
        }
        // pre 中最后一个节点的 pass 值即为 pre 出现的次数
        return cur.pass;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("acd");
        trie.insert("ace");
        trie.insert("bcd");
        trie.insert("bcfg");
        trie.erase("bcfg");
        trie.insert("cde");
        System.out.println(trie.countWordsEqualTo("abc"));
        System.out.println(trie.countWordsStartingWith("ac"));
        System.out.println(trie.countWordsStartingWith("bc"));
    }
}
