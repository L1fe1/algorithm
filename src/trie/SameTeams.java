package trie;

import java.util.*;

/**
 * 假设数组 a 和数组 b为两组信号
 * 1) length(b) <= length(a)
 * 2) 对于任意 0<=i<length(b), 有 b[i+1] - b[i] == a[i+1] - a[i]
 * 那么就称信号 b 和信号 a 一致，记为 b==a
 * 给你好多 b 数组，假设有 m 个: b0 数组、b1 数组...
 * 给你好多 a 数组，假设有 n 个: a0 数组、a1 数组...
 * 返回一个长度为 m 的结果数组 ans，ans[i] 表示：bi 数组和多少个 a 数组一致
 */
public class SameTeams {
    public static class TrieNode {
        // 孩子节点
        public Map<Integer, TrieNode> children;
        // 划过某个节点的次数
        public int pass;

        public TrieNode() {
            children = new HashMap<>();
            pass = 0;
        }
    }

    public static int[] sameTeamsArray(int[][] bArr, int[][] aArr) {
        TrieNode root = new TrieNode();
        TrieNode cur;
        int node;
        // aArr 中所有数组建前缀树（相邻两数差值）
        for (int[] a : aArr) {
            cur = root;
            for (int i = 1; i < a.length; i++) {
                node = a[i] - a[i - 1];
                if (!cur.children.containsKey(node)) {
                    cur.children.put(node, new TrieNode());
                }
                cur = cur.children.get(node);
                cur.pass ++;
            }
        }
        int[] ans = new int[bArr.length];
        int index = 0;
        boolean same = true;
        // bArr 中的所有数组相邻两数差值元素在前缀树中查找
        for (int[] b : bArr) {
            cur = root;
            for (int i = 1; i < b.length; i++) {
                node = b[i] - b[i - 1];
                if (!cur.children.containsKey(node)) {
                    same = false;
                    break;
                }
                cur = cur.children.get(node);
            }
            // 如果包含所有差值，那么最后节点的 pass 值即为一致的个数，否则为 0
            ans[index++] = same ? cur.pass : 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] bArr = new int[][]{{4, 8, 4}, {5, 10}};
        int[][] aArr = new int[][]{{1, 5, 1, 10, 2}, {0, 4, 0, 2}, {20, 25}};
        System.out.println(Arrays.toString(sameTeamsArray(bArr, aArr)));
    }
}
