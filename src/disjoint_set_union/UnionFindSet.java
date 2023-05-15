package disjoint_set_union;

import java.io.*;

/**
 * 给定一个没有重复值的整形数组 arr，初始时认为 arr 中每一个数各自都是一个单独的集合。请设计一种叫 UnionFind 的结构，并提供以下两个操作。
 * boolean isSameSet(int a, int b): 查询 a 和 b 这两个数是否属于一个集合
 * void union(int a, int b): 把 a 所在的集合与 b 所在的集合合并在一起，原本两个集合各自的元素以后都算作同一个集合
 * [要求]
 * 如果调用 isSameSet 和 union 的总次数逼近或超过 O(N)，请做到单次调用 isSameSet 或 union 方法的平均时间复杂度为 O(1)
 * 输入描述:
 * 第一行两个整数 N, M。分别表示数组大小、操作次数
 * 接下来M行，每行有一个整数 opt
 * 若 opt = 1，后面有两个数 x, y，表示查询 (x, y) 这两个数是否属于同一个集合
 * 若 opt = 2，后面有两个数 x, y，表示把 x, y 所在的集合合并在一起
 * 输出描述:
 * 对于每个 opt = 1 的操作，若为真则输出 "Yes"，否则输出 "No"
 */
public class UnionFindSet {
    public static int MAX = 1000001;
    // 并查集元素的父节点
    public static int[] parents = new int[MAX];
    // x 节点到它所在集合的根节点的路径上的所有节点
    public static int[] paths = new int[MAX];
    // 只有 x 为根节点时 sizes[x] 才有意义，代表以 x 为根节点的并查集大小
    public static int[] sizes = new int[MAX];

    public static void init(int n) {
        // 初始化
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    public static int find(int x) {
        int index = 0;
        // 从 x 向上找到它的根（代表）节点，将沿途经历的节点记录在 paths 中
        while (x != parents[x]) {
            paths[index++] = x;
            x = parents[x];
        }
        // 将 path 扁平化
        int pathIndex = index - 1;
        while (pathIndex >= 0) {
            parents[paths[pathIndex--]] = x;
        }
        return x;
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public static void union(int x, int y) {
        int xParent = find(x);
        int yParent = find(y);
        // 如果 x 和 y 不属于同一个并查集，合并
        if (xParent != yParent) {
            // 小集合挂在大集合的根节点下面
            if (sizes[xParent] < sizes[yParent]) {
                parents[xParent] = yParent;
                sizes[yParent] += sizes[xParent];
            } else {
                parents[yParent] = xParent;
                sizes[xParent] += sizes[yParent];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            init(n);
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                    out.flush();
                } else {
                    union(x, y);
                }
            }
        }
    }
}
