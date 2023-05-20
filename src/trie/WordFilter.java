package trie;

import java.util.ArrayList;
import java.util.List;

public class WordFilter {
    public static class TrieNode {
        // 孩子节点
        public TrieNode[] children;
        // 划过某个节点的字符下标
        public List<Integer> indices;

        public TrieNode() {
            children = new TrieNode[26];
            indices = new ArrayList<>();
        }
    }

    private TrieNode preRoot;
    private TrieNode sufRoot;

    public WordFilter(String[] words) {
        preRoot = new TrieNode();
        sufRoot = new TrieNode();
        int index = 0;
        for (String word: words) {
            char[] chars = word.toCharArray();
            int len = chars.length;
            int node;
            TrieNode cur = preRoot;
            for (int i = 0; i < len; i ++) {
                // 节点值（int）
                node = chars[i] - 'a';
                // 如果节点不存在，建出节点
                if (cur.children[node] == null) {
                    cur.children[node] = new TrieNode();
                }
                cur = cur.children[node];
                cur.indices.add(index);
            }
            cur = sufRoot;
            for (int i = len - 1; i >= 0; i --) {
                // 节点值（int）
                node = chars[i] - 'a';
                // 如果节点不存在，建出节点
                if (cur.children[node] == null) {
                    cur.children[node] = new TrieNode();
                }
                cur = cur.children[node];
                cur.indices.add(index);
            }
            index ++;
        }
    }

    public int f(String pref, String suff) {
        char[] prefChars = pref.toCharArray();
        char[] suffChars = suff.toCharArray();
        TrieNode cur = preRoot;
        int node;
        for (char c : prefChars) {
            // 节点值（int）
            node = c - 'a';
            // 如果节点不存在，说明前缀树中不包含 pref
            if (cur.children[node] == null) {
                return -1;
            }
            cur = cur.children[node];
        }
        List<Integer> preIndices = cur.indices;
        cur = sufRoot;
        for (int i = suffChars.length - 1; i >= 0; i --) {
            // 节点值（int）
            node = suffChars[i] - 'a';
            // 如果节点不存在，说明后缀树中不包含 suff
            if (cur.children[node] == null) {
                return -1;
            }
            cur = cur.children[node];
        }
        List<Integer> sufIndices = cur.indices;
        List<Integer> small = preIndices.size() <= sufIndices.size() ? preIndices : sufIndices;
        List<Integer> big = small == preIndices ? sufIndices : preIndices;
        // indices 数组必定是有序的，用短的数组从尾部开始在常的数组中进行二分查找，如果能找到，它就是包含前缀和后缀的最大的下标，否则返回 -1
        for (int i = small.size() - 1; i >= 0; i --) {
            if (bs(big, small.get(i))) {
                return small.get(i);
            }
        }
        return -1;
    }

    private boolean bs(List<Integer> arr, int num) {
        int l = 0;
        int r = arr.size() - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (arr.get(mid) == num) {
                return true;
            } else if (arr.get(mid) < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"abbba", "abba"};
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("ab", "ba"));
    }
}
